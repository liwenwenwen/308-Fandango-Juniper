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
import entity.Account;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RegistrationController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		String uname = request.getParameter("username");
                String email=request.getParameter("email");
		String pass = request.getParameter("password");
		
		if(uname.isEmpty()||pass.isEmpty()||email.isEmpty())
		{
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			//out.println("<font color=red>Please fill all the fields</font>");
			rd.include(request, response);
		}
		else
		{
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("308-FandangoPU");
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                
                Account newUser = new Account();
                newUser.setUserName(uname);
                newUser.setEmail(email);
                newUser.setPassword(pass);
                
                
                System.out.println("In register controller userid = "+newUser.getId());
                
                em.persist(newUser);
                
                
                /*create session here*/
                //HttpSession userInfoSession = request.getSession();
                //userInfoSession.setAttribute("UserInfoSession", newUser);
                /*create session here*/
                em.getTransaction().commit();
               
                TypedQuery<Account> query = em.createNamedQuery("Account.findByUserName", Account.class);
                query.setParameter("userName", uname);
                List<Account> accResults = query.getResultList();
                Account reigstedUser = accResults.get(0);
                newUser.setId(reigstedUser.getId());
                
                HttpSession userInfoSession = request.getSession();
                userInfoSession.setAttribute("UserInfoSession", newUser);
                
                
                em.close();
                emf.close();
                
                
                //RequestDispatcher rd = request.getRequestDispatcher("movies.jsp");
		//rd.forward(request, response);
               // RequestDispatcher rd = request.getRequestDispatcher("movies.jsp");
                //rd.forward(request, response);
                response.sendRedirect("movies.jsp");
		}
                
	}
}
