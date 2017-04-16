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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;

public class DisplayMoviesMainController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
                EntityManager em = EMF.createEntityManager();
                List<Movie> movieResults = makeMovieMainList(em);
                em.close();
                     
                /*create session here*/
                HttpSession movieMainSession = request.getSession();
                movieMainSession.setAttribute("MovieMainList", movieResults);
              
    }
    public List<Movie> makeMovieMainList(EntityManager em){

        TypedQuery<Movie> query = em.createNamedQuery("Movie.findAll", Movie.class);
        List<Movie> movieResults = query.setMaxResults(10).getResultList();
        return movieResults;
    }
    
}