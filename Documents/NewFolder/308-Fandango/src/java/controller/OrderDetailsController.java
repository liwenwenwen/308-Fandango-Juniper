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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
public class OrderDetailsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String orderId = request.getParameter("orderId");
                EntityManager em = EMF.createEntityManager();
                HttpSession session = request.getSession(true);
                
                Orders order = em.find(Orders.class,Integer.parseInt(orderId));
                MovieSchedules schedule = order.getScheduleId();
                MovieShowings showing = schedule.getShowingId();
                Movie movie = showing.getSmovieId();
                Theaters theater =showing.getStheaterId();
                
                double total = (order.getNumTickets())*(showing.getUnitPrice());
                String totalPrice = Double.toString(total);
                
                request.setAttribute("OrderOrder", order);
                request.setAttribute("OrderSchedule", schedule);
                request.setAttribute("OrderShowing", showing);
                request.setAttribute("OrderMovie", movie);
                request.setAttribute("OrderTheater", theater);
                request.setAttribute("OrderPrice", totalPrice);
                
                     
                RequestDispatcher rd = request.getRequestDispatcher("orderDetails.jsp");
                rd.forward(request, response);        
    }
    
}
