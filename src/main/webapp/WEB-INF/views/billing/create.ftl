[@override name="title"]创建计费模板信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
创建计费模板信息
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        <div class="col-md-12">
            [@mc.showAlert /]
            <form class="form-horizontal" action="/billing/create" method="post">

                [@spring.bind "command.kmBilling"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 每公里计费* </label>

                    <div class="col-sm-9">
                        <input type="text" id="form-field-1"
                               onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" name="kmBilling"
                               value="${command.kmBilling}"
                               placeholder="每公里计费" class="form-control" required/>
                        [@spring.showErrors "kmBilling"/]
                    </div>
                </div>

                [@spring.bind "command.startKm"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 起步公里* </label>

                    <div class="col-sm-9">
                        <input type="text" id="form-field-1" name="startKm"
                               value="${command.startKm}"
                               placeholder="起步公里" class="form-control" required/>
                        [@spring.showErrors "startKm"/]
                    </div>
                </div>

                [@spring.bind "command.minuteBilling"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 每分钟计费* </label>

                    <div class="col-sm-9">
                        <input type="text" id="form-field-1"
                               onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" name="minuteBilling"
                               value="${command.minuteBilling}"
                               placeholder="每分钟计费" class="form-control" required/>
                        [@spring.showErrors "minuteBilling"/]
                    </div>
                </div>

                [@spring.bind "command.startMin"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 起步分钟* </label>

                    <div class="col-sm-9">
                        <input type="text" id="form-field-1" name="startMin"
                               value="${command.startMin}"
                               placeholder="起步分钟" class="form-control" required/>
                        [@spring.showErrors "startMin"/]
                    </div>
                </div>

                [@spring.bind "command.startingPrice"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 起步价* </label>

                    <div class="col-sm-9">
                        <input type="text" id="form-field-1"
                               onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" name="startingPrice"
                               value="${command.startingPrice}"
                               placeholder="每分钟计费" class="form-control" required/>
                        [@spring.showErrors "startingPrice"/]
                    </div>
                </div>

                [@spring.bind "command.driverType"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 司机类型* </label>

                    <div class="col-sm-9">
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

                <div class="form-group carType">

                </div>

                <div>
                    <button class="btn btn-info" type="submit">
                        创建
                    </button>
                    <button class="btn" type="reset">
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
    $(document).ready(function () {
        $("#driverType").change(function () {
            var val = $(this).val();
            if (val == "LIMOUSINE") {
                $(".carType").append('<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 专车类型* </label><div class="col-sm-9"><select class="form-control" id="carType" name="carType" required><option value="">请选择</option><option value="ECONOMY" >经济型</option><option value="COMFORT" >舒适型</option><option value="BUSINESS" >商务型</option><option value="LUXURY">豪华型</option></select></div>');

            }else{
                $(".carType").empty();
            }
        })
    });
</script>
[/@override]
[@extends name="/decorator.ftl"/]