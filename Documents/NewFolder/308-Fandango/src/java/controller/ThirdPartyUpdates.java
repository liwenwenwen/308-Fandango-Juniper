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
import entity.Payments;
import entity.TheaterFav;
import entity.ThirdPartyData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
import servlet.HashPassword;
import servlet.HttpConnection;
import static source.Constants.ADMIN_EMAIL;
import static source.Constants.THIRD_PARTY_URL;



public class ThirdPartyUpdates extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
                response.setContentType("text/html");
                HttpSession session = request.getSession(true);
                HttpConnection http = new HttpConnection();
                ArrayList<ThirdPartyData> thirdpartyData = new ArrayList<ThirdPartyData>();
                String url = THIRD_PARTY_URL;
                String movie = "";
                String theater = "";
                ArrayList<String> genres=new ArrayList<String>();
                String duration ="";
                String cover  = "";
                Date releaseDate;
                String rating ="";
                String description = "";
                ArrayList<String> schedule=new ArrayList<String>();
                try {
                    String strData = http.sendGet(url);
                    thirdpartyData = http.wrapThirdPartyData(strData);
                    System.out.println("size:"+thirdpartyData.size());
                    /*
                    for(int i=0;i<thirdpartyData.size();i++){
                        movie = thirdpartyData.get(i).getMovie();
                        theater = thirdpartyData.get(i).getTheater();
                        duration = thirdpartyData.get(i).getDuration();
                        cover = thirdpartyData.get(i).getCover();
                        releaseDate = thirdpartyData.get(i).getReleaseDate();
                        rating = thirdpartyData.get(i).getRating();
                        description = thirdpartyData.get(i).getDescription();
                        //System.out.println("result:"+movie+theater+duration+cover+releaseDate+rating+description);
                        genres = thirdpartyData.get(i).getGenres();
                        schedule = thirdpartyData.get(i).getSchedule();
                        for(int j=0;j<genres.size();j++){
                          //System.out.println(genres.get(j));
                        }
                        for(int k=0;k<schedule.size();k++){
                          //System.out.println(schedule.get(k));
                        }
                    }
                    */
                   String dataTitle = "[ "+thirdpartyData.get(0).getMovie()+" "+thirdpartyData.get(0).getRating()+" ] At [ "+thirdpartyData.get(0).getTheater()+" ]";
                   
                   session.setAttribute("ThirdPartyDateTitle",dataTitle);
                   ArrayList<String> movieTitleList = new ArrayList<String>();
                   for(int m=1;m<thirdpartyData.size();m++){
                       movie = thirdpartyData.get(m).getMovie();
                       movieTitleList.add(movie);
                   }
                   session.setAttribute("ThirdPartyDateBody",movieTitleList);
                   session.setAttribute("ThirdPartyDate",thirdpartyData);
                   session.setAttribute("ThirdPartyDateConfirmation",null);
                   RequestDispatcher rd = request.getRequestDispatcher("thirdPartyData.jsp");
                   rd.forward(request, response); 
                } catch (Exception ex) {
                    Logger.getLogger(ThirdPartyUpdates.class.getName()).log(Level.SEVERE, null, ex);
                }


	
    }
}