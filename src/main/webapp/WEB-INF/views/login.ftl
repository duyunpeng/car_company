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
    <title>Car 公司用户系统 - 登录</title>


    <link href="[@spring.url '/resources/assets/style/bootstrap.css'/]" rel="stylesheet">
    <link rel="stylesheet" href="[@spring.url '/resources/assets/style/font-awesome.css'/]">
    <link href="[@spring.url '/resources/assets/style/style.css'/]" rel="stylesheet">
    <script src="[@spring.url '/resources/assets/js/jquery.js'/]"></script>
    <link rel="stylesheet" href="[@spring.url '/resources/assets/style/reset.css/' /]"/>
</head>

<body>

<div class="admin-form">
    <div class="container">

        <div class="row">
            <div class="col-md-12">
                <!-- 内容开始 -->
                <div class="widget worange">
                    <!-- 头部 -->
                    <div class="widget-head">
                        <i class="icon-lock"></i> 欢迎登录
                    </div>

                    <div class="widget-content">
                    [@mc.showAlert /]
                        <div class="padd">
                            <!-- 登录 -->
                            <form class="form-horizontal" action="/login" method="post">
                                <!-- 帐号 -->
                            [@spring.bind "user.username" /]
                                <div class="form-group">
                                    <label class="control-label col-lg-3" for="username">帐号</label>
                                    <div class="col-lg-9">
                                        <input type="text" class="form-control" name="username" id="username"
                                               placeholder="username"/>
                                    [@spring.showErrors "username" /]
                                    </div>
                                </div>
                                <!-- 密码 -->
                            [@spring.bind "user.password"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-3" for="password">密码</label>
                                    <div class="col-lg-9">
                                        <input type="password" class="form-control" name="password" id="password"
                                               placeholder="password"/>

                                    [@spring.showErrors "password"/]
                                    </div>
                                </div>

                            [@spring.bind "user.verificationCode"/]
                                <div class="form-group">
                                    <label class="control-label col-lg-3" for="verificationCode">验证码</label>
                                    <div class="col-lg-9">
                                        <input type="text" class="form-control" name="verificationCode"
                                               placeholder="验证码"/>
                                    [@mc.verificationCode /]
                                    </div>

                                </div>
                                <div class="col-lg-9 col-lg-offset-2">
                                    <button type="submit" class="btn btn-danger">登录</button>
                                </div>
                                <br/>
                            </form>

                        </div>
                    </div>

                    <div class="widget-foot">
                        忘记密码？ <a href="[@spring.url '/register'/]">注册</a> / <a href="/reset_password">找回密码</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>