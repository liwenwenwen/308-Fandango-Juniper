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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.json.simple.parser.ParseException;
import static source.Constants.TRY_TO_LOAD;
import source.GenerateSchedules;
import source.GenerateShowings;
import source.ReadDataFandango2017;


public class servletListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
            System.out.println("ServletContextListener destroyed");
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
            System.out.println("ServletContextListener started");
            int i = TRY_TO_LOAD;
            if(i==1){
                /*  write movie json into DB */
                loadMovieJson();
            }else if(i==2){
                try {
                    /* add movie showings into DB */
                    addShowings();
                } catch (IOException ex) {
                    Logger.getLogger(servletListener.class.getName()).log(Level.SEVERE, null, ex);
                } catch (java.text.ParseException ex) {
                    Logger.getLogger(servletListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(i==3){
                /* add movie schedules into DB*/
                addschedules();
            }
            
	}
        
	public void loadMovieJson(){
            ReadDataFandango2017 importData = new ReadDataFandango2017();
            try {
                importData.getJsonData();
            } catch (ParseException ex) {
                Logger.getLogger(servletListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(servletListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public void addShowings() throws IOException, java.text.ParseException{
            GenerateShowings importData = new GenerateShowings();
            importData.generateExistMovieShowings();         
        }
        public void addschedules(){
            GenerateSchedules importData = new GenerateSchedules();
            importData.generateExistMovieSchedules();
            
        }
}
