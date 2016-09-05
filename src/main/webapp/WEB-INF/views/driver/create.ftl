[@override name="title"]创建司机[/@override]

[@override name="topResources"]
    [@super /]
<link rel="stylesheet" type="text/css"
      href="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.css'/]"/>
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
创建司机
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        <div class="col-md-12">
            [@mc.showAlert /]
            <form class="form-horizontal" action="/driver/create" id="form-create" method="post">

                [@spring.bind "command.userName"/]
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="form-field-1"> 用户名* </label>

                    <div class="col-sm-7">
                        <input type="text" id="userName" name="userName" value="${command.userName!}"
                               placeholder="用户名(手机号)" pattern="^1[345678][0-9]{9}$"
                               class="form-control col-xs-10 col-sm-5"
                               required/>
                        [@spring.showErrors "userName"/]
                    </div>
                </div>

                [@spring.bind "command.password"/]
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="form-field-1"> 密码* </label>

                    <div class="col-sm-7">
                        <input type="password" id="password" name="password"
                               placeholder="密码" class="form-control col-xs-10 col-sm-5" minlength="6" required/>
                        [@spring.showErrors "password"/]
                    </div>
                </div>

                [@spring.bind "command.confirmPassword"/]
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="form-field-1"> 确认密码* </label>

                    <div class="col-sm-7">
                        <input type="password" id="confirmPassword" name="confirmPassword"
                               placeholder="确认密码" class="form-control col-xs-10 col-sm-5" minlength="6"
                               onchange="checkPasswords()" required/>
                        [@spring.showErrors "confirmPassword"/]
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="form-field-1"> 司机名字 </label>

                    <div class="col-sm-7">
                        <input type="text" id="name" name="name"
                               placeholder="司机名字" class="form-control col-xs-10 col-sm-5" required/>
                    </div>
                </div>

                [@spring.bind "command.phone"/]
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="identityCardPic"> 电话号码* </label>

                    <div class="col-sm-7">
                        <input type="text" id="phone" name="phone"
                               placeholder="电话号码" pattern="^1[345678][0-9]{9}$" class="form-control col-xs-10 col-sm-5"
                               required/>
                        [@spring.showErrors "phone"/]
                    </div>
                </div>

                [@spring.bind "command.startDriveDate"/]
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="form-field-1"> 开始驾驶时间* </label>

                    <div class="col-sm-7">
                        <input type="text" id="startDriveDate" name="startDriveDate"
                               placeholder="开始驾驶时间" class="form-control col-xs-10 col-sm-5" required/>
                        [@spring.showErrors "startDriveDate"/]
                    </div>
                </div>

                [@spring.bind "command.identityCardPic"/]
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="identityCardPic"> 身份证照片* </label>

                    <div class="col-sm-7">
                        <a class="btn btn-sm btn-primary left input-file-hidden"
                           id="identityCardPicUpload">点击上传照片
                        </a>
                        <input type="hidden" id="identityCardPic" name="identityCardPic"
                               placeholder="身份证照片" class="form-control col-xs-10 col-sm-5" required/>
                        <p class="img-box"></p>
                        [@spring.showErrors "identityCardPic"/]
                    </div>
                </div>

                [@spring.bind "command.drivingLicencePic"/]
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="identityCardPic"> 驾驶证照片* </label>

                    <div class="col-sm-7">
                        <a class="btn btn-sm btn-primary left input-file-hidden"
                           id="drivingLicencePicUpload">点击上传照片
                        </a>
                        <input type="hidden" id="drivingLicencePic" name="drivingLicencePic"
                               placeholder="驾驶证照片" class="form-control col-xs-10 col-sm-5" required/>
                        <p class="img-box"></p>
                        [@spring.showErrors "drivingLicencePic"/]
                    </div>
                </div>

                [@spring.bind "command.driverType"/]
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="form-field-1"> 司机类型* </label>

                    <div class="col-sm-7">
                        <select class="form-control" id="driverType" name="driverType" required>
                            [#assign status = (command.driverType)?default("") /]
                            <option value="">请选择</option>
                            <option value="GENERATION" [@mc.selected status "GENERATION"/]>代驾</option>
                            <option value="LIMOUSINE" [@mc.selected status "LIMOUSINE"/]>专车</option>
                            <option value="TAXI" [@mc.selected status "TAXI"/]>出租车</option>
                        </select>
                        [@spring.showErrors "driverType"/]
                    </div>
                </div>

                [@spring.bind "command.travelPic"/]
                <div class="form-group hidden">
                    <label class="col-sm-5 control-label no-padding-right" for="identityCardPic"> 行驶证照片* </label>

                    <div class="col-sm-7">
                        <a class="btn btn-sm btn-primary left input-file-hidden"
                           id="travelPicUpload">点击上传照片
                        </a>
                        <input type="hidden" id="travelPic" name="travelPic"
                               placeholder="行驶证照片" class="form-control col-xs-10 col-sm-5" required/>
                        <p class="img-box"></p>
                        [@spring.showErrors "travelPic"/]
                    </div>
                </div>

                [@spring.bind "command.drivingLicenceType"/]
                <div class="form-group hidden">
                    <label class="col-sm-5 control-label no-padding-right" for="drivingLicenceType"> 驾驶证类型* </label>

                    <div class="col-sm-7">
                        <select class="form-control" id="drivingLicenceType" name="drivingLicenceType">
                            <option value="">请选择</option>
                            <option value="A1">A1</option>
                            <option value="A2">A2</option>
                            <option value="B1">B1</option>
                            <option value="B2">B2</option>
                            <option value="C1">C1</option>
                            <option value="C2">C2</option>
                        </select>
                        [@spring.showErrors "drivingLicenceType"/]
                    </div>
                </div>

                [@spring.bind "command.businessPic"/]
                <div class="form-group hidden">
                    <label class="col-sm-5 control-label no-padding-right" for="identityCardPic"> 营业资格证照片* </label>

                    <div class="col-sm-7">
                        <a class="btn btn-sm btn-primary left input-file-hidden"
                           id="businessPicUpload">点击上传照片
                        </a>
                        <input type="hidden" id="businessPic" name="businessPic"
                               placeholder="营业资格证照片" class="form-control col-xs-10 col-sm-5" required/>
                        <p class="img-box"></p>
                        [@spring.showErrors "businessPic"/]
                    </div>
                </div>

                [@spring.bind "command.workPic"/]
                <div class="form-group hidden">
                    <label class="col-sm-5 control-label no-padding-right" for="identityCardPic"> 从业资格证照片* </label>

                    <div class="col-sm-7">
                        <a class="btn btn-sm btn-primary left input-file-hidden" id="workPicUpload">
                            点击上传照片
                        </a>
                        <input type="hidden" id="workPic" name="workPic"
                               placeholder="从业资格证照片" class="form-control col-xs-10 col-sm-5" required/>
                        <p class="img-box"></p>
                        [@spring.showErrors "workPic"/]
                    </div>
                </div>

                [@spring.bind "command.bankCardNo"/]
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="identityCardPic"> 银行卡号* </label>

                    <div class="col-sm-7">
                        <input type="text" id="bankCardNo" name="bankCardNo"
                               placeholder="银行卡号" class="form-control col-xs-10 col-sm-5" required/>
                        [@spring.showErrors "bankCardNo"/]
                    </div>
                </div>

                [@spring.bind "command.bankName"/]
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="bankName"> 银行名称* </label>

                    <div class="col-sm-7">
                        <input type="text" id="bankName" name="bankName"
                               placeholder="银行卡号" class="form-control col-xs-10 col-sm-5" required/>
                        [@spring.showErrors "bankName"/]
                    </div>
                </div>

                <div class="form-group">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        创建
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

[#--文件上传进度--]
<div class="file_upload_load"></div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/assets/js/jquery.js'/]"></script>
<script src="[@spring.url '/resources/assets/js/upload/webuploader.js'/]"></script>
<script src="[@spring.url '/resources/assets/js/layer/layer.js'/]"></script>
<script src="[@spring.url '/resources/assets/app/js/driverCreate.js'/]"></script>
<script src="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.full.js'/]"></script>
<script type="text/javascript">
    function checkPasswords() {
        var pass1 = document.getElementById("password");
        var pass2 = document.getElementById("confirmPassword");

        if (pass1.value != pass2.value)
            pass2.setCustomValidity("两次输入的密码不匹配");
        else
            pass2.setCustomValidity("");
    }

    var $travelPic = $("#travelPic");
    var $businessPic = $("#businessPic");
    var $workPic = $("#workPic");
    var $drivingLicenceType = $("#drivingLicenceType");
    $("#driverType").change(function () {
        if ($(this).val() == "LIMOUSINE") {
            $travelPic.parent().parent().removeClass("hidden");
            $businessPic.parent().parent().addClass("hidden");
            $workPic.parent().parent().addClass("hidden");
            $drivingLicenceType.parent().parent().addClass("hidden");
            bindTravelPic();
        } else if ($(this).val() == "GENERATION") {
            $drivingLicenceType.parent().parent().removeClass("hidden");
            $travelPic.parent().parent().addClass("hidden");
            $businessPic.parent().parent().addClass("hidden");
            $workPic.parent().parent().addClass("hidden");
        } else if ($(this).val() == "TAXI") {
            $travelPic.parent().parent().removeClass("hidden");
            $businessPic.parent().parent().removeClass("hidden");
            $workPic.parent().parent().removeClass("hidden");
            $drivingLicenceType.parent().parent().addClass("hidden");
            bindWorkPic();
            bindBusinessPic();
            bindTravelPic();
        } else {
            $travelPic.parent().parent().addClass("hidden");
            $businessPic.parent().parent().addClass("hidden");
            $workPic.parent().parent().addClass("hidden");
            $drivingLicenceType.parent().parent().addClass("hidden");
        }
    })

    $("#form-create").submit(function () {
        if ($("#identityCardPic").val() == "") {
            layer.msg("身份证照片不能为空");
            return false;
        }
        if ($("#drivingLicencePic").val() == "") {
            layer.msg("驾驶证照片不能为空");
            return false;
        }
        if ($("#driverType").val() == "LIMOUSINE") {
            if ($travelPic.val() == "") {
                layer.msg("行驶证照片不能为空");
                return false;
            }
        } else if ($("#driverType").val() == "TAXI") {
            if ($travelPic.val() == "") {
                layer.msg("行驶证照片不能为空");
                return false;
            }
            if ($businessPic.val() == "") {
                layer.msg("营业证照片不能为空");
                return false;
            }
            if ($workPic.val() == "") {
                layer.msg("从业证照片不能为空");
                return false;
            }
        } else if ($("#driverType").val() == "GENERATION") {
            if ($("#drivingLicenceType").val() == "") {
                layer.msg("驾照类型不能为空");
                return false;
            }
        }
        return true;
    })

    $.datetimepicker.setLocale('en');
    $('#startDriveDate').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
</script>
[/@override]

[@extends name="/decorator.ftl"/]