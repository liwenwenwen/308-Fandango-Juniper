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
import entity.Payments;
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
                EntityManager em = EMF.createEntityManager();
                HttpSession userInfoSession = request.getSession();
                
		if(uname.isEmpty()||pass.isEmpty())
		{
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
		}
                List<Account> checkedUserList = checkUsernamePassword(em,uname,pass);                
                if(checkedUserList.isEmpty()){
                     em.close();
                     RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                     rd.forward(request, response);
                 }else{
                     em.close();
                     Account loginUser =checkedUserList.get(0);
                     Payments payment = loginUser.getPaymentId();
                     userInfoSession.setAttribute("Payment", payment);
                     userInfoSession.setAttribute("UserInfoSession", loginUser);
                     RequestDispatcher rd = request.getRequestDispatcher("movies.jsp");
                     rd.forward(request, response);
                 }       
	}
    public List<Account> checkUsernamePassword(EntityManager em, String uname, String pass){
       TypedQuery<Account> query = em.createNamedQuery("Account.findByUserNamePassword", Account.class);
       query.setParameter("userName", uname);
       query.setParameter("password",pass);
       List<Account> accResults = query.getResultList();
       return accResults;
    }
    
}
