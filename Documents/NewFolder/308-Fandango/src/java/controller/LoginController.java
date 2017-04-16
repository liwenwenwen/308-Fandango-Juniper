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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;



public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
                
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		
		if(uname.isEmpty()||pass.isEmpty())
		{
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.include(request, response);
		}

                EntityManager em = EMF.createEntityManager();
                /* search User Account with [username and password], both should be correct*/
                List<Account> checkedUserList = checkUsernamePassword(em,uname,pass);
                
                if(checkedUserList.isEmpty()){
                     RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                     rd.include(request, response);
                 }else{
                     Account loginUser =checkedUserList.get(0);
                     /*create session here*/
                     HttpSession userInfoSession = request.getSession();
                     userInfoSession.setAttribute("UserInfoSession", loginUser);
                 } 
                em.close();
                response.sendRedirect("movies.jsp");
                
	}
    public List<Account> checkUsernamePassword(EntityManager em, String uname, String pass){
       TypedQuery<Account> query = em.createNamedQuery("Account.findByUserNamePassword", Account.class);
       query.setParameter("userName", uname);
       query.setParameter("password",pass);
       List<Account> accResults = query.getResultList();
       return accResults;
    }
    
}
