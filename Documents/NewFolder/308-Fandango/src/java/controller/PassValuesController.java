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
import entity.Movie;
import entity.MovieFav;
import entity.MovieReviews;
import entity.MovieSchedules;
import entity.MovieShowings;
import entity.Theaters;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
import static source.Constants.CHECKOUT_TIME_FORMAT;
import static source.Constants.DEFAULT_THEATER_ID;
import static source.Constants.DISPLAY_MOVIE_REVIEWS;

public class PassValuesController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
                int selectedTheaterId = Integer.parseInt(request.getParameter("selectedTheaterId"));
                EntityManager em = EMF.createEntityManager();
                MovieSchedules ms = em.find(MovieSchedules.class,scheduleId);
                Theaters selectedTheater = em.find(Theaters.class,selectedTheaterId);
                Date formatDate = ms.getDate();
                String date = new SimpleDateFormat(CHECKOUT_TIME_FORMAT).format(formatDate);
                
                HttpSession session = request.getSession();
                session.setAttribute("checkoutDate",date);
                session.setAttribute("schedule", ms);
                session.setAttribute("numTickets",null);
                session.setAttribute("selectedTheater",selectedTheater);
                RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
                rd.forward(request, response);
    }
}
