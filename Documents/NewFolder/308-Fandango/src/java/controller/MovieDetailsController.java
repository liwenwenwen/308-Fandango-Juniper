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
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import entity.Account;
import entity.Movie;
import entity.MovieFav;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;

public class MovieDetailsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		
                EntityManager em = EMF.createEntityManager();
                
                Movie movieInfo = em.find(Movie.class,movieId);    
                /*create session here - store movie obj*/
                HttpSession movieSession = request.getSession(true);
                movieSession.setAttribute("MovieInfo", movieInfo);
                /*check if this specific movie is Faved by the user*/
                HttpSession session = request.getSession(false);
                Account user = (Account)session.getAttribute("UserInfoSession");
                if(user!=null){
                 
                    // user is logged in, need to check if movie is faved
                    int userId=user.getId();
                    MovieFav userFaved = checkUserFav(em,userId,movieId);

                    if(userFaved==null){
                        HttpSession favSession = request.getSession(true);
                        favSession.setAttribute("FavId", null);
                    }else{
                        int favId = userFaved.getId();
                        HttpSession favSession = request.getSession(true);
                        favSession.setAttribute("FavId",String.valueOf(favId));
                    }
                    
                }
   
                //RequestDispatcher rd = request.getRequestDispatcher("movieDetails.jsp");
                //rd.forward(request, response);
                response.sendRedirect("movieDetails.jsp");
        
                
	}
    public MovieFav checkUserFav(EntityManager em,Integer userId,Integer movieId){
        MovieFav userFaved =null;
        TypedQuery<MovieFav> query = em.createNamedQuery("MovieFav.findByUserIdMovieId", MovieFav.class);
        query.setParameter("userId", userId);
        query.setParameter("movieId",movieId);
        List<MovieFav> movieFavResults = query.getResultList();
        if(movieFavResults.isEmpty()){

        }else{
            userFaved = movieFavResults.get(0);
        }
        return userFaved;
    }
    
}

