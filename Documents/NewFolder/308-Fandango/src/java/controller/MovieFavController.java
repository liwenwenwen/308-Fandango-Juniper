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
                HttpSession favSession = request.getSession(true);
                HttpSession userSession = request.getSession(true);
                
                Movie movie = em.find(Movie.class, movieId);
                if(favId!=null){                  
                    removeFav(em,favId);
                    favSession.setAttribute("FavId", null);
                }else{
                    Account user = (Account)userSession.getAttribute("UserInfoSession");
                    MovieFav fav = addFav(em,movie,user);
                    int newFavId = fav.getId();
                    favSession.setAttribute("FavId", String.valueOf(newFavId));
                }          
                em.close();                
                RequestDispatcher rd = request.getRequestDispatcher("movieDetails.jsp");
                rd.forward(request, response);
    }
    public void removeFav(EntityManager em,String favId){
        MovieFav fav = em.find(MovieFav.class, Integer.parseInt(favId));
        em.getTransaction().begin();
        em.remove(fav);
        em.getTransaction().commit();
    }
    public MovieFav addFav(EntityManager em,Movie movie,Account user){
        MovieFav fav = new MovieFav();
        fav.setMovieId(movie);
        fav.setUserId(user);  
        em.getTransaction().begin();
        em.persist(fav);
        em.getTransaction().commit();
        return fav;
    }
}