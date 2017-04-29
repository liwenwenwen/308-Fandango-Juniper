/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import entity.Movie;
import entity.MovieShowings;
import entity.Theaters;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import servlet.EMF;
import static source.Constants.DISPLAY_MAIN_MOVIES;
import static source.Constants.MAX_UNIT_PRICE;
import static source.Constants.MIN_UNIT_PRICE;
import static source.Constants.MOVIE_DATE_FROM;
import static source.Constants.MOVIE_DATE_TO;
import static source.Constants.MOVIE_PERIOD;
import static source.Constants.SHOWINGS_WRITE_READ;
import static source.Constants.S_MOVIE_DATE_FROM;
import static source.Constants.S_MOVIE_DATE_TO;

/**
 *
 * @author liwenfan
 */
public class GenerateShowings {
    public GenerateShowings(){}
    
    public static void generateExistMovieShowings() throws ParseException, IOException{
        EntityManager em = EMF.createEntityManager();
        TypedQuery<Movie> mquery = em.createNamedQuery("Movie.findByTwoDates", Movie.class);
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH); //d MMMM yyyy
        Date from = format.parse(S_MOVIE_DATE_FROM);
        Date to = format.parse(S_MOVIE_DATE_TO);
        mquery.setParameter("from",from);
        mquery.setParameter("to",to);
        List<Movie> movieResults = mquery.getResultList();
        TypedQuery<Theaters> tquery = em.createNamedQuery("Theaters.findAll", Theaters.class);
        List<Theaters> theaterResults = tquery.getResultList();
        System.out.println("m: "+movieResults.size()+"t: "+theaterResults.size());
        
        double max = MIN_UNIT_PRICE;
        double min = MAX_UNIT_PRICE;
        
        
        for(int i=0;i<movieResults.size();i++){
            /* unit price */
            double range = (max - min) + 1;     
            double unitPrice = (double)(Math.random() * range) + min;
            /* start date */
            Date periodStart = movieResults.get(i).getReleaseDate();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(periodStart);
            calendar.add(Calendar.DATE, MOVIE_PERIOD);
            Date periodEnd = calendar.getTime();
            
            for(int j=0;j<theaterResults.size();j++){
                MovieShowings newShowing = new MovieShowings();
                newShowing.setPeriodStart(periodStart);
                newShowing.setPeriodEnd(periodEnd);
                newShowing.setSmovieId(movieResults.get(i));
                newShowing.setStheaterId(theaterResults.get(j));
                newShowing.setUnitPrice((long)unitPrice);
                em.getTransaction().begin();
                em.persist(newShowing);
                em.getTransaction().commit();
            }
            

        }
    }
    
}
