<%-- 
    Document   : generalSearch
    Created on : Apr 16, 2017, 9:21:52 PM
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
        <title>Movies | ELM</title>
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
    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <!-- Navbar -->
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
        <!-- /Navbar -->
        <!-- Title -->
        <h3>ALL "${SearchTarget}" MOVIE RESULTS</h3>
        <div id="title-bg">
         <div id="title-div" class="container">
           <span id="page-title">MOVIES(${SearchCounts}) Matching "${SearchTarget}"</span>
         </div>
        </div>
        <!-- /Title -->        
        <!-- Page Content -->
        <div class="container">
          <div class="movie-listing">
            <c:forEach var="item" items="${SearchMovies}" >
                <a href="MovieDetailsController?method=get&movieId=<c:out value="${item.id}"/>" class="thumbnail movie-thumbnail">
                 <img class="movie-poster img-responsive noMargin" src="<c:out value="${item.cover}"/>" alt="">
              <div class="movie-text">
                <span class="movie-title"><c:out value="${item.title}"/></span>
                <span class="movie-release"><c:out value="${item.releaseDate}"/></span>
              </div>
            </a>
            </c:forEach>  
          </div>
          
         
          
        </div>

        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')</script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>
        <script src="js/bootstrap.js"></script>
    </body>
</html>
