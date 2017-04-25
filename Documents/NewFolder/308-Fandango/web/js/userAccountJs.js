/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function openInfo(evt, infoName) {
    // Declare all variables
    var i, tabcontent, tablinks;
    //hide dashboard
    //var dash =document.getElementsByClassName("dashboard");
    //dash.style.display="none";

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
   // dash=document.getElementsByClassName("dashboard");
    for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
    }
   

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    
    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(infoName).style.display = "block";
    evt.currentTarget.className += " active";
    

        
    
}


function openInfo2(evt, infoName) {
    alert(infoName);
    // Declare all variables
    var i, tabcontent, tablinks;
    //hide dashboard
    //var dash =document.getElementsByClassName("dashboard");
    //dash.style.display="none";

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
   // dash=document.getElementsByClassName("dashboard");
    for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
    }
   

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    
    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(infoName).style.display = "block";
    evt.currentTarget.className += " active";
    

        
    
}
