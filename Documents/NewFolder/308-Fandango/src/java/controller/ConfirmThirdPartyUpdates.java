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
import entity.GenreNames;
import entity.Genres;
import entity.Movie;
import entity.MovieSchedules;
import entity.MovieShowings;
import entity.Payments;
import entity.TheaterFav;
import entity.Theaters;
import entity.ThirdPartyData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
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
import servlet.HttpConnection;
import static source.Constants.ADMIN_EMAIL;
import static source.Constants.MAX_SHOWING_TIMES;
import static source.Constants.MAX_TIME;
import static source.Constants.MAX_UNIT_PRICE;
import static source.Constants.MIN_SHOWING_TIMES;
import static source.Constants.MIN_TIME;
import static source.Constants.MIN_UNIT_PRICE;
import static source.Constants.MOVIE_PERIOD;
import static source.Constants.THIRD_PARTY_URL;



public class ConfirmThirdPartyUpdates extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                response.setContentType("text/html");
                EntityManager em = EMF.createEntityManager();
                HttpSession session = request.getSession(true);
                String movie = "";
                String theater = "";
                ArrayList<String> genres=new ArrayList<String>();
                String duration ="";
                String cover  = "";
                Date releaseDate;
                String rating ="";
                String description = "";
                ArrayList<String> schedule=new ArrayList<String>();
                
                session.setAttribute("ThirdPartyDateConfirmation","true");
                ArrayList<ThirdPartyData> thirdpartyData = (ArrayList<ThirdPartyData>)session.getAttribute("ThirdPartyDate");
                for(int i=1;i<thirdpartyData.size();i++){
                        movie = thirdpartyData.get(i).getMovie();
                        theater = thirdpartyData.get(i).getTheater();
                        duration = thirdpartyData.get(i).getDuration();
                        cover = thirdpartyData.get(i).getCover();
                        releaseDate = thirdpartyData.get(i).getReleaseDate();
                        rating = thirdpartyData.get(i).getRating();
                        description = thirdpartyData.get(i).getDescription();
                        genres = thirdpartyData.get(i).getGenres();
                        schedule = thirdpartyData.get(i).getSchedule();
                        Movie newMovie = updateBasicMovieInfo(em,movie,duration,cover,releaseDate,rating,description);
                        updateGenres(em,genres,newMovie);
                        MovieShowings newShowing = updateMovieShowing(em,newMovie,theater);
                        updateMovieSchedule(em,newShowing,schedule);
                }
                em.close();
                RequestDispatcher rd = request.getRequestDispatcher("thirdPartyData.jsp");
                rd.forward(request, response); 
    }
    public Movie updateBasicMovieInfo(EntityManager em,String movie,String duration,String cover,Date releaseDate,String rating,String description){
        Movie newMovie = new Movie();
        newMovie.setContentRating(rating);
        newMovie.setCover(cover);
        newMovie.setDuration(duration);
        newMovie.setReleaseDate(releaseDate);
        newMovie.setSynopsis(description);
        newMovie.setTitle(movie);
        em.getTransaction().begin();
        em.persist(newMovie);
        em.getTransaction().commit();
        return newMovie;
    }
    public void updateGenres(EntityManager em,ArrayList<String> genres,Movie newMovie){
        for(int i=0;i<genres.size();i++){
            String genresName = genres.get(i);
            TypedQuery<GenreNames> query = em.createNamedQuery("GenreNames.findByGenreName", GenreNames.class);
            query.setParameter("genreName", genresName);
            List<GenreNames> theaterFavtemp = query.getResultList();
            if(!theaterFavtemp.isEmpty()){
                GenreNames gname =  theaterFavtemp.get(0);
            
                Genres newGenre = new Genres();
                newGenre.setGenreId(gname);
                newGenre.setMovieId(newMovie);
                em.getTransaction().begin();
                em.persist(newGenre);
                em.getTransaction().commit();
            }
        }
    }
    public MovieShowings updateMovieShowing(EntityManager em,Movie newMovie,String theater){
        TypedQuery<Theaters> query = em.createNamedQuery("Theaters.findByName", Theaters.class);
        query.setParameter("name", theater);
        Theaters thea = query.getResultList().get(0);
        double max = MIN_UNIT_PRICE;
        double min = MAX_UNIT_PRICE;
        /* unit price */
        double range = (max - min) + 1;     
        double unitPrice = (double)(Math.random() * range) + min;
        /* start date */
        Date periodStart = newMovie.getReleaseDate();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(periodStart);
        calendar.add(Calendar.DATE, MOVIE_PERIOD);
        Date periodEnd = calendar.getTime();
        /* new showing obj*/
        MovieShowings newShowing = new MovieShowings();
        newShowing.setPeriodStart(periodStart);
        newShowing.setPeriodEnd(periodEnd);
        newShowing.setSmovieId(newMovie);
        newShowing.setStheaterId(thea);
        newShowing.setUnitPrice((long)unitPrice);
        em.getTransaction().begin();
        em.persist(newShowing);
        em.getTransaction().commit();
        return newShowing;
    }
    public void updateMovieSchedule(EntityManager em,MovieShowings newShowing,ArrayList<String> schedule){
            /* start date */
            Date periodStart = newShowing.getPeriodStart();
            /* dates*/
            for(int j=0; j<16;j++){
                //total 16, from start 15
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(periodStart);
                calendar.add(Calendar.DATE, j);
                Date date = calendar.getTime(); 
                for(int k=0;k<schedule.size();k++){
                    MovieSchedules newSchedules = new MovieSchedules();
                    newSchedules.setShowingId(newShowing);
                    newSchedules.setDate(date);
                    newSchedules.setTime(schedule.get(k));
                    newSchedules.setNumTicketsLeft(100);
                    em.getTransaction().begin();
                    em.persist(newSchedules);
                    em.getTransaction().commit();
                }
            }   
        }
    

    
}