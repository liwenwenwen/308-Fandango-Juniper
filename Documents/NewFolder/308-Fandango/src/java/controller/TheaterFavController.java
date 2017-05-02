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
import entity.Account;
import entity.Movie;
import entity.MovieFav;
import entity.TheaterFav;
import entity.Theaters;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.EMF;
public class TheaterFavController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
                int theaterId = Integer.parseInt(request.getParameter("theaterId")); 
                EntityManager em = EMF.createEntityManager();
                HttpSession session = request.getSession(true);
                
                Account user = (Account)session.getAttribute("UserInfoSession");
                TheaterFav favedResult = findUserFavedTheater(em,theaterId,user);
                
                if(favedResult!=null){                  
                    removeFav(em,favedResult);
                }else{
                    addFav(em,user,theaterId);
                }
                /* update the list */
                List<String> theaterFav = checkUserTheaterFav(em,user);
                session.setAttribute("TheaterFavList", theaterFav);
                em.close();                
                RequestDispatcher rd = request.getRequestDispatcher("theaters.jsp");
                rd.forward(request, response);
    }
    public TheaterFav findUserFavedTheater(EntityManager em,int theaterId,Account user){
       TypedQuery<TheaterFav> query = em.createNamedQuery("TheaterFav.findByUserIdTheaterId", TheaterFav.class);
       int userId = user.getId();
       query.setParameter("theaterId", theaterId);
       query.setParameter("userId",userId);
       List<TheaterFav> theaterTemp = query.getResultList();
       TheaterFav theaterFavResult = new TheaterFav();
       if(!theaterTemp.isEmpty()){
            theaterFavResult = theaterTemp.get(0);
       }else{
           theaterFavResult=null;
       }
       return theaterFavResult;
    }
    public void removeFav(EntityManager em, TheaterFav favedResult){
        em.getTransaction().begin();
        em.remove(favedResult);
        em.getTransaction().commit();
    }
    public void addFav(EntityManager em,Account user,int theaterId){
        TheaterFav fav = new TheaterFav();
        Theaters th = em.find(Theaters.class,theaterId);
        fav.setTheaterId(th);
        fav.setUserId(user);
        em.getTransaction().begin();
        em.persist(fav);
        em.getTransaction().commit();
    }
    public List<String> checkUserTheaterFav(EntityManager em,Account loginUser){
       TypedQuery<TheaterFav> query = em.createNamedQuery("TheaterFav.findByUserId", TheaterFav.class);
       int userId = loginUser.getId();
       query.setParameter("userId", userId);
       List<TheaterFav> theaterFavtemp = query.getResultList();
       List<String> theaterFav = new ArrayList<String>();
       for(int i=0;i<5;i++){
           theaterFav.add("TNF");
           for(int j=0;j<theaterFavtemp.size();j++){
               if(theaterFavtemp.get(j).getTheaterId().getId()==(i+1)){
                   theaterFav.set(i,"TF");
               }
           }
       }
       return theaterFav;
    }
}