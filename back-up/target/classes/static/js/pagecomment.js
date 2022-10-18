
var $;
var layer;
layui.use(['element', 'jquery', 'form', 'layedit', 'flow'], function () {
    var form = layui.form;
    var element = layui.element;
    $ = layui.jquery;
    var layedit = layui.layedit;
    var flow = layui.flow;
    layer = layui.layer;
    var sub_btn_index = layedit.build('remarkEditor', {
        tool: [
            'face' //表情
            
            , '|' //分割线

            , 'strong' //加粗
            , 'italic' //斜体
            , 'underline' //下划线
            , 'del' //删除线
        ]
    }); //建立编辑器
    //评论和留言的编辑器的验证
    form.on('submit(addComment)', function (data) {
        //alert("asd");
        
        //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        //vm.methods.addComment123(data.form);
        setNameAndImg(function(){
            if(data.field.comment_user_img==""||data.field.comment_user_name==""){
                data.field.comment_user_img = vm.user.img;
                data.field.comment_user_name = vm.user.name;
            }
            // console.log(data.field); //被执行提交的form对象，一般在存在form标签时才会返回
                    
        axios({
            method: 'post',
            url: 'http://49.232.222.106:3000/blog/CommentController/addArticleComment',
            data: data.field
        })
            .then(function (response) {
                if (response.data == "success!") {
                    axios.get('http://49.232.222.106:3000/blog/CommentController/getArticleCommentByArticleId/' + getQueryVariable("id"))
                        .then(function (response) {
                            vm.comment = response.data;
                            layer.msg('留言成功', { icon: 6 });
                        })
                        .catch(function (error) { // 请求失败处理
                            console.log(error);
                        });
                }
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
        });

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
    form.on('submit(addCommentReply)', function (data) {
        //alert("asd");
        //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
        //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        axios({
            method: 'post',
            url: 'http://49.232.222.106:3000/blog/CommentController/addArticleCommentReply',
            data: data.field
        })
            .then(function (response) {
                if (response.data == "success!") {
                    axios.get('http://49.232.222.106:3000/blog/CommentController/getArticleCommentByArticleId/' + getQueryVariable("id"))
                        .then(function (response) {
                            vm.comment = response.data;
                            var $container = $(".btn-reply").parent('p').siblings('.replycontainer');
                            $container.addClass('layui-hide');
                            $(".btn-reply").text('回复');
                            layer.msg('回复成功', { icon: 6 });
                        })
                        .catch(function (error) { // 请求失败处理
                            console.log(error);
                        });
                }
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

    form.verify({
        content: function () {
            var value;
            var value = $.trim(layedit.getContent(sub_btn_index));
            if (value == "") return "请输入内容";
            layedit.sync(sub_btn_index);
        },
        replyContent: function (value) {
            if (value == "") return "请输入内容";
        }
    });


    // //回复按钮点击事件
    // $('#blog-comment').on('click', '.btn-reply', function () {
    //     var targetId = $(this).data('targetid')
    //         , targetName = $(this).data('targetname')
    //         , $container = $(this).parent('p').parent().siblings('.replycontainer');
    //     if ($(this).text() == '回复') {
    //         $container.find('textarea').attr('placeholder', '回复【' + targetName + '】');
    //         $container.removeClass('layui-hide');
    //         $container.find('input[name="targetUserId"]').val(targetId);
    //         $(this).parents('.blog-comment li').find('.btn-reply').text('回复');
    //         $(this).text('收起');
    //     } else {
    //         $container.addClass('layui-hide');
    //         $container.find('input[name="targetUserId"]').val(0);
    //         $(this).text('回复');
    //     }
    // });
});

var select = '    <div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;"><h2>选择你的头像</h2><br><select class="image-picker show-html"><option value=""></option><option data-img-src="./image/a1.png" value="1"> Page 1 </option><option data-img-src="./image/a2.png" value="2"> Page 2 </option><option data-img-src="./image/a3.png" value="3"> Page 3 </option><option data-img-src="./image/a4.png" value="4"> Page 4 </option></select><br>        <h2>请输入你的昵称</h2><br><input type="text" class="layui-input layui-name-text" placeholder="请输入昵称"></div>'

var vm = new Vue({
    el: '#doc-container',
    data: {
        article: {
            "article_id": -1,
            "article_url": "",
            "article_looking": -1,
            "article_likes": -1,
            "article_date": "",
            "article_istop": -1,
            "article_hat": "",
            "article_title": "",
            "article_detail": "",
            "article_image": "",
            "article_type": {
                "article_type_id": -1,
                "article_type_name": ""
            },
            "article_tags": [
                {
                    "article_tag_id": -1,
                    "article_id": -1,
                    "article_tag_name": ""
                }
            ]
        },
        comment: [{}],
        thumbsup: false,
        user: {
            "name": "",
            "img": ""
        }
    },
    methods: {
        replymsg: function (e, item) {
            var targetName = item.comment_user_name
                , $container = $(e.target).parent('p').siblings('.replycontainer');
            if ($(e.target).text() == '回复') {
                $container.find('textarea').attr('placeholder', '回复【' + targetName + '】');
                $container.removeClass('layui-hide');
                $(e.target).parents('.message-list li').find('.btn-reply').text('回复');
                $(e.target).text('收起');
                setNameAndImg(function(){});
            }
            else {
                $container.addClass('layui-hide');
                $(e.target).text('回复');
            }
        },
        donate: function (e) {
            layer.open({
                type: 1
                , title: false //不显示标题栏
                , closeBtn: true
                , area: '400px;'
                , shade: 0.8
                , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                , btn: ['OK']
                , btnAlign: 'c'
                , moveType: 1 //拖拽模式，0或者1
                , content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;"><div style=" display:inline-block;width:150px;"><img src="./image/a1.png" height="100px" width="100px"/><br><div style="text-align: center;">支付宝</div></div><div style=" display:inline-block;width:150px;"><img src="./image/a2.png" height="100px" width="100px"/><br><div style="text-align: center;">微信</div></div></div>'
                , success: function (layero) {
                    var btn = layero.find('.layui-layer-btn');
                    btn.find('.layui-layer-btn0').bind("click", function () {
                        layer.msg('谢谢你的捐赠', { icon: 6 });
                    });
                }
            });
        },
        thumbs: function (e) {
            if (!this.thumbsup) {
                layer.msg('谢谢你的点赞', { icon: 6 });
                this.thumbsup = true;
                axios.get('http://49.232.222.106:3000/blog/ArticleController/LikesUp/' + getQueryVariable("id"))
                    .then(function (response) {
                        if (response = "success") {
                            console.log("success");
                        }
                    })
                    .catch(function (error) { // 请求失败处理
                        console.log(error);
                    });
            }
            else {
                layer.msg('你已经点过赞了', { icon: 7 });
            }
        }
    },
    mounted() {
        axios.get('http://49.232.222.106:3000/blog/ArticleController/getArticleById/' + getQueryVariable("id"))
            .then(function (response) {
                vm.article = response.data;
                getMarkDown();
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
        axios.get('http://49.232.222.106:3000/blog/CommentController/getArticleCommentByArticleId/' + getQueryVariable("id"))
            .then(function (response) {
                vm.comment = response.data;
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
    }
})
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) { return pair[1]; }
    }
    return (false);
}
function getMarkDown() {
    var testEditormdView;
    $.ajax
        ({
            type: "GET",
            url: vm.article.article_url,
            contentType: "application/x-www-form-urlencoded",
            crossDomain: true,
            dataType: 'text',
            success: function (markdown, status) {
                if (status == "success") {
                    testEditormdView = editormd.markdownToHTML("test-editormd-view",
                        {
                            markdown: markdown,//+ "\r\n" + $("#append-test").text(),
                            //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
                            htmlDecode: "style,script,iframe",  // you can filter tags decode
                            //toc             : false,
                            tocm: true,    // Using [TOCM]
                            //tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
                            //gfm             : false,
                            //tocDropdown     : true,
                            // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
                            emoji: true,
                            taskList: true,
                            tex: true,  // 默认不解析
                            flowChart: true,  // 默认不解析
                            sequenceDiagram: true,  // 默认不解析
                        });
                }
            },
        })
}
function setNameAndImg(fun) {
    if ($.cookie().username == null || $.cookie().username == null) {
        layer.open({
            type: 1
            , title: false //不显示标题栏
            , closeBtn: false
            , area: '450px;'
            , shade: 0.8
            , id: 'LAY_layuipro1' //设定一个id，防止重复弹出
            , btn: ['OK']
            , btn1: function (index, layero) {
                //按钮【按钮一】的回调
                var text = layero.find('.layui-name-text').val();
                console.log(text);
                if (text.length > 7 || text.length == 0) {
                    layer.msg('昵称输入不正确，为空或大于七位', { icon: 7 });
                    return false;
                }
                else {
                    vm.user.name = text;
                    $.cookie('username', text)
                    if ($.cookie().userimg == null) {
                        $.cookie('userimg', "./image/t2.png")
                        vm.user.img = "./image/t2.png";
                    }
                    layer.close(layer.index);
                }
                fun();
            }
            , btnAlign: 'c'
            , moveType: 1 //拖拽模式，0或者1
            , content: select
            , success: function (layero) {
                layero.find(".image-picker").imagepicker({
                    changed: function (select, newValues, oldValues, event) {
                        var img = "";
                        switch (newValues[0]) {
                            case "1":
                                img = "./image/a1.png";
                                break;
                            case "2":
                                img = "./image/a2.png";
                                break;
                            case "3":
                                img = "./image/a3.png";
                                break;
                            case "4":
                                img = "./image/a4.png";
                                break;
                        }
                        vm.user.img = img;
                        $.cookie('userimg', img);
                    }
                })
            }
        });
    }
    else {
        vm.user.name = $.cookie().username;
        vm.user.img = $.cookie().userimg;
        fun();
    }
}