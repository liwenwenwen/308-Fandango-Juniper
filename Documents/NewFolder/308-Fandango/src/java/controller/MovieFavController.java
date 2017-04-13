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
import java.io.PrintWriter;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import entity.Account;
import entity.Movie;
import entity.MovieFav;
import java.util.List;
import javax.persistence.NamedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class MovieFavController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		String checkFav = request.getParameter("fav");
                
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("308-FandangoPU");
                EntityManager em = emf.createEntityManager();
                
                //System.out.println(fav);
		/*Check isFaved or isNotFaved*/
                if(checkFav.equals("isFaved")){
                    int favId = Integer.parseInt(request.getParameter("favId"));
                    //remove fav
                    MovieFav fav = em.find(MovieFav.class, favId);
 
                    em.getTransaction().begin();
                    em.remove(fav);
                    em.getTransaction().commit();
                }else if(checkFav.equals("isNotFaved")){
                    int movieId = Integer.parseInt(request.getParameter("favId"));
                    Movie movie = em.find(Movie.class, movieId);
                    //add fav
                    MovieFav fav = new MovieFav();
                    fav.setMovieId(movie);
                    /*access session*/
                    HttpSession session = request.getSession(false);
                    Account user = (Account)session.getAttribute("UserInfoSession");
                    int userId = user.getId();
                    fav.setUserId(user);
  
                    em.getTransaction().begin();
                    em.persist(fav);
                    em.getTransaction().commit();
                }else{
                    //wrong input
                }
                
               
                
                
              
                em.close();
                emf.close();
                
                RequestDispatcher rd = request.getRequestDispatcher("movieDetails.jsp");
                rd.forward(request, response);
                //response.sendRedirect("movieDetails.jsp");
    }
}