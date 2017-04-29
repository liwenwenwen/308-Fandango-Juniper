/*
 * A JAVA FILE TO SCRAP MOVIE DATA FROM FANDANGO - 2017
 * AND
 * STORE DATA INTO JSON FILE
 */
package source;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static source.Constants.FANDANGO_WRITE_READ;

/**
 *
 * @author liwenfan
 */

public class ScrapDataFandango2017 {
    /*
    public static void main(String[] args) throws IOException, ParseException, Exception{
       
        Document doc =Jsoup.connect("http://www.fandango.com/moviesintheaters").userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").timeout(12000).get();
        
        List<String> newMovieList = new ArrayList<String>();
        Elements div = doc.select("div.large-9");
        for(Element ul:div.select("div.movie-ls-group")){
            for(Element li : ul.select("ul")){
                for(Element link : li.select("a.visual-container")){
                    String url = link.attr("href");
                    
                    newMovieList.add(url);
                }
            }
        }
      scrapFandango(newMovieList);
      
    } 
    
    public static void scrapFandango(List<String> newMovieList) throws IOException{
        
        FileWriter file = new FileWriter(FANDANGO_WRITE_READ);
        JSONObject movieObject = new JSONObject();
        JSONArray movieObjectArray = new JSONArray();

        for(int i=0;i<newMovieList.size();i++){
            String title="";
            String cover="";
            String releaseDate="";
            String rating="";
            String duration="";
            String synopsis="";
            String genres="";
            List<String> genresList = new ArrayList<String>();
            
            Document sdoc =Jsoup.connect(newMovieList.get(i)).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").timeout(6000).get();
            /* title 
            Elements st = sdoc.select("div.large-12");
            title = st.select("h1.page-header a").text();
            /* release date 
            Elements sd = sdoc.select("ul.movie-detail-list");
            releaseDate = sd.select("li.movie-detail-release-date").text();
            /* cover 
            Elements sc = sdoc.select("div.movie-poster a img");
            cover = sc.attr("src"); 
            /* rating & duration 
            String ratingDuration = sd.select("li.movie-rating").text();
            List<String> ratingDurationList = Arrays.asList(ratingDuration.split(","));
            if(ratingDurationList.size()>=2){
                rating = ratingDurationList.get(0).replaceAll("\\s+","");
                duration = ratingDurationList.get(1).substring(1);
            }else if(ratingDurationList.size()==1){
                String temp = ratingDurationList.get(0);
                if((temp.indexOf("hr") >= 0)||(temp.indexOf("min") >= 0)){
                    duration = temp;
                    rating = null;
                }else{
                    rating = temp;
                    duration = null;
                }
            }else if(ratingDurationList.size()==0){
                rating = null;
                duration = null;
            }
            /* genres 
            genres = sd.select("li.movie-genre").text();
            genresList = Arrays.asList(genres.split("/"));
            /* synopsis 
            Elements ss = sdoc.select("div.movie-synopsis-content");
            synopsis = ss.select("span#SynopsisTextLabel").text();
            
            /*try to create json obj here 
            JSONObject obj = new JSONObject();
            obj.put("title", title);
            obj.put("cover", cover);
            obj.put("releaseDate", releaseDate);
            obj.put("rating", rating);
            System.out.println(i);
            obj.put("duration", duration);
            obj.put("synopsis", synopsis);
            JSONArray list = new JSONArray();
            for(int j=0;j<genresList.size();j++){
                list.add(genresList.get(j));
            }
            obj.put("genres", list);           
            movieObjectArray.add(obj);
        }
        
        movieObject.put("2017movies",movieObjectArray);
        
        String test1 = movieObject.toString();
        file.write(test1);

        file.close();
        
    }
*/
    
}
