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

public class MovieDetailsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int movieId = Integer.parseInt(request.getParameter("movieId"));
                EntityManager em = EMF.createEntityManager();
                HttpSession session = request.getSession(true);
  
                Movie movieInfo = em.find(Movie.class,movieId);
                Date formatDate = movieInfo.getReleaseDate();
                String date = new SimpleDateFormat(CHECKOUT_TIME_FORMAT).format(formatDate);
                session.setAttribute("MovieInfo_Date",date);
                session.setAttribute("MovieInfo", movieInfo);
                Account user = (Account)session.getAttribute("UserInfoSession");
                if(user!=null){
                    int userId=user.getId();
                    MovieFav userFaved = checkUserFav(em,userId,movieId);
                    if(userFaved==null){    
                        session.setAttribute("FavId", null);
                    }else{
                        int favId = userFaved.getId();
                        session.setAttribute("FavId",String.valueOf(favId));
                    }    
                }
                List<MovieReviews> reviewsResults = makeMovieReviewList(em,movieId);
                
                session.setAttribute("MovieReviewList", reviewsResults);
                /*view today tickets*/
                Date currentDate = getCurrentDate();
                String strCurrentDate = new SimpleDateFormat(CHECKOUT_TIME_FORMAT).format(currentDate);
                session.setAttribute("CurrentDate",strCurrentDate);
                List<MovieSchedules> movieSchedules =getMovieSchedule(em,movieId,currentDate);
                session.setAttribute("MovieScheduleList",movieSchedules);
                /*theater info*/
                int theaterId = DEFAULT_THEATER_ID;
                Theaters theater = em.find(Theaters.class,theaterId);
                session.setAttribute("TheaterInfo",theater);
                em.close(); 
                RequestDispatcher rd = request.getRequestDispatcher("movieDetails.jsp");
                rd.forward(request, response);  
               
    }   
    public MovieFav checkUserFav(EntityManager em,Integer userId,Integer movieId){
        MovieFav userFaved =null;
        TypedQuery<MovieFav> query = em.createNamedQuery("MovieFav.findByUserIdMovieId", MovieFav.class);
        query.setParameter("userId", userId);
        query.setParameter("movieId",movieId);
        List<MovieFav> movieFavResults = query.getResultList();
        if(!movieFavResults.isEmpty()){
            userFaved = movieFavResults.get(0);
        }
        return userFaved;
    }
    public List<MovieReviews> makeMovieReviewList(EntityManager em,Integer movieId){
        TypedQuery<MovieReviews> query = em.createNamedQuery("MovieReviews.findByMovieId", MovieReviews.class);
        query.setParameter("movieId", movieId);
        List<MovieReviews> movieReviewsResults = query.setMaxResults(DISPLAY_MOVIE_REVIEWS).getResultList();
        return movieReviewsResults;
    }
    public Date getCurrentDate(){
        //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //Calendar date = Calendar.getInstance();
        //String currentDate = format.format(date.getTime());
        Date currentDate = new Date();
        return currentDate;
    }
    public List<MovieSchedules> getMovieSchedule(EntityManager em,Integer movieId,Date currentDate){
        int theaterId = DEFAULT_THEATER_ID;
        int showingId = checkMovieStartandEndDates(em,movieId,theaterId,currentDate);
        if(showingId!=0){
            TypedQuery<MovieSchedules> query = em.createNamedQuery("MovieSchedules.findByMovieShwoingIdDate", MovieSchedules.class);
            query.setParameter("showingId", showingId);
            query.setParameter("date", currentDate);
            List<MovieSchedules> movieSchedulesResults=query.getResultList();
            List<MovieSchedules> schedulesList = checkTicketsLeft(movieSchedulesResults);
            return schedulesList;
        }
        return null;
    }
    public Integer checkMovieStartandEndDates(EntityManager em, Integer movieId, Integer theaterId,Date currentDate){
        TypedQuery<MovieShowings> query = em.createNamedQuery("MovieShowings.findByMovieIdTheaterId", MovieShowings.class);
        query.setParameter("movieId", movieId);
        query.setParameter("theaterId", theaterId);
        List<MovieShowings> movieShowingsResults=query.getResultList();
        if(movieShowingsResults.isEmpty()){
            System.out.println("no such movie in this theater");
        }else{
            MovieShowings movieShowing = movieShowingsResults.get(0);
            Date startDate = movieShowing.getPeriodStart();
            Date endDate = movieShowing.getPeriodEnd();
            if(currentDate.after(startDate) && currentDate.before(endDate)){
                return movieShowing.getId();
            }
        }
        return 0;
    }
    public List<MovieSchedules> checkTicketsLeft(List<MovieSchedules> movieSchedulesResults){
        List<MovieSchedules> schedulesList = new ArrayList<MovieSchedules>();
        for(int i=0;i<movieSchedulesResults.size();i++){
            if((movieSchedulesResults.get(i).getNumTicketsLeft())>0){
                schedulesList.add(movieSchedulesResults.get(i));
            }
        }
        return schedulesList;
    }
    
}

