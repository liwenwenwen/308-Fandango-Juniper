<%-- 
    Document   : userAccount
    Created on : Apr 8, 2017, 10:14:42 PM
    Author     : liwenfan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Account | ELM</title>
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
        <link rel="stylesheet" href="css/movies.css">
        <link rel="stylesheet" href="css/userAccount.css">
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
      
      <!--User Account PART (using HTML Tabs-->
      <!-- welcome title-->
      <div id="title-bg">
         <div id="title-div" class="container">
           <span id="page-title">Welcome, ${UserInfoSession.userName}</span>
         </div>
       </div>
       <div class="container">
           <div class="tab">
               <button class="tablinks" onclick="openInfo(event, 'dashboard')"><h1>Dashboard</h1></button>
               <button class="tablinks" onclick="openInfo(event, 'setting')"><h1>Account Settings</h1></button>
               <button class="tablinks" onclick="openInfo(event, 'myfavs')"><h1>My Movies + Theaters</h1></button>
               <button class="tablinks" onclick="openInfo(event, 'history')"><h1>Purchase History</h1></button>
            </div>
            <div id="dashboard" class="tabcontent current" >
                <h3>Dashboard</h3>
                <!--Display Basic Info-->
                <div id="part-bg">
                    <div id="part-div" class="container">
                        <span id="part-title">&nbsp; User Name: ${UserInfoSession.userName} &nbsp;
                                                     ||&nbsp;  
                                                     Email: ${UserInfoSession.email}</span>
                    </div>
                </div>
            </div>
           <!--Change Account Settings-->
            <div id="setting" class="tabcontent">
                <h3>Account Settings</h3>
                <!--Change User Name -->
                <div id="part-bg">
                    <div id="part-div" class="container">
                        <span id="part-title">Basic Information</span>
                    </div>
                </div>
                <form action="AccountSettingsController" method="post" style="margin-top: 20px" style="margin-bottom:20px">
                    <h1>User Name</h1>
                    <input class="text-input-md" type="text" name="username" value="${UserInfoSession.userName}">
                    <button class="btn nav-btn" type="submit" value="register">Save</button>
                </form>
                <!--Change Password -->
                <div id="part-bg">
                    <div id="part-div" class="container">
                        <span id="part-title">Change Password</span>
                    </div>
                </div>
                <form action="AccountSettingsController" method="post" style="margin-top: 20px" style="margin-bottom:20px">
                    <h1>Password</h1>
                    <input class="text-input-md" type="text" name="password">
                    <button class="btn nav-btn" type="submit" value="register">Save</button>
                </form>
            </div>
           <!--View User Fav -->
            <div id="myfavs" class="tabcontent">
                <h3>My Movies + Theaters</h3>
                <div id="part-bg">
                    <div id="part-div" class="container">
                        <span id="part-title">My Movies</span>
                    </div>
                </div>
                <div class="container">
                <div class="movie-listing">
                    <!--generate a movie list--> 
                    <c:forEach var="item" items="${MovieFavList}" >
                        <a href="MovieDetailsController?method=get&movieId=${item.id}" class="thumbnail movie-thumbnail">
                        <img class="movie-poster img-responsive noMargin" src="${item.cover}" alt="">
                        </a>
                    </c:forEach>
                </div>
                </div>
            </div>
           <!--View User History -->
            <div id="history" class="tabcontent">
                <h3>Purchase History</h3>
                <p></p>
            </div>
        </div>
        
        
       <!--User Account PART-->
       
      <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
      <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')</script>
      <script src="js/plugins.js"></script>
      <script src="js/main.js"></script>
      <script src="js/bootstrap.js"></script>
      <script src="js/userAccountJs.js"></script>
    </body>
</html>