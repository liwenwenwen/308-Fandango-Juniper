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
public final class Constants {
    private Constants() {
    }
    /* original[0] loadMovie[1] loadShowings[2] loadSchedule[3]*/
    public static final int TRY_TO_LOAD = 0; 
    /* Some constants numbers*/
    public static final int DISPLAY_MAIN_MOVIES = 10;
    public static final int DISPLAY_MAIN_THEATERS =20;
    public static final int DISPLAY_SEARCH_RESULTS = 20;
    public static final int DISPLAY_MOVIE_REVIEWS = 10;
    public static final int DISPLAY_MOVIE_FAVED = 5;
    public static final int DISPLAY_PURCHASE_HISTORY=10;
    public static final int DEFAULT_THEATER_ID=1;    
    /* email basic settings*/
    public static final String EMAIL_FROM = "juniper.jmovies";
    public static final String EMAIL_PASSWORD = "AA247413090";
    public static final String EMAIL_SUBJECT = "[ Juniper ] - HERE IS YOUR ORDER DETAILS";
    /* file for scrap and others */
    public static final String FANDANGO_WRITE_READ = "/Users/liwenfan/Documents/NewFolder/308-Fandango/src/java/source/fandango-2017.json";
    public static final String IMDB_WRITE_READ = "/Users/liwenfan/Documents/NewFolder/308-Fandango/src/java/source/imdb-2016.json";
    public static final String SHOWINGS_WRITE_READ = "/Users/liwenfan/Documents/NewFolder/308-Fandango/src/java/source/April20-30Showings.json";
    /* email body settings*/
    public static final String EMAIL_TAB = "/n";
    public static final String EMAIL_SPACE = " ";
    public static final String EMAIL_HI = "HI ";
    public static final String EMAIL_ORDER = "Your Juniper Order # ";
    public static final String EMAIL_ORDER_DATE = "Order Date: ";
    public static final String EMAIL_DOLLER_SIGN = " $ ";
    public static final String EMAIL_PER_EACH = "/each";
    public static final String EMAIL_TOTAL = "Total: ";
    /* main page movie display */
    public static final String MOVIE_DATE_FROM = "April 15, 2017";
    public static final String MOVIE_DATE_TO = "April 30, 2017";
    public static final String SUB_MOVIE_DATE_FROM = "May 1, 2017";
    public static final String SUB_MOVIE_DATE_TO = "May 15, 2017";
    /* movie showing generate*/
    public static final String S_MOVIE_DATE_FROM = "April 20, 2017";
    public static final String S_MOVIE_DATE_TO = "April 30, 2017";
    public static final double MIN_UNIT_PRICE = 10.00;
    public static final double MAX_UNIT_PRICE = 20.00;
    public static final int MOVIE_PERIOD = 15;
    /* movie shedule generate*/
    public static final int MOVIE_SHOWING_ID_MIN = 1;
    public static final int MOVIE_SHOWING_ID_MAX = 265;
    public static final int MIN_SHOWING_TIMES = 2;
    public static final int MAX_SHOWING_TIMES = 4;
    public static final int MIN_TIME = 9;
    public static final int MAX_TIME = 13;
    /* time format*/
    public static final String CHECKOUT_TIME_FORMAT= "EEE, MMM dd yyyy";
}
