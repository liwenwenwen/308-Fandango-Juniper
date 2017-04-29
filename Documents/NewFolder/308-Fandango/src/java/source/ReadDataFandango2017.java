/*
 * A JAVA FILE TO READ THE JSON FILE INTO DATABASE (MYSQL)
 * RUN BY SERVLET CONTEXT LISTENER (WOULD BE RAN WHEN SERVER STARS)
 */
package source;


import entity.Movie;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import servlet.EMF;
import static source.Constants.FANDANGO_WRITE_READ;

/**
 *
 * @author liwenfan
 */
public class ReadDataFandango2017 {
    
    public ReadDataFandango2017(){}
   
    public static void getJsonData() throws FileNotFoundException, IOException, ParseException, Exception{
        JSONParser parser = new JSONParser();
        JSONObject a = (JSONObject) parser.parse(new FileReader(FANDANGO_WRITE_READ));
        JSONArray array = (JSONArray)a.get("2017movies"); //2017movies
        EntityManager em = EMF.createEntityManager();
       
        for(Object o : array){

            JSONObject newMovie = (JSONObject) o;
            Date date = null;
            String title = (String) newMovie.get("title");
            String cover = (String) newMovie.get("cover");
            String releaseDate = (String) newMovie.get("releaseDate");
            try{
                DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH); //d MMMM yyyy
                date = format.parse(releaseDate);
            }catch (java.text.ParseException e){
                date=null;
            }
            
            String rating = (String) newMovie.get("rating");
            String duration = (String) newMovie.get("duration");
            String synopsis = (String) newMovie.get("synopsis");
            JSONArray genres = (JSONArray) newMovie.get("genres");
            /*
            for (Object c : genres){}
            */
            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setCover(cover);
            movie.setReleaseDate(date);
            movie.setContentRating(rating);
            movie.setDuration(duration);
            movie.setSynopsis(synopsis);
  
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            
           
        }
        em.close();
    }
    
}
