console.log(url);



layui.use(['jquery', 'util'], function () {

    var $ = layui.jquery,
        util = layui.util;
    var explorer = navigator.userAgent;
    //ie 
    if (explorer.indexOf("MSIE") >= 0) {
        //alert("ie");
    }
    //firefox 
    else if (explorer.indexOf("Firefox") >= 0) {
        //alert("Firefox");
    }
    //Chrome
    else if (explorer.indexOf("Chrome") >= 0) {
        //alert("Chrome");
    }
    //Opera
    else if (explorer.indexOf("Opera") >= 0) {
        //alert("Opera");
    }
    //Safari
    else if (explorer.indexOf("Safari") >= 0) {
        //alert("Safari");
        $("#section1").css("background-attachment","scroll");
        $("#section3").css("background-attachment","scroll");
    }
    //Netscape
    else if (explorer.indexOf("Netscape") >= 0) {
        //alert('Netscape');
    }
    $(window).load(function () {
        $("#loading").fadeOut(500);
        new WOW().init();
    })
    util.fixbar();;
    $('.next').click(function () {
        $('html,body').animate({
            scrollTop: $('#section1').outerHeight() + 1
        }, 600);
    });
    $('#menu').on('click', function () {
        var mark = $(this).attr('data-mark');
        if (mark === 'false') {
            $(this).removeClass('menu_open').addClass('menu_close');
            //open
            $('#navgation').removeClass('navgation_close').addClass('navgation_open');
            $(this).attr({ 'data-mark': "true" });
        } else {
            $(this).removeClass('menu_close').addClass('menu_open');
            //close
            $('#navgation').removeClass('navgation_open').addClass('navgation_close');
            $(this).attr({ 'data-mark': "false" });
        }
    });

});
new Vue({
    el:".layui-row",
    data:{
        msg:"123"
    }
})