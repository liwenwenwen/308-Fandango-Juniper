/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import entity.GenreNames;
import entity.Genres;
import entity.Movie;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import servlet.EMF;
import static source.Constants.FANDANGO_WRITE_READ;
import static source.Constants.IMDB_WRITE_READ;

/**
 *
 * @author liwenfan
 */
public class GenerateGenres {
    public GenerateGenres(){}
    public static void GenerateGenres2017() throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        JSONObject a = (JSONObject) parser.parse(new FileReader(FANDANGO_WRITE_READ));
        JSONArray array = (JSONArray)a.get("2017movies"); //2017movies
        EntityManager em = EMF.createEntityManager();
        HashMap hm = new HashMap();
        int count=1;
        int count1=1;
        for(Object o : array){
            count1=count1+1;
            JSONObject newMovie = (JSONObject) o;
            String title = (String) newMovie.get("title");
            String duration = (String) newMovie.get("duration");
            /* find obj*/
            TypedQuery<Movie> query = em.createNamedQuery("Movie.findByTitleDuration", Movie.class);
            query.setParameter("title",title);
            query.setParameter("duration",duration);
            List<Movie> movieResults = query.getResultList();
            if(movieResults.isEmpty()){
            }else{
                Movie m = movieResults.get(0);
                JSONArray genres = (JSONArray) newMovie.get("genres");
                for (Object c : genres){
                    List<GenreNames> genreResults;
                    Genres genreobj = new Genres();
                    TypedQuery<GenreNames> query2 = em.createNamedQuery("GenreNames.findByGenreName", GenreNames.class);
                    query2.setParameter("genreName",(String)c);
                    genreResults = query2.getResultList();
                    if(genreResults.isEmpty()&& ((String)c).length()>1){
                        query2.setParameter("genreName",((String)c).substring(1));
                        genreResults = query2.getResultList();
                    }
                    if(!genreResults.isEmpty()){
                    GenreNames g = genreResults.get(0);
                    genreobj.setMovieId(m);
                    genreobj.setGenreId(g);
                    em.getTransaction().begin();
                    em.persist(genreobj);
                    em.getTransaction().commit();
                    }
                }
            }
        }
            
        em.close();

    }
    public static void GenerateGenres2016() throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        JSONObject a = (JSONObject) parser.parse(new FileReader(IMDB_WRITE_READ));
        JSONArray array = (JSONArray)a.get("2016movies"); //2017movies
        EntityManager em = EMF.createEntityManager();
        HashMap hm = new HashMap();
        int count=1;
        int count1=1;
        for(Object o : array){
            count1=count1+1;
            JSONObject newMovie = (JSONObject) o;
            String title = (String) newMovie.get("title");
            String duration = (String) newMovie.get("duration");
            /* find obj*/
            TypedQuery<Movie> query = em.createNamedQuery("Movie.findByTitleDuration", Movie.class);
            query.setParameter("title",title);
            query.setParameter("duration",duration);
            List<Movie> movieResults = query.getResultList();
            if(movieResults.isEmpty()){
            }else{
                Movie m = movieResults.get(0);
                JSONArray genres = (JSONArray) newMovie.get("genres");
                for (Object c : genres){
                    List<GenreNames> genreResults;
                    Genres genreobj = new Genres();
                    TypedQuery<GenreNames> query2 = em.createNamedQuery("GenreNames.findByGenreName", GenreNames.class);
                    query2.setParameter("genreName",(String)c);
                    genreResults = query2.getResultList();
                    if(genreResults.isEmpty()&& ((String)c).length()>1){
                        query2.setParameter("genreName",((String)c).substring(1));
                        genreResults = query2.getResultList();
                    }
                    if(!genreResults.isEmpty()){
                    GenreNames g = genreResults.get(0);
                    genreobj.setMovieId(m);
                    genreobj.setGenreId(g);
                    em.getTransaction().begin();
                    em.persist(genreobj);
                    em.getTransaction().commit();
                    }
                }
            }
        }
        System.out.println(count1);   
        em.close();

    }

}
