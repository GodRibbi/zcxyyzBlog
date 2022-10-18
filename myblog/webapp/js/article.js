

var article = {};
article.Init = function ($) {
    //var $ = layui.jquery;
    var slider = 0;
    blogtype();
    
    //类别导航开关点击事件
    $('.category-toggle').click(function (e) {
        e.stopPropagation();    //阻止事件冒泡
        categroyIn();
    });
    //类别导航点击事件，用来关闭类别导航
    $('.article-category').click(function () {
        categoryOut();
    });
    //遮罩点击事件
    $('.blog-mask').click(function () {
        categoryOut();
    });
    $('.f-qq').on('click', function () {
        window.open('http://connect.qq.com/widget/shareqq/index.html?url=' + $(this).attr("href") + '&sharesource=qzone&title=' + $(this).attr("title") + '&pics=' + $(this).attr("cover") + '&summary=' + $(this).attr("desc") + '&desc=你的分享简述' + $(this).attr("desc"));
    });
    $("body").delegate(".fa-times", "click", function () {
        $(".search-result").hide().empty(); $("#searchtxt").val("");
        $(".search-icon i").removeClass("fa-times").addClass("fa-search");
    });
    //显示类别导航
    function categroyIn() {
        $('.category-toggle').addClass('layui-hide');
        $('.article-category').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');
        $('.blog-mask').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');
        $('.blog-mask').removeClass('maskOut').addClass('maskIn');
        $('.blog-mask').removeClass('layui-hide').addClass('layui-show');
        $('.article-category').removeClass('categoryOut').addClass('categoryIn');
        $('.article-category').addClass('layui-show');
    }
    //隐藏类别导航
    function categoryOut() {
        $('.blog-mask').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $('.blog-mask').addClass('layui-hide');
        });
        $('.article-category').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $('.article-category').removeClass('layui-show');
            $('.category-toggle').removeClass('layui-hide');
        });
        $('.blog-mask').removeClass('maskIn').addClass('maskOut').removeClass('layui-show');
        $('.article-category').removeClass('categoryIn').addClass('categoryOut');
    }
    function blogtype() {
        
        $('#category li').hover(function () {
            $(this).addClass('current');
            var num = $(this).attr('data-index');
            $('.slider').css({ 'top': ((parseInt(num) - 1) * 40) + 'px' });
        }, function () {
            $(this).removeClass('current');
            $('.slider').css({ 'top': slider });
        });
        $(window).scroll(function (event) {
            var winPos = $(window).scrollTop();
            if (winPos > 750)
                $('#categoryandsearch').addClass('fixed');
            else
                $('#categoryandsearch').removeClass('fixed');
        });
    };
};
var $;
layui.use(['jquery'], function () {
    $ = layui.jquery;
    article.Init($);//初始化共用js
    $ = layui.jquery,
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
        $("#section1").css("background-attachment", "scroll");
        $("#section3").css("background-attachment", "scroll");
    }
    //Netscape
    else if (explorer.indexOf("Netscape") >= 0) {
        //alert('Netscape');
    }

});

var vm = new Vue({
    el: '#doc-container',
    data: {
        article_top:[],
        article_list:[],
        article_type_List:[]
    },
    mounted() {
        axios.get('http://49.232.222.106:3000/blog/ArticleController/getArticleTop')
            .then(function (response) {
                vm.article_top = response.data;
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
        axios.get('http://49.232.222.106:3000/blog/ArticleController/getArticleList')
            .then(function (response) {
                vm.article_list = response.data;
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
        axios.get('http://49.232.222.106:3000/blog/ArticleController/getArticleTypeList')
            .then(function (response) {
                vm.article_type_List = response.data;
                $('#category li').hover(function () {
                    $(this).addClass('current');
                    var num = $(this).attr('data-index');
                    $('.slider').css({ 'top': ((parseInt(num) - 1) * 40) + 'px' });
                }, function () {
                    $(this).removeClass('current');
                    $('.slider').css({ 'top': slider });
                });
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
    }
})
