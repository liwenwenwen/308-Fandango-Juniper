<%-- 
    Document   : movieDetails
    Created on : Apr 12, 2017, 5:46:36 PM
    Author     : liwenfan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.MovieFav"%>
<%@page import="entity.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Movie | ELM</title>
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
          <a href="#" id="logo">ELM:Stay Frosty</a>
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
                <li><a href="actors.html">Actors</a></li>
                <li><a href="news.html">News</a></li>
              </ul>
            </li>
            <!--<li><a class="nav-btn" href="#">Showtimes</a></li>-->
            <!--change button if user is logged in-->
            <%
                Account checkUser = (Account)request.getSession().getAttribute("UserInfoSession");
                if(checkUser==null){
            %>
            <li><a class="nav-btn" href="login.jsp">Login / Register</a></li>
            <%
                }else{
            %>
            <li><a class="nav-btn" href="<%=request.getContextPath()%>/DisplayUserFavController">[${UserInfoSession.userName}] Account</a></li>
            
            <li><a class="nav-btn" href="<%=request.getContextPath()%>/LogoutController"> Logout</a></li>
            
            <%
                }
            %>
            <!--change button if user is logged in-->
          </ul>
        </div>
      
      <!--Movie Details PART (using HTML Tabs-->
      <!-- movie title-->
      <div id="title-bg">
         <div id="title-div" class="container">
           <span id="page-title">${MovieInfo.title}</span>
           <!--Movie Fav Icon-->
           
           <%
                String checkMovieFav = (String)request.getSession().getAttribute("FavId");
                if((checkUser!=null)&&(checkMovieFav!=null)){
            %>
            
            <a href="MovieFavController?method=get&favId=${FavId}&movieId=${MovieInfo.id}"><img src="img/heartOn.png" alt="isFaved"></a>
           <%
               }else if(checkUser==null){
               }else{
           %>
           
            <a href="MovieFavController?method=get&movieId=${MovieInfo.id}"><img src="img/heartOff.png" alt="isNotFaved"></a>
           <%
               }
           %>
           
           <!--Movie Fav Icon-->
           
         </div>
       </div>
       <div class="container">
           <div class="tab">
               <button class="tablinks" onclick="openInfo(event, 'dashboard')"><h1>Overview</h1></button>
               <button class="tablinks" onclick="openInfo(event, 'setting')"><h1>Synopsis</h1></button>
               <button class="tablinks" onclick="openInfo(event, 'emailp')"><h1>Movie Reviews</h1></button>
               <button class="tablinks" onclick="openInfo(event, 'history')"><h1>Movie Times + Tickets</h1></button>
            </div>
           <!--Movie Dashboard-->
            <div id="dashboard" class="tabcontent current" >
    
                <!--Display Basic Info-->
                <div class='parent_div_1'><img src="${MovieInfo.cover}" alt="cover"></div>
                <div class='parent_div_2'>
                    <div class="parent_div_center">
                    <h1>${MovieInfo.releaseDate}</h1>
                    <h1>${MovieInfo.contentRating}</h1>
                    <h1>${MovieInfo.duration1}</h1>
                    <h1>${MovieInfo.genres1} ${MovieInfo.genres2} ${MovieInfo.genres3} ${MovieInfo.genres4}</h1>
                    <a href="${MovieInfo.imdbLink}"><h1>IMDB Score: ${MovieInfo.imdbScore}</h1></a>
                    </div>
                </div>

            </div>
           <!--Change Account Settings-->
            <div id="setting" class="tabcontent">
                <h3>Purchase History</h3>
                <p>Tokyo is the capital of Japan.</p>
            </div>
           <!--Movie Reviews -->
            <div id="emailp" class="tabcontent reviewcontainer">
                <h3>Movie Reviews</h3>
                <!--Write Reviews Header-->
                <div id="part-bg"class="reviewheader">
                    <span>TELL US WHAT YOU THINK !</span>
                </div>
                <!--Write Reviews Creation-->
                <!--change button if user is logged in-->
            <%
                if(checkUser!=null){
            %>
                <div class="reviewcontent">
                    
                    <center><form  action="WriteReviewController" method="post" style="margin-top: 20px">
                            <h1>Title:</h1><input class="text-input-md" type="text" name="viewTitle" maxlength="50" size="50">
                            <h1>Body:</h1><textarea class="text-input-md" rows="4" cols="50" name="viewBody" ></textarea>
                            <button class="btn nav-btn" type="submit" name="movieId" value="${MovieInfo.id}">Submit Review</button>
                    </form></center>
                </div>
            <%
                }else{
            %>
                <div class="reviewcontent">
                    <center><h1>Login To Write A Movie Review</h1></center>
                </div>
            <%
                }
            %>
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
            <div id="history" class="tabcontent">
                <h3>Purchase History</h3>
                <p>Tokyo is the capital of Japan.</p>
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