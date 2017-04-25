/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author liwenfan
 */
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entity.Movie;
import entity.Theaters;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
import static source.Constants.DISPLAY_MAIN_THEATERS;

public class DisplayTheatersMainController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");	
                EntityManager em = EMF.createEntityManager();
                HttpSession theaterMainSession = request.getSession();
                
                List<Theaters> theaterResults = makeTheaterMainList(em);
                em.close();
                theaterMainSession.setAttribute("TheaterMainList", theaterResults);
                
                RequestDispatcher rd = request.getRequestDispatcher("theaters.jsp");
                rd.forward(request, response);
              
    }
    public List<Theaters> makeTheaterMainList(EntityManager em){
        TypedQuery<Theaters> query = em.createNamedQuery("Theaters.findAll", Theaters.class);
        List<Theaters> theaterResults = query.setMaxResults(DISPLAY_MAIN_THEATERS).getResultList();
        return theaterResults;
    }
    
}
