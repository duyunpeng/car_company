[@override name="title"]修改密码[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
<a href="/company/info">公司信息</a>
<span class="glyphicon glyphicon-chevron-right"></span>
修改密码
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        <div class="col-md-12">
            [@mc.showAlert /]
            <form class="form-horizontal" action="/company/update_password" method="post">

                <input type="hidden" name="id" value="${company.id!command.id}"/>
                <input type="hidden" name="version" value="${company.version!command.version}"/>

                [@spring.bind "command.oldPassword"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 旧密码* </label>

                    <div class="col-sm-9">
                        <input type="password" id="form-field-1" name="oldPassword"
                               placeholder="旧密码" class="col-xs-10 col-sm-5" required/>
                        [@spring.showErrors "oldPassword"/]
                    </div>
                </div>

                [@spring.bind "command.newPassword"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 新密码* </label>

                    <div class="col-sm-9">
                        <input type="password" id="form-field-1" name="newPassword"
                               placeholder="新密码" class="col-xs-10 col-sm-5" required/>
                        [@spring.showErrors "newPassword"/]
                    </div>
                </div>

                [@spring.bind "command.confirmNewPassword"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 确认新密码* </label>

                    <div class="col-sm-9">
                        <input type="password" id="form-field-1" name="confirmNewPassword"
                               placeholder="确认新密码" class="col-xs-10 col-sm-5" onchange="checkPasswords()" required/>
                        [@spring.showErrors "confirmNewPassword"/]
                    </div>
                </div>

                <div class="form-group col-xs-8">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        修改
                    </button>
                    <button class="btn" type="reset">
                        <i class="icon-undo bigger-110"></i>
                        重置
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script>
    function checkPasswords() {
        var pass1 = document.getElementById("newPassword");
        var pass2 = document.getElementById("confirmNewPassword");

        if (pass1.value != pass2.value)
            pass1.setCustomValidity("两次输入的密码不匹配");
        else
            pass1.setCustomValidity("");
    }
</script>
[/@override]

[@extends name="/decorator.ftl"/]