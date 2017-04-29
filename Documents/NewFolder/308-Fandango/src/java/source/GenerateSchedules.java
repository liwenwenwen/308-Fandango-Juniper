/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import entity.Movie;
import entity.MovieSchedules;
import entity.MovieShowings;
import entity.Theaters;
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
import static source.Constants.MAX_SHOWING_TIMES;
import static source.Constants.MAX_TIME;
import static source.Constants.MAX_UNIT_PRICE;
import static source.Constants.MIN_SHOWING_TIMES;
import static source.Constants.MIN_TIME;
import static source.Constants.MIN_UNIT_PRICE;
import static source.Constants.MOVIE_PERIOD;
import static source.Constants.MOVIE_SHOWING_ID_MAX;
import static source.Constants.MOVIE_SHOWING_ID_MIN;
import static source.Constants.S_MOVIE_DATE_FROM;
import static source.Constants.S_MOVIE_DATE_TO;

/**
 *
 * @author liwenfan
 */
public class GenerateSchedules {
    public GenerateSchedules(){}
    
    public static void generateExistMovieSchedules(){
        EntityManager em = EMF.createEntityManager();
        TypedQuery<MovieShowings> squery = em.createNamedQuery("MovieShowings.findByTwoIDs", MovieShowings.class);
        squery.setParameter("min",MOVIE_SHOWING_ID_MIN);
        squery.setParameter("max",MOVIE_SHOWING_ID_MAX);
        List<MovieShowings> scheduleResults = squery.getResultList();
        
        int max = MIN_SHOWING_TIMES;
        int min = MAX_SHOWING_TIMES;
        
        int tmax = MIN_TIME;
        int tmin = MAX_TIME;
        
        for(int i=0;i<scheduleResults.size();i++){
            /* get how many times of showings per day*/
            int range = (max - min) + 1;     
            int showingTimes = (int)(Math.random() * range) + min;
            /*start time */
            int range2 = (tmax - tmin) + 1;     
            int timeStart = (int)(Math.random() * range2) + tmin;
            int time = timeStart;
            /* start date */
            Date periodStart = scheduleResults.get(i).getPeriodStart();
            /* showing */
            MovieShowings ms = scheduleResults.get(i);
            /* dates*/
            for(int j=0; j<16;j++){
                //total 16, from start 15
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(periodStart);
                calendar.add(Calendar.DATE, j);
                Date date = calendar.getTime(); 
                /* times */
                for(int k=0;k<showingTimes;k++){
                    String strTime = Integer.toString(time);
                    strTime = strTime+":00";
                    MovieSchedules newSchedules = new MovieSchedules();
                    newSchedules.setShowingId(ms);
                    newSchedules.setDate(date);
                    newSchedules.setTime(strTime);
                    newSchedules.setNumTicketsLeft(100);
                    em.getTransaction().begin();
                    em.persist(newSchedules);
                    em.getTransaction().commit();
                    time =time+3;
                }
            }
            
           
        }
    }
    
}
