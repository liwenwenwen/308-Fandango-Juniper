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
import entity.Movie;
import entity.MovieSchedules;
import entity.MovieShowings;
import entity.Orders;
import entity.Theaters;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
import servlet.sendEmails;
import static source.Constants.EMAIL_DOLLER_SIGN;
import static source.Constants.EMAIL_FROM;
import static source.Constants.EMAIL_HI;
import static source.Constants.EMAIL_ORDER;
import static source.Constants.EMAIL_ORDER_DATE;
import static source.Constants.EMAIL_PASSWORD;
import static source.Constants.EMAIL_PER_EACH;
import static source.Constants.EMAIL_SPACE;
import static source.Constants.EMAIL_SUBJECT;
import static source.Constants.EMAIL_TAB;
import static source.Constants.EMAIL_TOTAL;

public class PurchaseTicketsMainController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
                EntityManager em = EMF.createEntityManager();
                String cardnum = request.getParameter("cardNumber");
                String expirM = request.getParameter("expirationMonth");
                String expirY = request.getParameter("expirationYear");
                String firstN = request.getParameter("firstName");
                String lastN = request.getParameter("lastName");
                String zipcode = request.getParameter("zipcode");
                HttpSession session = request.getSession(true);
                
                boolean paySccuessed = makePayment(cardnum,expirM,expirY,firstN,lastN,zipcode);
                if(paySccuessed==true){
                    Account user = (Account)session.getAttribute("UserInfoSession");
                    int numTickets = (Integer)session.getAttribute("numTickets");
                    MovieSchedules schedule = (MovieSchedules)session.getAttribute("schedule");
                    Date createDate = new Date();
                    int ticketsBought = (Integer)session.getAttribute("numTickets");
                    removeTickets(em,schedule,ticketsBought);
                    if(user==null){
                        String guestEmail = request.getParameter("email");
                        Orders newOrder = storeOrder(em,guestEmail,createDate,schedule,numTickets,user);
                        sendNotificationEmail(guestEmail,newOrder);
                    }else{
                        String memberEmail = user.getEmail();
                        Orders newOrder = storeOrder(em,memberEmail,createDate,schedule,numTickets,user);
                        sendNotificationEmail(memberEmail,newOrder);
                    }
                }else{
                    //display error in jsp
                }
                em.close();
                
                RequestDispatcher rd = request.getRequestDispatcher("movies.jsp");
                rd.forward(request, response);
    }
    public boolean makePayment(String cardnum,String expirM,String expirY,String firstN,String lastN,String zipcode){
        // since we are faking this step, we will always make this one sccuess.
        return true;
    }
    public Orders storeOrder(EntityManager em,String email,Date createDate,MovieSchedules schedule,int numTickets,Account user){
        Orders newOrder = new Orders();
        newOrder.setEmail(email);
        newOrder.setCreateDate(createDate);
        newOrder.setScheduleId(schedule);
        newOrder.setNumTickets(numTickets);
        newOrder.setUserId(user);
        em.getTransaction().begin();
        em.persist(newOrder);
        em.getTransaction().commit();
        return newOrder;
    }
    public void removeTickets(EntityManager em,MovieSchedules schedule,int ticketsBought){
        int total = schedule.getNumTicketsLeft();       
        schedule.setNumTicketsLeft(total -ticketsBought);
        em.getTransaction().begin();
        em.merge(schedule); 
        em.getTransaction().commit();
        
    }
    public void sendNotificationEmail(String email,Orders order){
        String from = EMAIL_FROM;
        String password = EMAIL_PASSWORD;
        String subject = EMAIL_SUBJECT;
        String[] to = {email};    
        String body = creatEmailBody(email,order);

        sendEmails.sendFromGMail(from, password, to, subject, body);
    }
    public String creatEmailBody(String email,Orders order){
        String emailBody;
        String emailBodyTitle;
        String emailBodayDisplayOrdernum;
        String emailBodyDisplayOrderDate;
        String emailBodyDisplayMovie;
        String emailBodyDisplayTheater;
        String emailBodyDisplayShowingDate;
        String emailBodyDisplayTicketNum;
        String emailBodyDisplayPrice;
        String emailBodyDisplayTotal;
        
        String hi = EMAIL_HI;
        String emailaddress = email;
        String tab = EMAIL_TAB;
        String space = EMAIL_SPACE;
        emailBodyTitle = hi+emailaddress+tab+tab;

        String body1 = EMAIL_ORDER;
        String ordernum = Integer.toString(order.getId());
        emailBodayDisplayOrdernum=body1+ordernum+tab+tab;

        String orderDate = EMAIL_ORDER_DATE;
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String orderDateDate = formatter.format(order.getCreateDate());
        emailBodyDisplayOrderDate = orderDate+orderDateDate+tab+tab;

        MovieSchedules schedule = order.getScheduleId();
        Format formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        String showDate = formatter2.format(schedule.getDate());
        String showTime = schedule.getTime();
        emailBodyDisplayShowingDate = showDate+space+showTime+space;
  
        MovieShowings showing = schedule.getShowingId();
        String price = Double.toString(showing.getUnitPrice());
        String dollerSign = EMAIL_DOLLER_SIGN;
        String each = EMAIL_PER_EACH;
        emailBodyDisplayPrice=dollerSign+price+each+space;
 
        Movie movie = showing.getSmovieId();
        String movieTitle = movie.getTitle();
        emailBodyDisplayMovie = movieTitle+space;

        Theaters theater =showing.getStheaterId();
        String theaterTitle = theater.getName();
        emailBodyDisplayTheater=theaterTitle+space;
    
        String num = Integer.toString(order.getNumTickets());
        emailBodyDisplayTicketNum=num+space+tab;
     
        double total = (order.getNumTickets())*(showing.getUnitPrice());
        String totalPrice = Double.toString(total);
        String tt = EMAIL_TOTAL;
        emailBodyDisplayTotal=tt+totalPrice;
        
        emailBody = emailBodyTitle+
                    emailBodayDisplayOrdernum+
                    emailBodyDisplayOrderDate+
                    emailBodyDisplayMovie+
                    emailBodyDisplayTheater+
                    emailBodyDisplayShowingDate+
                    emailBodyDisplayTicketNum+
                    emailBodyDisplayPrice+
                    emailBodyDisplayTotal;
     
        return emailBody;
    }
}