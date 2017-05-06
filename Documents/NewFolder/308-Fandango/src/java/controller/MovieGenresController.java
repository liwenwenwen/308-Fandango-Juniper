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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
import static source.Constants.DISPLAY_GENRE_RESULTS;
import static source.Constants.DISPLAY_SEARCH_RESULTS;
import static source.Constants.G_MOVIE_DATE_FROM;
import static source.Constants.G_MOVIE_DATE_TO;
import static source.Constants.SUB_MOVIE_DATE_FROM;
import static source.Constants.SUB_MOVIE_DATE_TO;



public class MovieGenresController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String selectedGenre = request.getParameter("genre");
                EntityManager em = EMF.createEntityManager();
                HttpSession session = request.getSession();

                session.setAttribute("GenreName", selectedGenre);
                List<Movie> searchedGenreMovies = new ArrayList<Movie>();
                try {
                    searchedGenreMovies = getGenreResults(em,selectedGenre);
                } catch (ParseException ex) {
                    Logger.getLogger(MovieGenresController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(searchedGenreMovies.isEmpty()){
                    session.setAttribute("GenreResult", null);
                }else{
                    session.setAttribute("GenreResult", searchedGenreMovies);                   
                }
                RequestDispatcher rd = request.getRequestDispatcher("movieGenres.jsp");
                rd.forward(request, response);    
    }  
    public List<Movie> getGenreResults(EntityManager em,String selectedGenre) throws ParseException{
       
       DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH); //d MMMM yyyy
       Date from = format.parse(G_MOVIE_DATE_FROM);
       Date to = format.parse(G_MOVIE_DATE_TO);
       TypedQuery<Genres> query = em.createNamedQuery("Genres.findByGenreName", Genres.class);
       query.setParameter("genreName",selectedGenre);
       query.setParameter("from",from);
       query.setParameter("to",to); 
       List<Genres> searchedGenreTemp = query.setMaxResults(DISPLAY_GENRE_RESULTS).getResultList();
       List<Movie> searchedGenre = new ArrayList<Movie>();
       for(int i=0;i<searchedGenreTemp.size();i++){
           Movie m = searchedGenreTemp.get(i).getMovieId();
           searchedGenre.add(m);
       }
       return searchedGenre;
    }

                
}
  
