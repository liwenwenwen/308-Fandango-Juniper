/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author liwenfan
 */
import entity.GenreNames;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import servlet.EMF;
import static source.Constants.FANDANGO_WRITE_READ;
import static source.Constants.IMDB_WRITE_READ;

public class ReadDataForGenres {
    public ReadDataForGenres(){};
    
    public static void getJsonDataForGenreTypes() throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        JSONObject a = (JSONObject) parser.parse(new FileReader(FANDANGO_WRITE_READ));
        JSONArray array = (JSONArray)a.get("2017movies"); //2017movies
        EntityManager em = EMF.createEntityManager();
        String newGenre;
        HashMap hm = new HashMap();
        int count=1;
        int count1=1;
        for(Object o : array){
            count1=count1+1;
            JSONObject newMovie = (JSONObject) o;
            JSONArray genres = (JSONArray) newMovie.get("genres");
            for (Object c : genres){
                count=count+1;
                newGenre = (String) c;               
                    if (!hm.containsKey(newGenre)) {
                        hm.put(newGenre, 1);
                    } else {
                        hm.put(newGenre, (Integer) hm.get(newGenre) + 1);
                    }
            }
        }
        for (Object gname : hm.keySet()){
            GenreNames genre = new GenreNames();
            genre.setGenreName((String)gname);
            genre.setFrequency((Integer) hm.get(gname));
            em.getTransaction().begin();
            em.persist(genre);
            em.getTransaction().commit();
        }
        em.close();

    }
    public static void getJsonDataForGenreTypes2() throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        JSONObject a = (JSONObject) parser.parse(new FileReader(IMDB_WRITE_READ));
        JSONArray array = (JSONArray)a.get("2016movies");
        EntityManager em = EMF.createEntityManager();
        String newGenre;
        HashMap hm = new HashMap();
        int count=1;
        int count1=1;
        for(Object o : array){
            count1=count1+1;
            JSONObject newMovie = (JSONObject) o;
            JSONArray genres = (JSONArray) newMovie.get("genres");
            for (Object c : genres){
                count=count+1;
                newGenre = (String) c;               
                    if (!hm.containsKey(newGenre)) {
                        hm.put(newGenre, 1);
                    } else {
                        hm.put(newGenre, (Integer) hm.get(newGenre) + 1);
                    }
            }
        }
        for (Object gname : hm.keySet()){
            List<GenreNames> genreResults;
            TypedQuery<GenreNames> query = em.createNamedQuery("GenreNames.findByGenreName", GenreNames.class);
            query.setParameter("genreName",(String)gname);
            genreResults = query.getResultList();
            if(genreResults.isEmpty()){
                query.setParameter("genreName",((String)gname).substring(1));
                genreResults = query.getResultList();
            }
            if(genreResults.isEmpty()){
                GenreNames genre = new GenreNames();
                genre.setGenreName((String)gname);
                genre.setFrequency((Integer) hm.get(gname));
                em.getTransaction().begin();
                em.persist(genre);
                em.getTransaction().commit();
            }else{
                GenreNames oldgenre = genreResults.get(0);
                oldgenre.setFrequency((oldgenre.getFrequency())+((Integer) hm.get(gname)));
                em.getTransaction().begin();
                em.merge(oldgenre);
                em.getTransaction().commit();
            }
        }
        em.close();

    }
}
