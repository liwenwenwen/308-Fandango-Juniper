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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import entity.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NamedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class DisplayMoviesMainController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		//String uname = request.getParameter("username");
		//String pass = request.getParameter("password");
		
		
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("308-FandangoPU");
                EntityManager em = emf.createEntityManager();
                
                em.getTransaction().begin();
                /*set a query*/
                TypedQuery<Movie> query = em.createNamedQuery("Movie.findAll", Movie.class);
                List<Movie> movieResults = query.getResultList();
                //set max result number
                
                
                //System.out.println(movieResults.get(0).getTitle());
               
                
                em.getTransaction().commit();
                em.close();
                emf.close();
                     
                /*create session here*/
                request.setAttribute("MovieMainList", movieResults);
                
                
                /*create session here*/
                //System.out.println("enter the servlet");
                //out.println("<font color=red>Please fill all the fields</font>");
                //request.getRequestDispatcher("movies.jsp").forward(request, response);
                //RequestDispatcher rd = request.getRequestDispatcher("movies.jsp");
                //rd.forward(request, response);
                 
                 
      

                
    }
    
}