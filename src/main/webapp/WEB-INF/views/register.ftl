<!DOCTYPE html>
<html lang="en">
<head>
[@block name="Meta"]
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content=""/>
    <meta name="author" content="YJH"/>
[/@block]
    <title>Car 公司用户系统 - 公司注册</title>


    <link href="[@spring.url '/resources/assets/style/bootstrap.css'/]" rel="stylesheet">
    <link rel="stylesheet" href="[@spring.url '/resources/assets/style/font-awesome.css'/]">
    <link rel="stylesheet" href="[@spring.url '/resources/assets/file_upload/file-upload.css'/]">
    <link href="[@spring.url '/resources/assets/style/style.css'/]" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.css'/]"/>
</head>

<body>

<div class="admin-form">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <!-- Widget starts -->
                <div class="widget wred">
                    <div class="widget-head">
                        <i class="icon-lock"></i> 用户注册
                    </div>
                    <div class="widget-content">
                    [@mc.showAlert /]
                        <div class="padd">
                            <form class="form-horizontal" action="[@spring.url '/register'/]"
                                  onsubmit="return checkSubmit()" method="post">
                                <!-- Registration form starts -->
                                <!-- Name -->
                            [@spring.bind "command.userName"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-4" for="userName">用户名</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" id="userName" name="userName"
                                               value="${command.userName!}" placeholder="用户名" required/>
                                    [@spring.showErrors "userName"/]
                                    </div>
                                </div>

                            [@spring.bind "command.verificationCode"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-4" for="userName">验证码</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="col-md-8" id="verificationCode" placeholder="验证码"
                                               value="${command.verificationCode!}" name="verificationCode"
                                               required/>
                                        <button type="button" id="send_verification_code"
                                                class="btn btn-success btn-sm">发送验证码
                                        </button>
                                    [@spring.showErrors "verificationCode"/]
                                    </div>
                                </div>

                            [@spring.bind "command.password"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-4" for="password">密码</label>
                                    <div class="col-lg-8">
                                        <input type="password" class="form-control" id="password" minlength="6"
                                               name="password" value="${command.password!}" placeholder="密码" required/>
                                    [@spring.showErrors "password"/]
                                    </div>
                                </div>

                            [@spring.bind "command.password"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-4" for="confirmPassword">确认密码</label>
                                    <div class="col-lg-8">
                                        <input type="password" class="form-control" id="confirmPassword"
                                               placeholder="确认密码"
                                               name="confirmPassword" value="${command.confirmPassword!}"
                                               onchange="checkPasswords()" required/>
                                    [@spring.showErrors "password"/]
                                    </div>
                                </div>

                            [@spring.bind "command.email"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-4" for="email">邮箱</label>
                                    <div class="col-lg-8">
                                        <input type="email" class="form-control" id="email" value="${command.email!}"
                                               name="email" placeholder="邮箱" required/>
                                    [@spring.showErrors "email"/]
                                    </div>
                                </div>

                            [@spring.bind "command.name"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-4" for="name">公司名称</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" id="name" name="name"
                                               placeholder="公司名称" value="${command.name!}" required/>
                                    [@spring.showErrors "name"/]
                                    </div>
                                </div>

                            [@spring.bind "command.registerDate"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-4" for="registerDate">公司注册时间</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" id="registerDate"
                                               name="registerDate" value="${command.registerDate!}" required/>
                                    [@spring.showErrors "registerDate"/]
                                    </div>
                                </div>

                            [@spring.bind "command.registerAddress"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-4" for="registerAddress">公司注册地点</label>
                                    <div class="col-lg-8">
                                        <div class="register_area_box">
                                            <select class="col-xs-3 area_data">
                                            </select>
                                        </div>
                                    [@spring.showErrors "registerAddress"/]
                                    </div>
                                </div>

                            [@spring.bind "command.operateAddress"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-4" for="operateAddress">公司运营地点</label>
                                    <div class="col-lg-8">
                                        <div class="operate_area_box">
                                            <select class="col-xs-3 area_data">
                                            </select>
                                        </div>
                                    [@spring.showErrors "operateAddress"/]
                                    </div>
                                </div>

                            [@spring.bind "command.folder"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-4" for="folder">资质文件</label>
                                    <div class="col-lg-8">
                                        <input type="hidden" class="form-control" id="folder"
                                               name="folder" value="${command.folder!}" required/>
                                        <button type="button" class="btn btn-success btn-sm"
                                                onclick="EV_modeAlert('uploadFile')">添加图片
                                        </button>
                                        <div class="folder-img-box">

                                        </div>
                                    [@spring.showErrors "folder"/]
                                    </div>
                                </div>
                                <!-- Accept box and button s-->
                                <div class="form-group">
                                    <div class="col-lg-9 col-lg-offset-3">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" id="read_clause" checked="checked"/>
                                                我已阅读并同意相关服务条款
                                            </label>
                                        </div>
                                        <br/>
                                        <button type="submit" class="btn btn-danger">提交注册</button>
                                        <!--<button type="reset" class="btn btn-success">取消</button>-->
                                    </div>
                                </div>
                                <br/>
                            </form>

                        </div>
                    </div>
                    <div class="widget-foot">
                        已有账号? <a href="[@spring.url "/login"/]">直接登录</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="uploadFile">
    <div class="clearfix">
        <span>图片上传</span>

        <form action="/uploadFile/upload" id="registerForm" enctype="multipart/form-data"
              class="form-horizontal" data-min-file-count="1"
              method="post">
            <a class="file"><input name="files" onchange="fileChange(this)" multiple type="file"/>
                <p class="showFileName">您未选择上传文件！</p></a>
            <button type="button" onclick="sub()" class="btn btn-primary">上传</button>
            <button type="button" onclick="ok($(this))" id="ok-img-result" data-result class="btn btn-default">确定
            </button>
        </form>
        <div>
            <ul id="upload_imgs" class="clearfix"></ul>
        </div>
    </div>
</div>
<!-- JS -->
<script src="[@spring.url '/resources/assets/js/jquery.js'/]"></script>
<script src="[@spring.url '/resources/assets/app/js/area.js'/]"></script>
<script src="[@spring.url '/resources/assets/js/jquery.form.js'/]"></script>
<script src="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.full.js'/]"></script>
[#--<script src="js/bootstrap.js"></script>--]
<script>
    function checkSubmit() {
        console.log($("#read_clause").is(":checked"));
        if (!$("#read_clause").is(":checked")) {
            alert("请阅读相关服务条款条款");
            return false;
        }
        return true;
    }

    function checkPasswords() {
        var pass1 = document.getElementById("password");
        var pass2 = document.getElementById("confirmPassword");

        if (pass1.value != pass2.value)
            pass2.setCustomValidity("两次输入的密码不匹配");
        else
            pass1.setCustomValidity("");
    }

    $(".register_area_box").areaCascade("registerAddress");
    $(".operate_area_box").areaCascade("operateAddress");

    $("#send_verification_code").click(function () {
        var userName = $("#userName").val();
        var _this = $(this);
        var re = /^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}|177\\d{8}$/;
        if (re.test(userName)) {
            $.ajax({
                type: "post",
                url: "/sms/send_register",
                data: "userName=" + userName,
                dataType: "json",
                success: function (data) {
                    if (typeof data == 'object') {
                        data = data;
                    } else {
                        data = JSON.parse(data);
                    }
                    if (data.code == "0") {
                        alert("发送成功!");
                        countdown(_this);
                    } else {
                        alert(data.message);
                    }
                }
            })
        } else {
            alert("请输入正确的11位手机号码");
        }
    })

    function countdown(sendMessageBtn) {
        var timer = 120;
        var clearTimer = setInterval(sendMessage, 1000);
        sendMessage();
        function sendMessage() {
            if (timer == 0) {
                sendMessageBtn.html("重新发送").removeAttr("disabled");
                sendMessageBtn.css({
                    'cursor': 'pointer',
                    'background': '#FFC100'
                });
                clearInterval(clearTimer);
            } else {
                timer--;
                sendMessageBtn.attr("disabled", true);
                sendMessageBtn.css({
                    'cursor': 'no-drop',
                    'background': '#ccc'
                });
                sendMessageBtn.html(timer + " 秒后重试");
            }
        }
    }
</script>
<!--弹层JS-->
<script language="JavaScript" type="text/javascript">
    //用来记录要显示的DIV的ID值
    var EV_MsgBox_ID = ""; //重要

    //弹出对话窗口(msgID-要显示的div的id)
    function EV_modeAlert(msgID) {
        //创建大大的背景框
        var bgObj = document.createElement("div");
        bgObj.setAttribute('id', 'EV_bgModeAlertDiv');
        document.body.appendChild(bgObj);
        //背景框满窗口显示
        EV_Show_bgDiv();
        //把要显示的div居中显示
        EV_MsgBox_ID = msgID;
        EV_Show_msgDiv();
    }

    //关闭对话窗口
    function EV_closeAlert() {
        var msgObj = document.getElementById(EV_MsgBox_ID);
        var bgObj = document.getElementById("EV_bgModeAlertDiv");
        msgObj.style.display = "none";
        document.body.removeChild(bgObj);
        EV_MsgBox_ID = "";
    }

    //窗口大小改变时更正显示大小和位置
    window.onresize = function () {
        if (EV_MsgBox_ID.length > 0) {
            EV_Show_bgDiv();
            EV_Show_msgDiv();
        }
    };

    //窗口滚动条拖动时更正显示大小和位置
    window.onscroll = function () {
        if (EV_MsgBox_ID.length > 0) {
            EV_Show_bgDiv();
            EV_Show_msgDiv();
        }
    };

    //把要显示的div居中显示
    function EV_Show_msgDiv() {
        var msgObj = document.getElementById(EV_MsgBox_ID);
        msgObj.style.display = "block";
        var msgWidth = msgObj.scrollWidth;
        var msgHeight = msgObj.scrollHeight;
        var bgTop = EV_myScrollTop();
        var bgLeft = EV_myScrollLeft();
        var bgWidth = EV_myClientWidth();
        var bgHeight = EV_myClientHeight();
        var msgTop = bgTop + Math.round((bgHeight - msgHeight) / 2);
        var msgLeft = bgLeft + Math.round((bgWidth - msgWidth) / 2);
        msgObj.style.position = "absolute";
        msgObj.style.top = msgTop + "px";
        msgObj.style.left = msgLeft + "px";
        msgObj.style.zIndex = "10001";
        $("#upload_imgs").empty();
    }
    //背景框满窗口显示
    function EV_Show_bgDiv() {
        var bgObj = document.getElementById("EV_bgModeAlertDiv");
        var bgWidth = EV_myClientWidth();
        var bgHeight = EV_myClientHeight();
        var bgTop = EV_myScrollTop();
        var bgLeft = EV_myScrollLeft();
        bgObj.style.position = "absolute";
        bgObj.style.top = bgTop + "px";
        bgObj.style.left = bgLeft + "px";
        bgObj.style.width = bgWidth + "px";
        bgObj.style.height = bgHeight + "px";
        bgObj.style.zIndex = "10000";
        bgObj.style.background = "#777";
        bgObj.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=60,finishOpacity=60);";
        bgObj.style.opacity = "0.6";
    }
    //网页被卷去的上高度
    function EV_myScrollTop() {
        var n = window.pageYOffset
                || document.documentElement.scrollTop
                || document.body.scrollTop || 0;
        return n;
    }
    //网页被卷去的左宽度
    function EV_myScrollLeft() {
        var n = window.pageXOffset
                || document.documentElement.scrollLeft
                || document.body.scrollLeft || 0;
        return n;
    }
    //网页可见区域宽
    function EV_myClientWidth() {
        var n = document.documentElement.clientWidth
                || document.body.clientWidth || 0;
        return n;
    }
    //网页可见区域高
    function EV_myClientHeight() {
        var n = document.documentElement.clientHeight
                || document.body.clientHeight || 0;
        return n;
    }
</script>

<script>

    function fileChange(file) {
        var files = file.files;
        console.log(files);
        if (0 < files.length) {
            var name = "";
            for (var x = 0; x < files.length; x++) {
                console.log(files[x]);
                name += files[x].name + ",";
            }
            $(".showFileName").html(name.substring(0, name.length - 1));
        } else {
            $(".showFileName").html("您未选择上传文件！");
        }


//        console.log(filePath);
//        if(filePath.indexOf("jpg")!=-1 || filePath.indexOf("png")!=-1){
//            var arr=filePath.split('\\');
//            var fileName=arr[arr.length-1];
//            $(".showFileName").html(fileName);
//        }else{
//            $(".showFileName").html("您未上传文件，或者您上传文件类型有误！");
//            return false
//        }
    }

    var fileSize = 0;
    function sub() {
        var options = {
            url: '/uploadFile/upload ',
            beforeSubmit: function (data) {

            },  //提交前处理
            success: function (data) {
                var imgs = eval(data).files;
                $("#upload_imgs").empty();
                for (index in imgs) {
                    var img = "<li id='" + imgs[index].name + "'><img src=\"" + imgs[index].thumbnailUrl + "\"  class=\"img-rounded\"/>"
                            + "<i onclick='delete_img(\"" + imgs[index].deleteUrl + "\", \"" + imgs[index].name +
                            "\", this)' class=\"glyphicon glyphicon-trash\"></i></li>";
                    $("#ok-img-result").attr("data-result", imgs[index].thumbnailUrl);
                    $("#upload_imgs").append(img);
                }
                fileSize++;
                $(".showFileName").html("您未选择上传文件！");
            },  //处理完成
            resetForm: true,
            dataType: 'json'
        };
        $('#registerForm').ajaxSubmit(options);
        return false;
    }

    function ok(eve) {
        console.log(eve);
        $(".folder-img-box").empty();
        $(".folder-img-box").html($("#upload_imgs").html());
        $("#folder").val(eve.attr("data-result"));
        EV_closeAlert();
    }

    function delete_img(url, filename, i) {
        $.ajax({
            type: "post",
            url: url,
            data: "",
            success: function (data) {
                var imgs = eval(data).files;
                if (null != imgs) {
                    imgs.forEach(function (e) {
                        if (e[filename]) {
                            $(i).parent("li").remove();
                        }
                    })
                }
                $("#folder").val("");
                $("#ok-img-result").attr("data-result", "");
            }
        })
    }

    $.datetimepicker.setLocale('en');
    $('#registerDate').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
</script>
</body>
</html>