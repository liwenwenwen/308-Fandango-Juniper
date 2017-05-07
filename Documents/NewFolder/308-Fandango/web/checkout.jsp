<%-- 
    Document   : checkout
    Created on : Apr 22, 2017, 7:12:06 PM
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
        <title>Checkout | JUNIPER</title>
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
        <link rel="stylesheet" href="css/checkout.css">
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
           <span id="page-title">CHECKOUT</span>
         </div>
        </div>
        
        <!-- /Title -->
    <c:choose>
        <c:when test="${numTickets==null}"> 
        
        <form class="container well" action="PurchaseTicketsController" method="get" style="margin-top: 20px">
            
            <h1>HOW MANY TICKETS?</h1>
            <h3>Adult : </h3>
            <select class="shorttext leavespace" name="numTickets" id="tt"></select>
            <br>
            <button class="btn nav-btn" type="submit" value="register">Buy Tickets</button>
        </form> 
        </c:when>
        
        <c:otherwise>
         
            
          <form class="container well" action="PurchaseTicketsMainController" method="post" style="margin-top: 20px">   
           <div class="parent_div_1">
            <c:choose>
                <c:when test="${UserInfoSession==null}">            
                    <h1>- Email -</h1>
                    <input class="text-input-md longtext" type="text" name="email">
                </c:when>
                <c:otherwise>
                    <h1>- HI ${UserInfoSession.userName} -</h1>
                    <h3>( ${UserInfoSession.email} )</h3>
                </c:otherwise>        
            </c:choose>
            <h1>- PAYMENT -</h1>
            <c:choose>
            <c:when test="${Payment!=null}">
            <p>First Name: ${Payment.firstName}</p>
            <p>Last Name: ${Payment.lastName}</p>
            <p>Card Number: ${Payment.cardNum}</p>
            <p>Expiration Date: ${Payment.expirationMonth}, ${Payment.expirationYear}</p>
            <p>Billing Zip Code: ${Payment.billingZip}</p>
            <button class="btn nav-btn" type="submit" value="register">Purchase With This Payment Method</button>
            </c:when>
            </c:choose>
            <h3>Card Number</h3>
            <input class="text-input-md longtext" type="text" name="cardNumber">
            <h3>Expiration Date</h3>
            <select class="monthyear" id="month"></select>
            <select class="monthyear" id="year"></select>
            <h3>First Name</h3>
            <input class="text-input-md shorttext" type="text" name="firstName">
            <h3>Last Name</h3>
            <input class="text-input-md shorttext" type="text" name="lastName">
            <h3>Billing ZIP Code</h3>
            <input class="text-input-md shorttext leavespace" type="text" name="zipcode">
            <br>
            <button class="btn nav-btn" type="submit" value="register">Purchase</button>
          </div>
          <div class="parent_div_2 parent_div_center">
          
            <a href="MovieDetailsController?method=get&movieId=${MovieInfo.id}">
                <img src="${MovieInfo.cover}" style="width:230px; height:350px; " alt="cover">
            </a>
            
            <h3>- ${MovieInfo.title} -</h3>
            <h3>${schedule.time}, ${checkoutDate}</h3>
            <h3>${selectedTheater.name}</h3>
            <p>( ${selectedTheater.addrStreet},${selectedTheater.addrCity},${selectedTheater.addrState},${selectedTheater.addrZipcode} )</p>
            <div class="parent_div_center2">
                <h3 class="barcode">Unit Price:    $${unitPrice}</h3>
                <h3 class="barcode">${numTickets} x $${unitPrice} = $${TotalPrice}</h3>
                <h3 class="barcode">Total:         $${TotalPrice}</h3>
            </div>
          </div> 
               
        </form>
        </div>
         
        </c:otherwise>
        
    </c:choose>
       

        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')</script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/loadMore_generalSearch.js"></script>
        <script src ="js/checkout_monthyear.js"></script>
        <script src ="js/checkout_ticket.js"></script>
    </body>
</html>


