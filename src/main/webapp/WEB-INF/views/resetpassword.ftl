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
    <title>Car 公司用户系统 - 找回密码</title>


    <link href="[@spring.url '/resources/assets/style/bootstrap.css'/]" rel="stylesheet">
    <link rel="stylesheet" href="[@spring.url '/resources/assets/style/font-awesome.css'/]">
    <link rel="stylesheet" href="[@spring.url '/resources/assets/file_upload/file-upload.css'/]">
    <link href="[@spring.url '/resources/assets/style/style.css'/]" rel="stylesheet">
</head>

<body>

<div class="admin-form">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <!-- Widget starts -->
                <div class="widget wred">
                    <div class="widget-head">
                        <i class="icon-lock"></i> 用户找回密码
                    </div>
                    <div class="widget-content">
                    [@mc.showAlert /]
                        <div class="padd">
                            <form class="form-horizontal" action="[@spring.url '/reset_password'/]" method="post">
                            [@spring.bind "command.userName"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-3" for="userName">用户名</label>
                                    <div class="col-lg-9">
                                        <input type="text" class="form-control" id="userName" name="userName"
                                               value="${command.userName!}" placeholder="用户名" required/>
                                    [@spring.showErrors "userName"/]
                                    </div>
                                </div>

                            [@spring.bind "command.verificationCode"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-3" for="userName">验证码</label>
                                    <div class="col-lg-9">
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
                                    <label class="control-label col-lg-3" for="password">密码</label>
                                    <div class="col-lg-9">
                                        <input type="password" class="form-control" id="password" minlength="6"
                                               name="password" value="${command.password!}" placeholder="密码" required/>
                                    [@spring.showErrors "password"/]
                                    </div>
                                </div>

                            [@spring.bind "command.password"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-3" for="confirmPassword">确认密码</label>
                                    <div class="col-lg-9">
                                        <input type="password" class="form-control" id="confirmPassword"
                                               placeholder="确认密码"
                                               name="confirmPassword" value="${command.confirmPassword!}"
                                               onchange="checkPasswords()" required/>
                                    [@spring.showErrors "password"/]
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-lg-9 col-lg-offset-3">
                                        <button type="submit" class="btn btn-danger">提交</button>
                                    </div>
                                </div>
                                <br/>
                            </form>

                        </div>
                    </div>
                    <div class="widget-foot">
                        <a href="[@spring.url "/login"/]">登录</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="[@spring.url '/resources/assets/js/jquery.js'/]"></script>
<script>

    function checkPasswords() {
        var pass1 = document.getElementById("password");
        var pass2 = document.getElementById("confirmPassword");

        if (pass1.value != pass2.value)
            pass2.setCustomValidity("两次输入的密码不匹配");
        else
            pass1.setCustomValidity("");
    }

    $("#send_verification_code").click(function () {
        var userName = $("#userName").val();
        var _this = $(this);
        var re = /^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}|177\\d{8}$/;
        if (re.test(userName)) {
            $.ajax({
                type: "post",
                url: "/sms/send_reset_password",
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

</body>
</html>