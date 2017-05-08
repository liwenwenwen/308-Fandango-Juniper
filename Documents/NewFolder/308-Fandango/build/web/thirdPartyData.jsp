<%-- 
    Document   : thirdPartyData
    Created on : May 7, 2017, 10:09:29 PM
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
        <title>Your Order | JUNIPER</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="icon" href="img/ic_movie_filter_black_24dp_1x.png">
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
        <link rel="stylesheet" href="css/orderDetails.css">
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
           <span id="page-title">New Updates From Third Party</span>
         </div>
        </div>
        <!-- /Title -->        
        <!-- Page Content -->
        <!--Movie Dashboard-->
        <div class="container">
             <c:choose>
                 <c:when test="${ThirdPartyDateConfirmation==null}">
                    <div class="mainorderdiv">
                <!--Display Basic Info-->
                <div class="parent_div_10">
                    <form action="ConfirmThirdPartyUpdates" method="get">
                        <center><button class="btn nav-btn" style="margin-top: 100px;" type="submit" >Confirm New Updates</button></center>
                    </form>
                </div>
                <div class="parent_div_11">
                    <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover" style="">
                            <tbody>
                                <tr>
                                <td>${ThirdPartyDateTitle}</td>  
                                </tr>
                                <c:forEach var="item" items="${ThirdPartyDateBody}" >
                                <tr>
                                <td>${item}</td>  
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        </div>
                       </div>
                    </div>
                    </div> 
                 </c:when>
                 <c:otherwise>
                     <div style="text-align: center;">
                        <span>Success !</span>
                        <br>
                        <a href="movies.jsp"><span>Go Back to Main Page</span></a>
                     </div>
                     
                 </c:otherwise>
             </c:choose>
            
        </div>
        
        

        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')</script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/loadMore_generalSearch.js"></script>
    </body>
</html>
