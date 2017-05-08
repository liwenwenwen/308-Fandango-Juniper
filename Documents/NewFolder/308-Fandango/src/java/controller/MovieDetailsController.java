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
import entity.MovieFav;
import entity.MovieReviews;
import entity.MovieSchedules;
import entity.MovieShowings;
import entity.TheaterFav;
import entity.Theaters;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
import static source.Constants.CHECKOUT_TIME_FORMAT;
import static source.Constants.DATE_TIME_FORMAT;
import static source.Constants.DEFAULT_THEATER_ID;
import static source.Constants.DISPLAY_MOVIE_REVIEWS;
import static source.Constants.ORDER_TIME_FORMAT;
import static source.Constants.TIME_24_FORMAT;

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
                List<Theaters> theaterInfoList = new ArrayList<Theaters>();
                List<List<MovieSchedules>> movieSchedules =getMovieSchedule(em,user,theaterInfoList,movieId,currentDate);
                session.setAttribute("MovieScheduleList",movieSchedules);
                
                    try {
                        List<List<Boolean>> passTime = checkSchedulesTimes(em,movieSchedules);
                        session.setAttribute("MovieScheduleTimeCheckList",passTime);
                    } catch (ParseException ex) {
                        Logger.getLogger(MovieDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                /*theater info*/
                session.setAttribute("TheaterInfo",theaterInfoList);
                /* moive gener*/
                List<String> genreList = getGenresList(em,movieId);
                session.setAttribute("ThisMovieGenres",genreList);
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
        Date currentDate = new Date();
        return currentDate;
    }
    public Time getCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat(TIME_24_FORMAT);
        Date date= new Date();
        Time currentTime = new Time(date.getTime());
        System.out.println("curr "+ currentTime);
        return currentTime;
    }
    public Time convertStringtoTime(String time) throws ParseException{
        SimpleDateFormat format2 = new SimpleDateFormat(ORDER_TIME_FORMAT);
        Date tempDate = getCurrentDate();
        String temp = format2.format(tempDate);
        String allTime = temp + ","+time;
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        /*format.setTimeZone(TimeZone.getTimeZone("GMT"));*/
        Date d1 =format.parse(allTime);
        Time tTime = new Time(d1.getTime());
        System.out.println("use "+ d1);
        return tTime;
    }
    public boolean compareTwoTimes(Date currentTime,Date tTime){
     
        if(currentTime.before(tTime)){
            System.out.println("1");
            return true;
        }else{
            System.out.println("2");
            return false;
        }
    }
    
    public List<List<MovieSchedules>> getMovieSchedule(EntityManager em,Account user,List<Theaters> theaterInfoList, Integer movieId,Date currentDate){
        List<TheaterFav> userTheaterFavList=new ArrayList<TheaterFav>();
        List<Integer> showingIdList = new ArrayList<Integer>();
        if(user!=null){
            TypedQuery<TheaterFav> query = em.createNamedQuery("TheaterFav.findByUserId", TheaterFav.class);
            query.setParameter("userId", user.getId());
            userTheaterFavList=query.getResultList();
        }
        int theaterId;
        int showingId;
        if(userTheaterFavList.isEmpty()){
            /* default - display all five theaters*/
            /*
            theaterId = DEFAULT_THEATER_ID;
            showingId = checkMovieStartandEndDates(em,movieId,theaterId,currentDate);
            showingIdList.add(showingId);
            theaterInfoList.add(em.find(Theaters.class,theaterId));
            */
            TypedQuery<Theaters> query = em.createNamedQuery("Theaters.findAll", Theaters.class);
            List<Theaters> allTheatersList=query.getResultList();
            for(int j=0;j<allTheatersList.size();j++){
                theaterId = allTheatersList.get(j).getId();
                showingId = checkMovieStartandEndDates(em,movieId,theaterId,currentDate);
                if(showingId!=0){
                    showingIdList.add(showingId);
                    theaterInfoList.add(em.find(Theaters.class,theaterId));
                }    
            }
        }else{
            /* user has faved theaters*/
            for(int i=0;i<userTheaterFavList.size();i++){
                theaterId = userTheaterFavList.get(i).getTheaterId().getId();
                showingId = checkMovieStartandEndDates(em,movieId,theaterId,currentDate);
                if(showingId!=0){
                    showingIdList.add(showingId);
                    theaterInfoList.add(em.find(Theaters.class,theaterId));
                }    
            }
        }
        
        
        if(!showingIdList.isEmpty()){
            List<List<MovieSchedules>> mainSchedulesList = new ArrayList<List<MovieSchedules>>();
            for(int j=0;j<showingIdList.size();j++){
                int showId = showingIdList.get(j);
                TypedQuery<MovieSchedules> query = em.createNamedQuery("MovieSchedules.findByMovieShwoingIdDate", MovieSchedules.class);
                query.setParameter("showingId", showId);
                query.setParameter("date", currentDate);
                List<MovieSchedules> movieSchedulesResults=query.getResultList();
                List<MovieSchedules> schedulesList = checkTicketsLeft(movieSchedulesResults);
                mainSchedulesList.add(schedulesList);
            }
            return mainSchedulesList;
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
    public List<String> getGenresList(EntityManager em, Integer movieId){
        List<String> strMovieGenres = new ArrayList<String>();
        TypedQuery<Genres> query = em.createNamedQuery("Genres.findByMovieId", Genres.class);
        query.setParameter("movieId", movieId);
        List<Genres> movieGenresResults=query.getResultList();
        System.out.println("here"+movieGenresResults.size());
        for(int i=0;i<movieGenresResults.size();i++){
            GenreNames genreNames = movieGenresResults.get(i).getGenreId();
            String mg = genreNames.getGenreName();
            strMovieGenres.add(mg);
        }
        return strMovieGenres;
    }
    public List<List<Boolean>> checkSchedulesTimes(EntityManager em, List<List<MovieSchedules>> movieSchedules) throws ParseException{
        if(movieSchedules!=null){
        List<List<Boolean>> passTime = new ArrayList<List<Boolean>>();
        Time currentTime = getCurrentTime();
        for(int i=0;i<movieSchedules.size();i++){
            List<Boolean> timeSet = new ArrayList<Boolean>();
            for(int j=0;j<movieSchedules.get(i).size();j++){
               String movieTime = movieSchedules.get(i).get(j).getTime();
               Time ttime = convertStringtoTime(movieTime);
               boolean checkTime = compareTwoTimes(currentTime,ttime);
               timeSet.add(checkTime);
               
            }
            passTime.add(timeSet);
        }
        return passTime;
    }else{
            return null;
        }
    }
}

