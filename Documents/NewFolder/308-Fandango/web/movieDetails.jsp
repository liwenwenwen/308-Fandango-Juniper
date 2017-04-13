<%-- 
    Document   : movieDetails
    Created on : Apr 12, 2017, 5:46:36 PM
    Author     : liwenfan
--%>

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
              <form action="#">
                <input id="search-bar" type="text" placeholder="Search">
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
            <li><a class="nav-btn" href="userAccount.jsp">[${UserInfoSession.userName}] Account</a></li>
            
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
                String fav = (String)request.getAttribute("UserFaved");
                if(fav.equals("isFaved")){
            %>
            <a href="MovieFavController?method=get&fav=isFaved&favId=${FavObj}"><img src="img/heartOn.png" alt="isFaved"></a>
           <%
               }else if(fav.equals("null")){
               }else{
           %>
            <a href="MovieFavController?method=get&fav=isNotFaved&favId=${MovieInfo.id}"><img src="img/heartOff.png" alt="isNotFaved"></a>
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
            <div id="dashboard" class="tabcontent current" >
                <h3>OverView</h3>
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
                        <span id="part-title">change Password</span>
                    </div>
                </div>
                <form action="AccountSettingsController" method="post" style="margin-top: 20px" style="margin-bottom:20px">
                    <h1>Password</h1>
                    <input class="text-input-md" type="text" name="password">
                    <button class="btn nav-btn" type="submit" value="register">Save</button>
                </form>
            </div>
           <!--Change Email -->
            <div id="emailp" class="tabcontent">
                <h3>Email + Preferences</h3>
                <p>Paris is the capital of France.</p> 
            </div>
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
    </body>
</html>