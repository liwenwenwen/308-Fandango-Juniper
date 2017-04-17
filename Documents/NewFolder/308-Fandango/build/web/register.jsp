<%@page import="entity.Account"%>
<!doctype html>
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Register | ELM</title>
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
        <link href="https://fonts.googleapis.com/css?family=Luckiest+Guy|Permanent+Marker" rel="stylesheet">
        
        <!-- Page specific CSS links go here -->
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/register.css">
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
    
      <!--REGISTER PART-->
      <form class="container well" action="RegistrationController" method="post" style="margin-top: 20px">
        <h1>CREATE A NEW ACCOUNT</h1>
        <h3>User Name</h3>
        <input class="text-input-sm" type="text" name="username">
        <h3>Email Address</h3>
        <input class="text-input-md" type="text" name="email">
        <h3>Password</h3>
        <input class="text-input-md" type="password" name="password">
        <button class="btn nav-btn" type="submit" value="register">Register</button>
      </form>
      <!--REGISTER PART-->

      <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
      <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')</script>
      <script src="js/plugins.js"></script>
      <script src="js/main.js"></script>
      <script src="js/bootstrap.js"></script>
    </body>
</html>
