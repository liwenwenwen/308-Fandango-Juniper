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
                /*access session*/
                HttpSession session = request.getSession(false);
                Account user = (Account)session.getAttribute("UserInfoSession");
                int userId = user.getId();
                List<Movie> movieFavList = makeMovieFavList(em,userId);
                em.close();
                     
                /*create session here*/
                HttpSession movieMainSession = request.getSession();
                movieMainSession.setAttribute("MovieFavList", movieFavList);
                
                RequestDispatcher rd = request.getRequestDispatcher("userAccount.jsp");
                rd.include(request, response);
              
    }
    public List<Movie> makeMovieFavList(EntityManager em, Integer userId){
        /*search user faved obj*/
        TypedQuery<MovieFav> query = em.createNamedQuery("MovieFav.findByUserId", MovieFav.class);
        query.setParameter("userId", userId);
        List<MovieFav> movieFavResults = query.getResultList();
        /*use movieId in faved obj to search whole movie info*/
        List<Movie> movieFavList = new ArrayList<Movie>();
        for(int i=0;i<movieFavResults.size();i++){
            Movie movieObj = movieFavResults.get(i).getMovieId();
            movieFavList.add(movieObj);
        }
        return movieFavList;
    }
    
}
