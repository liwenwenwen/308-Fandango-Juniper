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
import static source.Constants.DISPLAY_MOVIE_REVIEWS;


public class WriteReviewController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String reviewTitle = request.getParameter("viewTitle");
                String reviewBody=request.getParameter("viewBody");
		String strMovieId = request.getParameter("movieId");
		EntityManager em = EMF.createEntityManager();
                HttpSession userSession = request.getSession(true);
                HttpSession movieReviewSession = request.getSession(true);
                
		if(reviewTitle.isEmpty()){
                    RequestDispatcher rd = request.getRequestDispatcher("movieDetails.jsp");
                    rd.include(request, response);
		}else{
                    Account user = (Account)userSession.getAttribute("UserInfoSession");
                    String userName = user.getUserName();
                    int userId=user.getId();
                    int movieId = Integer.parseInt(strMovieId);
                    Movie movie = em.find(Movie.class, movieId);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    String submitDate = dateFormat.format(date);
                    MovieReviews userWrote = checkUserReview(em,userId,movieId);
                    if(userWrote==null){
                        MovieReviews newReview = new MovieReviews();
                        MovieReviews review = createOrUpdateMoviewReview(newReview,reviewBody,submitDate,reviewTitle,userName,user,movie);
                        em.getTransaction().begin();
                        em.persist(review);
                        em.getTransaction().commit();
                    }else{
                        MovieReviews updateReview = createOrUpdateMoviewReview(userWrote,reviewBody,submitDate,reviewTitle,userName,user,movie);
                        em.getTransaction().begin();
                        em.merge(updateReview);
                        em.getTransaction().commit();
                    }
                List<MovieReviews> reviewsResults = makeMovieReviewList(em,movieId);
                em.close();      
                movieReviewSession.setAttribute("MovieReviewList", reviewsResults);
                RequestDispatcher rd = request.getRequestDispatcher("movieDetails.jsp");
                rd.forward(request, response);
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
        List<MovieReviews> movieReviewsResults = query.setMaxResults(DISPLAY_MOVIE_REVIEWS).getResultList();
        return movieReviewsResults;
    }
}

