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
import entity.Account;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entity.Movie;
import entity.MovieFav;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;

public class DisplayUserFavController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");	
                EntityManager em = EMF.createEntityManager();
                HttpSession session = request.getSession(false);
                HttpSession movieFavSession = request.getSession();

                Account user = (Account)session.getAttribute("UserInfoSession");
                int userId = user.getId();
                List<Movie> movieFavList = makeMovieFavList(em,userId);
                em.close();
                movieFavSession.setAttribute("MovieFavList", movieFavList);
                
                RequestDispatcher rd = request.getRequestDispatcher("userAccount.jsp");
                rd.forward(request, response);
              
    }
    public List<Movie> makeMovieFavList(EntityManager em, Integer userId){
        List<Movie> movieFavList = new ArrayList<Movie>();
        TypedQuery<MovieFav> query = em.createNamedQuery("MovieFav.findByUserId", MovieFav.class);
        query.setParameter("userId", userId);
        List<MovieFav> movieFavResults = query.getResultList();
        for(int i=0;i<movieFavResults.size();i++){
            Movie movieObj = movieFavResults.get(i).getMovieId();
            movieFavList.add(movieObj);
        }
        return movieFavList;
    }
    
}
