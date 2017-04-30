<%@page import="entity.Account"%>
<!doctype html>
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Login | JUNIPER</title>
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
        <link rel="stylesheet" href="css/login.css">
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
            <!--li class="dropdown">
              <a class="nav-btn dropdown-toggle" data-toggle="dropdown" href="#">Menu</a>
              <ul class="dropdown-menu nav-dropdown">
                <li><a href="movies.jsp">Movies</a></li>
                <li><a href="DisplayTheatersMainController">Theaters</a></li>
                <li><a href="news.html">News</a></li>
              </ul>
            </li-->
            <li><a class="nav-btn" href="movies.jsp">Movies</a></li>
            <li><a class="nav-btn" href="DisplayTheatersMainController">Theaters</a></li>
            <!--<li><a class="nav-btn" href="#">Showtimes</a></li>-->
            <!--change button if user is logged in-->
            <li><a class="nav-btn" href="login.jsp">Login / Register</a></li>
            <!--change button if user is logged in-->
          </ul>
        </div>
      
      <!--LOGIN PART-->
      <form class="container well" action="LoginController" method="post" style="margin-top: 20px">
        <h1>LOG IN</h1>
        <h3>User Name</h3>
        <input class="text-input-md" type="text" name="username">
        <h3>Password</h3>
        <input class="text-input-md" type="password" name="password">
        <a class="btn nav-btn" href="register.jsp">[Don't have an Account?] Register</a>
        <button class="btn nav-btn" type="submit" value="register">Log In</button>
      </form>  
       <!--LOGIN PART-->
       
      <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
      <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')</script>
      <script src="js/plugins.js"></script>
      <script src="js/main.js"></script>
      <script src="js/bootstrap.js"></script>
    </body>
</html>
