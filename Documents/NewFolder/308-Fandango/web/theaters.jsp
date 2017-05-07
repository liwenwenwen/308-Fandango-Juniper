<%-- 
    Document   : theaters
    Created on : Apr 17, 2017, 6:46:11 PM
    Author     : liwenfan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="entity.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Account"%>

<!doctype html>
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Theaters | JUNIPER</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="icon" href="img/ic_movie_filter_black_24dp_1x.png">
        <!-- Place favicon.ico in the root directory -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        
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
        <link rel="stylesheet" href="css/theaters.css">
    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <!-- Navbar -->
        <div id="header" class="container">
          <a href="movies.jsp" id="logo">JUNIPER</a>
          <ul class="nav nav-pills ">
            <li>
              <form action="SearchController" method="post">
                <input id="search-bar" type="text" name="Search">
              </form>
            </li>
            <!--li class="dropdown">
              <a class="nav-btn dropdown-toggle" data-toggle="dropdown" href="#">Menu</a>
              <ul class="dropdown-menu nav-dropdown">
                <li><a href="movies.jsp">Movies</a></li>
                <li><a href="DisplayTheatersMainController">Theaters</a></li>
                <li><a href="news.html">News</a></li>
              </ul>
            </li-->
            <li><a class="nav-btn" href="MovieGenresController?method=get&genre=action">Movies</a></li>
            <li><a class="nav-btn" href="DisplayTheatersMainController">Theaters</a></li>
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
        <!-- /Navbar -->
        <!-- Title -->
        <div id="title-bg">
         <div id="title-div" class="container">
           <span id="page-title">Theaters</span>
         </div>
        </div>
        <c:forEach var="item" items="${TheaterMainList}" varStatus="status">
         
           <!-- Page Content -->
        
        <div class ="theater_main_div">
            <div class="parent_div_7">
                <img class="movie-poster img-responsive noMargin" src="${item.iconImage}" alt="theaterImg">
            </div>
            <div class="parent_div_8">
                <h1>${item.name}</h1>
                <span>${item.addrStreet}</span>
                <span>${item.addrCity}</span>
                <span>${item.addrState}</span>
                <span>${item.addrZipcode}</span>
                <br>
                <span>${item.phoneNum}</span>
            </div>
            <div class="parent_div_9">
                <div class="center_div_9">
                <c:choose>
                <c:when test="${UserInfoSession!=null && TheaterFavList[status.index]=='TF'}"> 
                    <a href="TheaterFavController?method=get&theaterId=${item.id}"><i class="material-icons" style="font-size:40px;color:red;">favorite</i></a>
                </c:when>
                <c:when test="${UserInfoSession==null}">
                </c:when>
                <c:otherwise>
                     <a href="TheaterFavController?method=get&theaterId=${item.id}"><i class="material-icons" style="font-size:40px;color:red;">favorite_border</i></a>
                </c:otherwise>
                </c:choose>
                </div>    
            </div>
        </div>
         
        </c:forEach> 
          
     
      
        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')</script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/loadMore_generalSearch.js"></script>
    </body>
</html>


