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
import entity.Account;
import entity.Movie;
import entity.MovieFav;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
public class MovieFavController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
                String favId = request.getParameter("favId");
                int movieId = Integer.parseInt(request.getParameter("movieId"));
                
                EntityManager em = EMF.createEntityManager();

                Movie movie = em.find(Movie.class, movieId);
                if(favId!=null){
                    //remove fav
                    MovieFav fav = em.find(MovieFav.class, Integer.parseInt(favId));
                    em.getTransaction().begin();
                    em.remove(fav);
                    em.getTransaction().commit();
                    
                    HttpSession favSession = request.getSession(false);
                    favSession.setAttribute("FavId", null);
                  
                }else{
                    //add fav
                    MovieFav fav = new MovieFav();
                    fav.setMovieId(movie);
                    HttpSession session = request.getSession(false);
                    Account user = (Account)session.getAttribute("UserInfoSession");
                    fav.setUserId(user);
  
                    em.getTransaction().begin();
                    em.persist(fav);
                    em.getTransaction().commit();
    
                    int newFavId = fav.getId();
                    HttpSession favSession = request.getSession(false);
                    favSession.setAttribute("FavId", String.valueOf(newFavId));
                }
          
                em.close();
                
                //RequestDispatcher rd = request.getRequestDispatcher("movieDetails.jsp");
                //rd.forward(request, response);
                response.sendRedirect("movieDetails.jsp");
    }
}