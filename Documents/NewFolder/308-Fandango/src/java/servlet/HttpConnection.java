/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

/**
 *
 * @author liwenfan
 */
import entity.ThirdPartyData;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class HttpConnection{

	public static final String USER_AGENT = "Mozilla/5.0";

	// HTTP GET request
	public static String sendGet(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
                
		return response.toString();
	}
        
        public static ArrayList<ThirdPartyData> wrapThirdPartyData(String input){
            String h1="";
            String h2="";
            String img="";
            ArrayList<ThirdPartyData> data = new ArrayList<ThirdPartyData>();
            /* theater */
            String title = input.substring(input.indexOf("<title>")+7,input.indexOf("</title>"));
            String[] titleList = title.split("\\|");
            String month = titleList[0];
            String year = titleList[1];
            String theater = titleList[2];
            ThirdPartyData dataobjhead = new ThirdPartyData();
            dataobjhead.setMovie(month);
            dataobjhead.setRating(year);
            dataobjhead.setTheater(theater);
            data.add(dataobjhead);
            System.out.println(month +" "+year+" "+theater);
            /* movie*/
            for(int j=0;j<6;j++){
                ThirdPartyData dataobj = new ThirdPartyData();
                h1 = input.substring(input.indexOf("<h1>")+4,input.indexOf("</h1>"));
                input = input.substring(input.indexOf("</h1>")+5,input.length());
                img = input.substring(input.indexOf("<h1>")+4,input.indexOf("</h1>"));
                img =img.substring(img.indexOf("<img src=")+10,img.indexOf(">")-1);
                input = input.substring(input.indexOf("</h1>")+5,input.length());
                dataobj.setMovie(h1);
                dataobj.setCover(img);
                dataobj.setTheater(theater);
                for(int i=0;i<6;i++){
                    h2 = input.substring(input.indexOf("<h2>")+4,input.indexOf("</h2>"));
                    input = input.substring(input.indexOf("</h2>")+5,input.length());
                    if(i==0){
                        ArrayList<String> genreList = new ArrayList<String>();
                        for(String str:h2.split("\\|")){
                            genreList.add(str);
                        }
                        dataobj.setGenres(genreList);
                    }else if(i==1){
                        dataobj.setDuration(h2);
                    }else if(i==2){
                        Date date = null;
                        try{
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //d MMMM yyyy
                            date = format.parse(h2);
                        }catch (java.text.ParseException e){
                            date=null;
                        }
                        dataobj.setReleaseDate(date);
                    }else if(i==3){
                        dataobj.setRating(h2);
                    }else if(i==4){
                        dataobj.setDescription(h2);
                    }else if(i==5){
                        ArrayList<String> scheduleList = new ArrayList<String>();
                        for(String str:h2.split("\\|")){
                            scheduleList.add(str);
                        }
                        dataobj.setSchedule(scheduleList);
                    }
                  
                }
             data.add(dataobj);   
            }
            return data;
            
        }
        
}
