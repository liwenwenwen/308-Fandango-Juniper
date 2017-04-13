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
import java.util.List;
import javax.persistence.NamedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		
		if(uname.isEmpty()||pass.isEmpty())
		{
                    /* adding tags for this part*/
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			//out.println("<font color=red>Please fill all the fields</font>");
			rd.include(request, response);
		}
		
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("308-FandangoPU");
                EntityManager em = emf.createEntityManager();

                em.getTransaction().begin();
                
                /* search User Account with [username and password], both should be correct*/
  
                 TypedQuery<Long> searchAccQuery = em.createQuery("SELECT COUNT(c) FROM Account c WHERE c.userName = :userName AND c.password = :password", Long.class);
                 searchAccQuery.setParameter("userName", uname);
                 searchAccQuery.setParameter("password", pass); 
                 
                 Long numloginUser = searchAccQuery.getSingleResult();
                
                 if(numloginUser==0){
                     System.out.println("    no user    ");
                     RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                     //out.println("<font color=red>Wrong info entered</font>");
                     rd.include(request, response);
                 }else{
                     
                     System.out.println("    yes user    ");
                     
                     
                     TypedQuery<Account> query = em.createNamedQuery("Account.findByUserName", Account.class);
                     query.setParameter("userName", uname);
                     List<Account> accResults = query.getResultList();
                     Account loginUser = accResults.get(0);
                     System.out.println("    "+loginUser.getId()+"   ");
                      
                      /*create session here*/
                     HttpSession userInfoSession = request.getSession();
                     userInfoSession.setAttribute("UserInfoSession", loginUser);
                     /*create session here*/
                     
                     //RequestDispatcher rd = request.getRequestDispatcher("movies.jsp");
                     //rd.forward(request, response);
                     response.sendRedirect("movies.jsp");
                 }
                 
      
                em.getTransaction().commit();
                em.close();
                emf.close();
                
	}
    
}
