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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.json.simple.parser.ParseException;
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
            /* uncommend when needed 
            ReadDataFandango2017 importData = new ReadDataFandango2017();
            try {
                importData.getJsonData();
            } catch (ParseException ex) {
                Logger.getLogger(servletListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(servletListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
	}
}
