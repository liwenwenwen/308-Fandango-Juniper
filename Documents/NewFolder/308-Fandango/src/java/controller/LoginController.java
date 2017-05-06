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
import entity.TheaterFav;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
import servlet.HashPassword;



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
                Account checkedUserList =null;                
                try {
                    checkedUserList = checkUsernamePassword(em,uname,pass);
                    if(checkedUserList==null){
                        em.close();
                        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    }else{
                        Account loginUser =checkedUserList;
                        Payments payment = loginUser.getPaymentId();
                        userInfoSession.setAttribute("Payment", payment);
                        List<String> theaterFavForIcon = checkUserTheaterFav(em,loginUser);
                        userInfoSession.setAttribute("TheaterFavList", theaterFavForIcon);
                        userInfoSession.setAttribute("UserInfoSession", loginUser);
                        em.close();
                        RequestDispatcher rd = request.getRequestDispatcher("movies.jsp");
                        rd.forward(request, response);
                 }    
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
	}
    public Account checkUsernamePassword(EntityManager em, String uname, String pass) throws Exception{
       HashPassword hashPass = new HashPassword();
       /*
       TypedQuery<Account> query = em.createNamedQuery("Account.findByUserNamePassword", Account.class);
       query.setParameter("userName", uname);
       query.setParameter("password",pass);
       List<Account> accResults = query.getResultList();
       return accResults;
       */
       TypedQuery<Account> query = em.createNamedQuery("Account.findByUserName",Account.class);
       query.setParameter("userName",uname);
       List<Account> accResultList = query.getResultList();
       for(int i=0;i<accResultList.size();i++){
           String storedPass = accResultList.get(i).getPassword();
           boolean checkpass = hashPass.check(pass,storedPass);
           if(checkpass==true){
               return accResultList.get(i);
           }
       }
       return null;
    }
    public List<String> checkUserTheaterFav(EntityManager em,Account loginUser){
       TypedQuery<TheaterFav> query = em.createNamedQuery("TheaterFav.findByUserId", TheaterFav.class);
       int userId = loginUser.getId();
       query.setParameter("userId", userId);
       List<TheaterFav> theaterFavtemp = query.getResultList();
       List<String> theaterFav = new ArrayList<String>();
       for(int i=0;i<5;i++){
           theaterFav.add("TNF");
           for(int j=0;j<theaterFavtemp.size();j++){
               if(theaterFavtemp.get(j).getTheaterId().getId()==(i+1)){
                   theaterFav.set(i,"TF");
               }
           }
       }
       return theaterFav;
    }
    
}
