/*
 * A JAVA FILE TO READ THE JSON FILE INTO DATABASE (MYSQL)
 * RUN BY SERVLET CONTEXT LISTENER (WOULD BE RAN WHEN SERVER STARS)
 */
package source;


import entity.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

/**
 *
 * @author liwenfan
 */
public class ReadDataFandango2017 {
    
    public ReadDataFandango2017(){}
   
    public static void getJsonData() throws FileNotFoundException, IOException, ParseException, Exception{
        JSONParser parser = new JSONParser();
        JSONObject a = (JSONObject) parser.parse(new FileReader("/Users/liwenfan/Documents/NewFolder/308-Fandango/src/java/source/fandango-2017.json"));
        JSONArray array = (JSONArray)a.get("2017movies");
        EntityManager em = EMF.createEntityManager();
       
        for(Object o : array){

            JSONObject newMovie = (JSONObject) o;

            String title = (String) newMovie.get("title");
            String cover = (String) newMovie.get("cover");
            String releaseDate = (String) newMovie.get("releaseDate");
            String rating = (String) newMovie.get("rating");
            String duration = (String) newMovie.get("duration");
            String synopsis = (String) newMovie.get("synopsis");
            JSONArray genres = (JSONArray) newMovie.get("genres");
            /*
            for (Object c : genres){}
            */
            Test test = new Test();
            test.setTitle(title);
            test.setCover(cover);
            test.setReleaseDate(releaseDate);
            test.setContentRating(rating);
            test.setDuration(duration);
            test.setSynopsis(synopsis);
  
            em.getTransaction().begin();
            em.persist(test);
            em.getTransaction().commit();

           
        }
        em.close();
    }
    
}
