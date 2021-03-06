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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
import servlet.HashPassword;
import static source.Constants.CHECKOUT_TIME_FORMAT;



public class RegistrationController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String uname = request.getParameter("username");
                String email=request.getParameter("email");
		String pass = request.getParameter("password");
		EntityManager em = EMF.createEntityManager();
                HttpSession userInfoSession = request.getSession();
                
		if(uname.isEmpty()||pass.isEmpty()||email.isEmpty()){
                    request.setAttribute("registerEmpty", "true");
                    RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                    rd.include(request, response);
		}else{ 
                    boolean allowToRegister = checkUsernameEmail(em,uname,email);
                    if(allowToRegister==false){
                        em.close();
                        request.setAttribute("registerExist", "true");
                        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                        rd.include(request, response);
                    }else{
                        Account newUser;
                        try {
                            newUser = createNewAccount(em,uname,email,pass);
                            em.getTransaction().begin();
                            em.persist(newUser);
                            em.getTransaction().commit();
                            em.close();
                            userInfoSession.setAttribute("Payment", null);
                            userInfoSession.setAttribute("TheaterFavList", null);
                            userInfoSession.setAttribute("UserInfoSession", newUser);
                        } catch (Exception ex) {
                            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        RequestDispatcher rd = request.getRequestDispatcher("movies.jsp");
                        rd.forward(request, response);
                    }
                    
            }
                
        }
    public boolean checkUsernameEmail(EntityManager em,String uname,String email){
        TypedQuery<Account> usernamequery = em.createNamedQuery("Account.findByUserName", Account.class);
        usernamequery.setParameter("userName", uname);
        TypedQuery<Account> emailquery = em.createNamedQuery("Account.findByEmail", Account.class);
        emailquery.setParameter("email", email);
        if((usernamequery.getResultList()).isEmpty()&&(emailquery.getResultList()).isEmpty()){
            return true;
        }
        return false;
        
    }
        
    public Account createNewAccount(EntityManager em,String uname,String email,String pass) throws Exception{
        Account newUser = new Account();
        Date currentDate = new Date();
        HashPassword hashPass = new HashPassword();
        String strCurrentDate = new SimpleDateFormat(CHECKOUT_TIME_FORMAT).format(currentDate);
        String hasedPassword = hashPass.getSaltedHash(pass);
        
        newUser.setJoinedDate(strCurrentDate);
        newUser.setUserName(uname);
        newUser.setEmail(email);
        newUser.setPassword(hasedPassword);
        return newUser;
    }
}
