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
import entity.MovieSchedules;
import entity.MovieShowings;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;

public class PurchaseTicketsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
                int numTickets = Integer.parseInt(request.getParameter("numTickets"));
                EntityManager em = EMF.createEntityManager();
                HttpSession session = request.getSession(true);
                
                MovieSchedules schedule = (MovieSchedules)session.getAttribute("schedule");
                int ticketsLeft = schedule.getNumTicketsLeft();
                if(ticketsLeft>=numTickets){
                    MovieShowings showing = schedule.getShowingId();
                    double unitPrice = showing.getUnitPrice();
                    double totalPrice = unitPrice * numTickets;
                    session.setAttribute("numTickets",numTickets);
                    request.setAttribute("TotalPrice", totalPrice);
                    request.setAttribute("unitPrice",unitPrice);
                }else{
                    //not enought tickets
                    request.setAttribute("TotalPrice", 0);
                }
                em.close();
                
                RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
                rd.forward(request, response);
    }
}
