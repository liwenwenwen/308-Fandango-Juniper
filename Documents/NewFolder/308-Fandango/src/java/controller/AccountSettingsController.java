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
import entity.Account;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
public class AccountSettingsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
               
                EntityManager em = EMF.createEntityManager();
                /*access session*/
                HttpSession session = request.getSession(false);
                Account user = (Account)session.getAttribute("UserInfoSession");
                changeSettings(user,uname,pass);

                em.getTransaction().begin();
                em.merge(user); 
                em.getTransaction().commit();
 
                em.close();
                     
                RequestDispatcher rd = request.getRequestDispatcher("userAccount.jsp");
                rd.forward(request, response);        
    }
    public void changeSettings(Account user,String uname,String pass){
        if(uname!=null){
            user.setUserName(uname);
        }else if(pass!=null){
            user.setPassword(pass);
        }else{
            
        }
    }
}
