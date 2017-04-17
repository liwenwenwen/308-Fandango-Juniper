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
import entity.Account;
import entity.Movie;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
import static source.Constants.DISPLAY_SEARCH_RESULTS;



public class SearchController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		String searchTarget = request.getParameter("Search");    
                HttpSession searchTargetSession = request.getSession();
                searchTargetSession.setAttribute("SearchTarget", searchTarget);
                EntityManager em = EMF.createEntityManager();
                /* search target movies*/
                List<Movie> searchedMovies = searchResults(em,searchTarget);
                HttpSession searchSession = request.getSession();
                HttpSession searchCounts = request.getSession();
                if(searchedMovies.isEmpty()){
                    searchSession.setAttribute("SearchMovies", null);
                    searchCounts.setAttribute("SearchCounts", "0");
                }else{
                    searchSession.setAttribute("SearchMovies", searchedMovies);
                    searchCounts.setAttribute("SearchCounts", Integer.toString(searchedMovies.size()));                    
                }
                    
                response.sendRedirect("generalSearch.jsp");
    }
    
    public List<Movie> searchResults(EntityManager em,String searchTarget){
       TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m WHERE m.title LIKE :search", Movie.class);
       query.setParameter("search", "%" + searchTarget + "%");
       List<Movie> searchedMovies = query.setMaxResults(DISPLAY_SEARCH_RESULTS).getResultList();
       
       return searchedMovies;
    }

                
}
  

