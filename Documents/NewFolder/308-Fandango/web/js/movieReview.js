/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(".reviewheader").click(function () {

    $reviewheader = $(this);
    //getting the next element
    $reviewcontent = $reviewheader.next();
    //open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
    $reviewcontent.slideToggle(500, function () {
        //execute this after slideToggle is done
        //change text of header based on visibility of content div
        $reviewheader.text(function () {
            //change text based on condition
            return $reviewcontent.is(":visible") ? "WRITE A REVIEW" : "TELL US WHAT YOU THINK !";
        });
    });

});

