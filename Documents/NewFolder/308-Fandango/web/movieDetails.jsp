<%-- 
    Document   : movieDetails
    Created on : Apr 12, 2017, 5:46:36 PM
    Author     : liwenfan
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.MovieFav"%>
<%@page import="entity.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Movie | JUNIPER</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="apple-touch-icon" href="apple-touch-icon.png">
        <!-- Place favicon.ico in the root directory -->

        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/vendor/modernizr-2.8.3.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/site.css">
        
        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Luckiest+Guy|Permanent+Marker" rel="stylesheet">
        
        <!-- Page specific CSS links go here -->
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/userAccount.css">
        <link rel="stylesheet" href="css/movieview.css">
        
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    </head>
    <body>
      <!--[if lt IE 8]>
          <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
      <![endif]-->

      <!-- Add your site or application content here -->
      <div id="header" class="container">
          <a href="movies.jsp" id="logo">JUNIPER</a>
          <ul class="nav nav-pills ">
            <li>
              <form action="SearchController" method="post">
                <input id="search-bar" type="text" name="Search">
              </form>
            </li>
            <li class="dropdown">
              <a class="nav-btn dropdown-toggle" data-toggle="dropdown" href="#">Menu</a>
              <ul class="dropdown-menu nav-dropdown">
                <li><a href="movies.jsp">Movies</a></li>
                <li><a href="DisplayTheatersMainController">Theaters</a></li>
                <li><a href="news.html">News</a></li>
              </ul>
            </li>
            <!--<li><a class="nav-btn" href="#">Showtimes</a></li>-->
            <!--change button if user is logged in-->
            <c:choose>
                <c:when test="${UserInfoSession==null}">            
                    <li><a class="nav-btn" href="login.jsp">Login / Register</a></li>
                </c:when>
                <c:otherwise>
                    <li><a class="nav-btn" href="DisplayUserFavController">[${UserInfoSession.userName}] Account</a></li>
                    <li><a class="nav-btn" href="LogoutController"> Logout</a></li>
                </c:otherwise>        
            </c:choose>
            <!--change button if user is logged in-->
          </ul>
        </div>
      
      <!--Movie Details PART (using HTML Tabs-->
      <!-- movie title-->
      <div id="title-bg">
         <div id="title-div" class="container">
           <span id="page-title">${MovieInfo.title}</span>
           <!--Movie Fav Icon-->
           <c:choose>
                <c:when test="${UserInfoSession!=null && FavId!=null}">   
                    <a href="MovieFavController?method=get&favId=${FavId}&movieId=${MovieInfo.id}"><img src="img/heartOn.png" alt="isFaved"></a>
                </c:when>
                <c:when test="${UserInfoSession==null}">
                </c:when>
                <c:otherwise>
                     <a href="MovieFavController?method=get&movieId=${MovieInfo.id}"><img src="img/heartOff.png" alt="isNotFaved"></a>
                </c:otherwise>
            </c:choose>
            <!--Movie Fav Icon-->
           
         </div>
       </div>
       <div class="container">
           <div class="tab">
               <button class="tablinks" onclick="openInfo(event, 'overview')">Overview</button>
               <button class="tablinks" onclick="openInfo(event, 'reviews')">Movie Reviews</button>
               <button class="tablinks" onclick="openInfo(event, 'tickets')">Movie Times + Tickets</button>
            </div>
           <!--Movie Dashboard-->
            <div id="overview" class="tabcontent current" >
    
                <!--Display Basic Info-->
                <div id='parent_div_1'><img src="${MovieInfo.cover}" style="width: 100%; height: 100%" alt="cover"></div
                <div id='parent_div_2'>
                    <div class="parent_div_center">
                    <h1>${MovieInfo.releaseDate}</h1>
                    <h1>${MovieInfo.contentRating}</h1>
                    <h1>${MovieInfo.duration}</h1>
                    <h1>${MovieInfo.synopsis}</h1>
                   </div>
               
                </div>
            
           
           <!--Movie Reviews -->
            <div id="reviews" class="tabcontent reviewcontainer">
                <h3>Movie Reviews</h3>
                <!--Write Reviews Header-->
                <div id="part-bg"class="reviewheader">
                    TELL US WHAT YOU THINK !
                </div>
                <!--Write Reviews Creation-->
                <!--change button if user is logged in-->
           <c:choose>
             <c:when test="${UserInfoSession!=null}"> 
                <div class="reviewcontent">
                    
                    <center><form  action="WriteReviewController" method="post" style="margin-top: 20px">
                            <h1>Title:</h1><input class="text-input-md" type="text" name="viewTitle" maxlength="50" size="50">
                            <h1>Body:</h1><textarea class="text-input-md" type="text" rows="4" cols="50" name="viewBody" ></textarea>
                            <br>
                            <button class="btn nav-btn" type="submit" name="movieId" value="${MovieInfo.id}">Submit Review</button>
                    </form></center>
                </div>
             </c:when>
             <c:otherwise>
                <div class="reviewcontent">
                    <center><h1>Login To Write A Movie Review</h1></center>
                </div>
             </c:otherwise>
            </c:choose> 
            <!--Display movie Reviews-->
            <div class="review-border">
            <!--generate a movie list-->
            <c:forEach var="item" items="${MovieReviewList}" >
              
                <div class="reviewheader-yellow reviewTitle-bg">  
                    <span ><c:out value="${item.title}"/></span>
                </div>
                <div class="reviewTitle-author">
                    <span >By <c:out value="${item.userName}"/></span>
                    <span > Written<c:out value="${item.date}"/></span>
                </div>
                <div class="reviewTitle-body" 
                    <span ><c:out value="${item.body}"/></span>
                </div>
            </a>
            </c:forEach>
            <!--generate a movie list-->  
          </div>
                <!--Display movie Reviews end here-->
                   
            </div>
           <!--Movie Reviews -->
            <div id="tickets" class="tabcontent">
                
                <h3>Movie Times + Tickets</h3>
                <!--viewTimes 7days-->
                <div id="part-bg"class="reviewheader">
                    <span>${CurrentDate}</span>
                </div>
               <!--TAB BODY-->
               <div>
                    <c:forEach var="item" items="${MovieScheduleList}" >
                        <div class="reviewTitle-body">
                            <a href="PassValuesController?method=get&scheduleId=${item.id}">
                            <!--a href="checkout.jsp"-->
                            <span ><c:out value="${item.time}"/></span>
                            <span ><c:out value="${item.numTicketsLeft}"/></span>
                            </a>
                        </div>
                    </c:forEach>
               </div>    
               

            </div>
        </div>
        
        
       <!--User Account PART-->
       
      <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
     
      <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')</script>
      <script src="js/plugins.js"></script>
      <script src="js/main.js"></script>
      <script src="js/bootstrap.js"></script>
      <script src="js/userAccountJs.js"></script>
      <script src="js/movieReview.js"></script>
    </body>
</html>