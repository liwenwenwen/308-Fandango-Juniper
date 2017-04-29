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
import entity.Movie;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
import static source.Constants.DISPLAY_MAIN_MOVIES;
import static source.Constants.MOVIE_DATE_FROM;
import static source.Constants.MOVIE_DATE_TO;
import static source.Constants.SUB_MOVIE_DATE_FROM;
import static source.Constants.SUB_MOVIE_DATE_TO;

public class DisplayMoviesMainController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
                HttpSession movieMainSession = request.getSession();               
                EntityManager em = EMF.createEntityManager();
                List<Movie> movieResults;
                List<Movie> movieSubResults;
                try {
                    movieResults = makeMovieMainList(em);
                    movieSubResults = makeMovieSubList(em);
                    em.close();
                    movieMainSession.setAttribute("MovieMainList", movieResults);
                    movieMainSession.setAttribute("MovieSubList", movieSubResults);
                } catch (ParseException ex) {
                    Logger.getLogger(DisplayMoviesMainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                           
    }
    public List<Movie> makeMovieMainList(EntityManager em) throws ParseException{
        TypedQuery<Movie> query = em.createNamedQuery("Movie.findByTwoDates", Movie.class);
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH); //d MMMM yyyy
        Date from = format.parse(MOVIE_DATE_FROM);
        Date to = format.parse(MOVIE_DATE_TO);
        query.setParameter("from",from);
        query.setParameter("to",to);
        List<Movie> movieResults = query.setMaxResults(DISPLAY_MAIN_MOVIES).getResultList();
        return movieResults;
    }
    public List<Movie> makeMovieSubList(EntityManager em) throws ParseException{
        TypedQuery<Movie> query = em.createNamedQuery("Movie.findByTwoDates", Movie.class);
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH); //d MMMM yyyy
        Date from = format.parse(SUB_MOVIE_DATE_FROM);
        Date to = format.parse(SUB_MOVIE_DATE_TO);
        query.setParameter("from",from);
        query.setParameter("to",to);
        List<Movie> movieSubResults = query.setMaxResults(DISPLAY_MAIN_MOVIES).getResultList();
        return movieSubResults;
    }
    
}