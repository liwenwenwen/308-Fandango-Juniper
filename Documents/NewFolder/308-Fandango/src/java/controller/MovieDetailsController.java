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
public class MovieDetailsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("308-FandangoPU");
                EntityManager em = emf.createEntityManager();
                
                /*find specific movie object by id*/
                Movie movieInfo = em.find(Movie.class,movieId);
		 
                    
                /*create session here - store movie obj*/
                request.setAttribute("MovieInfo", movieInfo);
                /*create session here*/
                
                /*check if this specific movie is Faved by the user*/
                HttpSession session = request.getSession(false);
                Account user = (Account)session.getAttribute("UserInfoSession");
                if(user==null){
                    // user is not logged in
                    /*create session here*/
                        request.setAttribute("UserFaved", "null");
                        /*create session here*/
                }else{
                    // user is logged in, need to check if movie is faved
                    int userId=user.getId();
                    TypedQuery<MovieFav> query = em.createNamedQuery("MovieFav.findByUserIdMovieId", MovieFav.class);
                    query.setParameter("userId", userId);
                    query.setParameter("movieId",movieId);
                    List<MovieFav> movieFavResults = query.getResultList();
                    
                    if(movieFavResults.isEmpty()){
                        //user is NOT faved
                        /*create session here*/
                        request.setAttribute("UserFaved", "isNotFaved");
                        /*create session here*/
                    }else{
                        //user is faved
                        /*create session here*/
                        MovieFav userFavOne = movieFavResults.get(0);
                        int favOneId = userFavOne.getId();
                        request.setAttribute("UserFaved", "isFaved");
                        request.setAttribute("FavObj",favOneId);
                        /*create session here*/
                    }
                    
                }
                /*check if this specific movie is Faved by the user*/
                
                     
                RequestDispatcher rd = request.getRequestDispatcher("movieDetails.jsp");
                rd.forward(request, response);
                 
                 
      

                
	}
    
}

