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
import entity.MovieReviews;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import servlet.EMF;


public class WriteReviewController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String reviewTitle = request.getParameter("viewTitle");
                String reviewBody=request.getParameter("viewBody");
		String strMovieId = request.getParameter("movieId");
		
		if(reviewTitle.isEmpty()){
			RequestDispatcher rd = request.getRequestDispatcher("movieDetails.jsp");
			rd.include(request, response);
		}else{
                
                EntityManager em = EMF.createEntityManager();
                
                /*access session*/
                HttpSession session = request.getSession(false);
                Account user = (Account)session.getAttribute("UserInfoSession");
                int movieId = Integer.parseInt(strMovieId);
                Movie movie = em.find(Movie.class, movieId);
                String userName = user.getUserName();
                int userId=user.getId();
                /*create current timestamp*/
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String submitDate = dateFormat.format(date);
                
                /*check if user already has one*/
                MovieReviews userWrote = checkUserReview(em,userId,movieId);
                if(userWrote==null){
                    /*create movie view obj*/
                    MovieReviews newReview = new MovieReviews();
                    MovieReviews review = createOrUpdateMoviewReview(newReview,reviewBody,submitDate,reviewTitle,userName,user,movie);
                    em.getTransaction().begin();
                    em.persist(review);
                    em.getTransaction().commit();

                }else{
                    /*update the old review to new review*/
                    MovieReviews updateReview = createOrUpdateMoviewReview(userWrote,reviewBody,submitDate,reviewTitle,userName,user,movie);
                    em.getTransaction().begin();
                    em.merge(updateReview);
                    em.getTransaction().commit();
                }
                /*update movie reviews*/
                List<MovieReviews> reviewsResults = makeMovieReviewList(em,movieId);
                em.close();    
                /*create session here*/
                HttpSession movieReviewSession = request.getSession(false);
                movieReviewSession.setAttribute("MovieReviewList", reviewsResults);
                //RequestDispatcher rd = request.getRequestDispatcher("movieDetails.jsp");
                //rd.forward(request, response);
                response.sendRedirect("movieDetails.jsp");
              
		}
                
	}
        
        
    public MovieReviews createOrUpdateMoviewReview (MovieReviews review, String reviewBody,String submitDate,String reviewTitle, String userName,Account user,Movie movie){
        review.setBody(reviewBody);
        review.setDate(submitDate);
        review.setTitle(reviewTitle);
        review.setUserName(userName);
        review.setUserId(user);
        review.setMovieId(movie);
        return review;
    }
 
    public MovieReviews checkUserReview(EntityManager em,Integer userId,Integer movieId){
        MovieReviews userwrote =null;
        TypedQuery<MovieReviews> query = em.createNamedQuery("MovieReviews.findByUserIdMovieId", MovieReviews.class);
        query.setParameter("userId", userId);
        query.setParameter("movieId",movieId);
        List<MovieReviews> movieReviewResults = query.getResultList();
        if(movieReviewResults.isEmpty()){

        }else{
            userwrote = movieReviewResults.get(0);
        }
        return userwrote;
    }
    public List<MovieReviews> makeMovieReviewList(EntityManager em,Integer movieId){
        TypedQuery<MovieReviews> query = em.createNamedQuery("MovieReviews.findByMovieId", MovieReviews.class);
        query.setParameter("movieId", movieId);
        List<MovieReviews> movieReviewsResults = query.getResultList();
        return movieReviewsResults;
    }
}

