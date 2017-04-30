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
import entity.Payments;
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
                String cardNumber = request.getParameter("cardNumber");
                String year = request.getParameter("year");
                String month = request.getParameter("month");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String zipcode = request.getParameter("zipcode");
                EntityManager em = EMF.createEntityManager();
                HttpSession session = request.getSession(true);
                
                Account user = (Account)session.getAttribute("UserInfoSession");
                Payments pay = new Payments();
                changeSettings(em,user,pay,uname,pass,cardNumber,year,month,firstName,lastName,zipcode);
                em.getTransaction().begin();
                em.merge(user); 
                em.getTransaction().commit();
                em.close();
                if((cardNumber!=null)&&(year!=null)&&(month!=null)&&(firstName!=null)&&(lastName!=null)&&(zipcode!=null)){
                    session.setAttribute("Payment",pay);
                }
                RequestDispatcher rd = request.getRequestDispatcher("userAccount.jsp");
                rd.forward(request, response);        
    }
    public void changeSettings(EntityManager em,Account user,Payments pay,String uname,String pass,String cardNumber,String year,String month,String firstName,String lastName,String zipcode){
        System.out.println("enter1");
        if(uname!=null){
            user.setUserName(uname);
        }else if(pass!=null){
            user.setPassword(pass);
        }else if((cardNumber!=null)&&(year!=null)&&(month!=null)&&(firstName!=null)&&(lastName!=null)&&(zipcode!=null)){
            System.out.println("enter2");
            if(user.getPaymentId()==null){
                pay.setAccount(user);
                pay.setCardNum(cardNumber);
                pay.setExpirationMonth(month);
                pay.setExpirationYear(year);
                pay.setFirstName(firstName);
                pay.setLastName(lastName);
                pay.setBillingZip(zipcode);
                user.setPaymentId(pay);
                em.getTransaction().begin();
                em.persist(pay); 
                em.merge(user);
                em.getTransaction().commit();
            }else{
                Payments oldPay = user.getPaymentId();
                oldPay.setCardNum(cardNumber);
                oldPay.setExpirationMonth(month);
                oldPay.setExpirationYear(year);
                oldPay.setFirstName(firstName);
                oldPay.setLastName(lastName);
                oldPay.setBillingZip(zipcode);
                em.getTransaction().begin(); 
                em.merge(oldPay);
                em.getTransaction().commit();
            }
        }
    }
}
