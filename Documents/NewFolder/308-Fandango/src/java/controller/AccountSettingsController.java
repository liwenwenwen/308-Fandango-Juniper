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
public class AccountSettingsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
                System.out.println("enter account seeting servclet");
		
		
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("308-FandangoPU");
                EntityManager em = emf.createEntityManager();
                
                /*access session*/
                HttpSession session = request.getSession(false);
                Account user = (Account)session.getAttribute("UserInfoSession");
                if(user==null){
                    System.out.println("no session user find");
                }
                System.out.println("user name in session"+user.getId());
                
                if(uname!=null){
                    user.setUserName(uname);
                }else if(pass!=null){
                    user.setPassword(pass);
                }
                
                em.getTransaction().begin();
                em.merge(user); 
                em.getTransaction().commit();
                
                
                
                
                
                em.close();
                emf.close();
                     
                    
                /*create session here*/
                HttpSession userInfoSession = request.getSession();
                userInfoSession.setAttribute("UserInfoSession", user);
                /*create session here*/
                     
                RequestDispatcher rd = request.getRequestDispatcher("userAccount.jsp");
                rd.forward(request, response);
                 
                 
      

                
	}
    
}
