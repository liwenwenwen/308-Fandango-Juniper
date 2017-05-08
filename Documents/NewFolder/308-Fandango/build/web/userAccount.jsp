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
        <title>Account | JUNIPER</title>
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
        <link rel="stylesheet" href="css/userAccount.css">
        <link rel="stylesheet" href="css/movieview.css">
        <link rel="stylesheet" href="css/checkout.css">
    </head>
    <body pay-year="${Payment.expirationYear}" pay-month="${Payment.expirationMonth}">
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
      
      <!--User Account PART (using HTML Tabs-->
      <!-- welcome title-->
      <div id="title-bg">
         <div id="title-div" class="container">
            <c:if test="${Admin!=null}">
                 <span id="page-title">Welcome, ${UserInfoSession.userName} [Admin]</span>
            </c:if>
            <c:if test="${Admin==null}">
                <span id="page-title">Welcome, ${UserInfoSession.userName}</span>
            </c:if>
         </div>
       </div>
       <div class="container">
           <div class="tab">
               <button class="tablinks" onclick="openInfo(event, 'dashboard')">Dashboard</button>
               <button class="tablinks" onclick="openInfo(event, 'setting')">Account Settings</button>
               <button class="tablinks" onclick="openInfo(event, 'myfavs')">My Movies + Theaters</button>
               <button class="tablinks" onclick="openInfo(event, 'history')">Purchase History</button>
               <c:if test="${Admin!=null}">
               <button class="tablinks" onclick="openInfo(event, 'checkstatus')">Third Party Data</button>
               </c:if>
            </div>
            <div id="dashboard" class="tabcontent current reviewcontainer" >
                <h3>Dashboard</h3>
                <!--Display Basic Info-->
                <div class ="parent_div_center5">
                    <h3>${UserInfoSession.userName}</h3> 
                    <h1>Email: ${UserInfoSession.email}</h1>
                    <h1>Joined: ${UserInfoSession.joinedDate}</h1>
                    <br>
                    
                </div>
                <c:choose>
                    <c:when test="${Payment!=null}">
                        <div class="parent_div_center6">
                            <h3>Payment Method</h3> 
                            <h1>First Name: ${Payment.firstName}</h1>
                            <h1>Last Name: ${Payment.lastName}</h1>
                            <h1>Card Number: ${Payment.cardNum}</h1>
                            <h1>Expiration Date: ${Payment.expirationMonth}, ${Payment.expirationYear}</h1>
                            <h1>Billing Zip Code: ${Payment.billingZip}</h1>
                        </div>
                    </c:when>
                </c:choose>                                     
                
            </div>
           <!--Change Account Settings-->
            <div id="setting" class="tabcontent reviewcontainer">
                <h3>Account Settings</h3>
                <!--Change User Name -->
                <div id="part-bg"class="reviewheader">
                    Basic Information
                </div>
                
                <form action="AccountSettingsController" method="post" style="margin-top: 20px" style="margin-bottom:20px">
                    <h1>User Name</h1>
                    <input class="text-input-md shorttext" type="text" name="username" value="${UserInfoSession.userName}">
                    <button class="btn nav-btn" type="submit" value="register">Save</button>
                </form>
                <br>
                <!--Change Password -->
                <div id="part-bg"class="reviewheader">
                    Change Password
                </div>
                <form action="AccountSettingsController" method="post" style="margin-top: 20px" style="margin-bottom:20px">
                    <h1>Password</h1>
                    <input class="text-input-md shorttext" type="text" name="password">
                    <button class="btn nav-btn" type="submit" value="register">Save</button>
                </form>
                <br>
                <!--Add/Change pay-->
                <div id="part-bg"class="reviewheader">
                    Add/Change Payment Method
                </div>
                <form action="AccountSettingsController" method="post" style="margin-top: 20px" style="margin-bottom:20px">
                    <c:choose>
                        <c:when test="${Payment==null}">
                            <h1>Card Number</h1>
                            <input class="text-input-md longtext" type="text" name="cardNumber">
                            <h1>Expiration Date</h1>
                            <select class="monthyear" id="month" name="month"></select>
                            <select class="monthyear" id="year" name="year"></select>
                            <h1>First Name</h1>
                            <input class="text-input-md shorttext" type="text" name="firstName">
                            <h1>Last Name</h1>
                            <input class="text-input-md shorttext" type="text" name="lastName">
                            <h1>Billing ZIP Code</h1>
                            <input class="text-input-md shorttext leavespace" type="text" name="zipcode">
                            <br>
                            <button class="btn nav-btn" type="submit" value="register">Save</button>
                        </c:when>
                        <c:otherwise>
                            <h1>Card Number</h1>
                            <input class="text-input-md longtext" type="text" name="cardNumber" value="${Payment.cardNum}">
                            <h1>Expiration Date</h1>
                            <select class="monthyear" id="month" name="month" value="${Payment.expirationMonth}"></select>
                            <select class="monthyear" id="year" name="year"  value="${Payment.expirationYear}"></select>
                            <h1>First Name</h1>
                            <input class="text-input-md shorttext" type="text" name="firstName" value="${Payment.firstName}">
                            <h1>Last Name</h1>
                            <input class="text-input-md shorttext" type="text" name="lastName" value="${Payment.lastName}">
                            <h1>Billing ZIP Code</h1>
                            <input class="text-input-md shorttext leavespace" type="text" name="zipcode" value="${Payment.billingZip}">
                            <br>
                            <button class="btn nav-btn" type="submit" value="register">Save</button>
                            
                        </c:otherwise>
                    </c:choose>
                    
                </form>
            </div>
           <!--View User Fav -->
            <div id="myfavs" class="tabcontent reviewcontainer">
                <h3>My Movies + Theaters</h3>
                <div id="part-bg"class="reviewheader">
                    My Movies
                </div>
                <br>
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
                <div id="part-bg"class="reviewheader">
                    My Theaters
                </div>
                <br>
                <div class="container">
                <div class="movie-listing">
                    <!--generate a movie list--> 
                    <c:forEach var="item" items="${TheaterFavMainList}" >
                        <a href="DisplayTheatersMainController?method=get" class="thumbnail movie-thumbnail">
                        <img class="movie-poster img-responsive noMargin" src="${item.iconImage}" alt="">
                        <h1>${item.name}</h1>
                        </a>
                    </c:forEach>
                </div>
                </div>
            </div>
           <!--View User History -->
            <div id="history" class="tabcontent reviewcontainer">
                <h3>Purchase History</h3>
                <c:choose>
                    <c:when test="${not empty OrderList}">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover" style="">
                            <thead>
                                <tr>
                                <th>Order#</th>
                                <th>Create Date</th>
                                <th>Tickets#</th>
                                <th>Order Details</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${OrderList}" >
                                    
                                <tr>
                                        <td># ${item.id}</td>
                                        <td>${item.createDate}</td>
                                        <td>${item.numTickets}</td>
                                        <td><a class="btn nav-btn" href="OrderDetailsController?method=get&orderId=${item.id}" target="_blank">Details</a></td>
                                     
                                </tr>
                              
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                    </c:when>
                    <c:otherwise>
                        <h3> No Purchase History Yet! </h3>
                    </c:otherwise>
                </c:choose>
            </div>
           <!--View Tird Party -->
           <c:if test="${Admin!=null}">
           <div id="checkstatus" class="tabcontent reviewcontainer">
               <div id="part-bg"class="reviewheader">
                    Check Any Updates From Third Party
               </div>
               <form action="ThirdPartyUpdates" method="get">
               <center><button class="btn nav-btn" style="margin-top: 10px;" type="submit" >Check</button></center>
               </form>
           </div>
           </c:if>
        </div>
        
        
       <!--User Account PART-->
       
      <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
      <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')</script>
      <script src="js/plugins.js"></script>
      <script src="js/main.js"></script>
      <script src="js/bootstrap.js"></script>
      <script src="js/userAccountJs.js"></script>
      <script src ="js/checkout_monthyear.js"></script>
    </body>
</html>