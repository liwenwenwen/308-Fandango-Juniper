/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var Default=4;
var DefaultViewMore=5;
var inc=0;
var inputs = document.getElementsByTagName("input"), len = inputs.length, i, searchResults;

loadMore();
drawTable(searchResults,Default);


$("#More").click(function () {
   Default=Default+ DefaultViewMore;
   loadMore();
    drawTable(searchResults,Default);
});


function loadMore(){
    
    for (i = 0; i < len; i++) {
        if (inputs[i].name == "searchResults") {
            searchResults = inputs[i].value;
            break;
        }
    }
    window.alert(searchResults[0].title);
    //window.alert(5 + 6);
}

function drawTable(searchResults,bindRow) {

    for (var i = inc; i < searchResults.length; i++) {

        drawRow(searchResults[i]);

        if(searchResults.length-1 == i)
            {
               $("#More").hide();
            }

        if(i==bindRow)
            {
                inc=bindRow;
                break;
            }
    }
}

function drawRow(rowData) {
    var row = $("<tr />")
    $("#personDataTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    row.append($("<td>" + rowData.id + "</td>"));
    row.append($("<td>" + rowData.title + "</td>"));
    
}


