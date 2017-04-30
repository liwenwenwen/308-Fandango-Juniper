/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var tstart = 01;
var tend = 10;
var toptions = "";
for(var ticket = tstart ; ticket <=tend; ticket++){
  toptions += "<option>"+ ticket +"</option>";
}
document.getElementById("tt").innerHTML = toptions;