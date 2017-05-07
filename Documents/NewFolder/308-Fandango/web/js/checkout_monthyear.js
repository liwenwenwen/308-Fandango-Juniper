/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function(){
        var year=$("body").attr("pay-year");
        $("#year").val(year);
});
$(function(){
        var month=$("body").attr("pay-month");
        $("#month").val(month);
});

var ystart = 2017;
var yend = 2035;
var yoptions = "";
for(var year = ystart ; year <=yend; year++){
  yoptions += "<option>"+ year +"</option>";
}
document.getElementById("year").innerHTML = yoptions;

var mstart = 01;
var mend = 12;
var moptions = "";
for(var month = mstart ; month <=mend; month++){
  moptions += "<option>"+ month +"</option>";
}
document.getElementById("month").innerHTML = moptions;


