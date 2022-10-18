var area;
var $;
layui.use(['element', 'jquery', 'form', 'layedit', 'flow'], function () {
    var element = layui.element;
    var form = layui.form;
    $ = layui.jquery;
    var layedit = layui.layedit;
    var flow = layui.flow;
    //留言的编辑器
    var editIndex = layedit.build('remarkEditor', {
        height: 150,
        tool: [
            'face' //表情

            , '|' //分割线

            , 'strong' //加粗
            , 'italic' //斜体
            , 'underline' //下划线
            , 'del' //删除线
        ]
    });
    //留言的编辑器的验证
    form.verify({
        content: function (value) {
            value = $.trim(layedit.getContent(editIndex));
            if (value == "") return "请输入内容";
            layedit.sync(editIndex);
        },
        replyContent: function (value) {
            if (value == "") return "请输入内容";
        }
    });
    form.on('submit(addComment)', function (data) {
        //alert("asd");

        //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        //vm.methods.addComment123(data.form);
        setNameAndImg(function () {
            if (data.field.comment_user_img == "" || data.field.comment_user_name == "") {
                data.field.comment_user_img = vm.user.img;
                data.field.comment_user_name = vm.user.name;
            }
            //console.log(data.field); //被执行提交的form对象，一般在存在form标签时才会返回

            axios({
                method: 'post',
                url: 'http://49.232.222.106:3000/blog/CommentController/addMainComment',
                data: data.field
            })
                .then(function (response) {
                    if (response.data == "success!") {
                        axios.get('http://49.232.222.106:3000/blog/CommentController/getMainComment')
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
            url: 'http://49.232.222.106:3000/blog/CommentController/addMainCommentReply',
            data: data.field
        })
            .then(function (response) {
                if (response.data == "success!") {
                    axios.get('http://49.232.222.106:3000/blog/CommentController/getMainComment')
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
    // //回复按钮点击事件
    // $('#btn-reply').on('click', function () {
    //      var targetId = $(e.target).data('targetid')
    //          , targetName = $(e.target).data('targetname')
    //          , $container = $(e.target).parent('p').parent().siblings('.replycontainer');
    //          console.log($(e.target));
    //      if ($(e.target).text() == '回复') {
    //          $container.find('textarea').attr('placeholder', '回复【' + targetName + '】');
    //          $container.removeClass('layui-hide');
    //          $container.find('input[name="targetUserId"]').val(targetId);
    //          $(e.target).parents('.message-list li').find('.btn-reply').text('回复');
    //          $(e.target).text('收起');
    //      } else {
    //          $container.addClass('layui-hide');
    //          $container.find('input[name="targetUserId"]').val(0);
    //          $(e.target).text('回复');
    //      }
    //  });

});
var select = '    <div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;"><h2>选择你的头像</h2><br><select class="image-picker show-html"><option value=""></option><option data-img-src="./image/a1.png" value="1"> Page 1 </option><option data-img-src="./image/a2.png" value="2"> Page 2 </option><option data-img-src="./image/a3.png" value="3"> Page 3 </option><option data-img-src="./image/a4.png" value="4"> Page 4 </option></select><br>        <h2>请输入你的昵称</h2><br><input type="text" class="layui-input layui-name-text" placeholder="请输入昵称"></div>'
var vm = new Vue({
    el: '#doc-container',
    data: {
        comment: [{}],
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
                setNameAndImg(function () { });
            }
            else {
                $container.addClass('layui-hide');
                $(e.target).text('回复');
            }
        }
    },
    mounted() {
        axios.get('http://49.232.222.106:3000/blog/CommentController/getMainComment')
            .then(function (response) {
                vm.comment = response.data;
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
    },

})
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