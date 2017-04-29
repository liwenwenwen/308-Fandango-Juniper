/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import static source.Constants.IMDB_WRITE_READ;

/**
 *
 * @author liwenfan
 */
public class ScrapDataImdb2016 {
    /*
    public static void main(String[] args) throws IOException, InterruptedException{
        List<String> newMovieList = new ArrayList<String>();
        int max = 4000;
        int min = 2000;
        for(int i=1; i<17;i++){            
            Document doc =Jsoup.connect("http://www.imdb.com/search/title?year=2016,2016&title_type=feature&sort=moviemeter,asc&page="+i+"&ref_=adv_prv").userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").timeout(6000).get();
            Elements div = doc.select("div.lister-list");
            for(Element ul:div.select("div.lister-item div.lister-item-content h3")){
                for(Element link : ul.select("a")){
                    String url = link.attr("href");
                    newMovieList.add(url);
                }
            }
            int range = (max - min) + 1;     
            int time = (int)(Math.random() * range) + min;
            Thread.sleep(time);
        }
        scrapImdb(newMovieList);
    }
     public static void scrapImdb(List<String> newMovieList) throws IOException, InterruptedException{
        
        FileWriter file = new FileWriter(IMDB_WRITE_READ);
        JSONObject movieObject = new JSONObject();
        JSONArray movieObjectArray = new JSONArray();
        int max = 1500;
        int min = 1000;
        for(int i=0;i<newMovieList.size();i++){
            int range = (max - min) + 1;     
            int time = (int)(Math.random() * range) + min;
            Thread.sleep(time);
            
            String title="";
            String cover="";
            String releaseDate="";
            String rating="";
            String duration="";
            String synopsis="";
            String genres="";
            String s="";
            List<String> genresList = new ArrayList<String>();
            
            Document sdoc =Jsoup.connect("http://www.imdb.com"+newMovieList.get(i)).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").timeout(6000).get();
            /* title 
            Elements st = sdoc.select("div.title_wrapper");
            String title1 = st.select("h1").text();
            title = title1.replace("(2016)","");
            
            /* rating/duration/genres/release date 
            Elements sd = sdoc.select("div.title_wrapper");
            String comboString = sd.select(" div.subtext").text();
            List<String> comboStringList = Arrays.asList(comboString.split("\\|"));
            for(int j=0;j<comboStringList.size();j++){
                String temp = comboStringList.get(j);
                
                if((temp.indexOf("(") >= 0)||(temp.indexOf(")") >= 0)){
                    s=(comboStringList.get(j)).replace("(USA)", "");  
                    releaseDate=s.substring(1, s.length());
                }else if((temp.indexOf("h ") >= 0)||(temp.indexOf("min") >= 0)&&(temp.indexOf("March")<=0)){
                    s=comboStringList.get(j);
                    duration=s.substring(0, s.length()-1);
                }else if(temp.equals("G ")||temp.equals("PG-13 ") ||temp.equals("PG ")||temp.equals("R ")||temp.equals("NC-17 ")){
                    s=comboStringList.get(j);
                    rating=s.substring(0, s.length()-1);
                }else{
                    s=comboStringList.get(j);
                    genres=s.substring(0, s.length()-1);
                    genresList = Arrays.asList(genres.split(", "));
                }
                
            }
            /*
            System.out.println("releaseDate "+releaseDate);
            System.out.println("duration "+duration);
            System.out.println("rating "+rating);
            System.out.println("genres "+genres);
            */
            /* cover 
            Elements sc = sdoc.select("div.poster a img");
            cover = sc.attr("src"); 
            /* synopsis 
            Elements ss = sdoc.select("div.plot_summary");
            synopsis = ss.select("div.summary_text").text();
            
            
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
        
        movieObject.put("2016movies",movieObjectArray);
        
        String test1 = movieObject.toString();
        file.write(test1);

        file.close();
    
     }   
*/
}
