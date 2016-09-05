[@override name="title"]修改计费模板信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
修改计费模板信息
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        <div class="col-md-12">
            [@mc.showAlert /]
            <form class="form-horizontal" action="/billing/edit" method="post">

                <input type="hidden" value="${billing.id!command.id}" name="id"/>
                <input type="hidden" value="${billing.version!command.version}" name="version"/>

                [@spring.bind "command.kmBilling"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 每公里计费* </label>

                    <div class="col-sm-9">
                        <input type="text" id="form-field-1"
                               onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" name="kmBilling"
                               value="${billing.kmBilling!command.kmBilling}"
                               placeholder="每公里计费" class="form-control" required/>
                        [@spring.showErrors "kmBilling"/]
                    </div>
                </div>

                [@spring.bind "command.startKm"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 起步公里* </label>

                    <div class="col-sm-9">
                        <input type="text" id="form-field-1" name="startKm"
                               value="${billing.startKm!command.startKm}"
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
                               value="${billing.minuteBilling!command.minuteBilling}"
                               placeholder="每分钟计费" class="form-control" required/>
                        [@spring.showErrors "minuteBilling"/]
                    </div>
                </div>

                [@spring.bind "command.startMin"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 起步分钟* </label>

                    <div class="col-sm-9">
                        <input type="text" id="form-field-1" name="startMin"
                               value="${billing.startMin!command.startMin}"
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
                               value="${billing.startingPrice!command.startingPrice}"
                               placeholder="每分钟计费" class="form-control" required/>
                        [@spring.showErrors "startingPrice"/]
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 司机类型* </label>

                    <div class="col-sm-9">
                    [#--<select class="form-control" id="driverType" name="driverType" required readonly>--]
                    [#--[#assign status = (billing.driverType!command.driverType)?default("") /]--]
                    [#--<option value="">请选择</option>--]
                    [#--<option value="GENERATION" [@mc.selected status "GENERATION"/]>代驾</option>--]
                    [#--<option value="LIMOUSINE" [@mc.selected status "LIMOUSINE"/]>专车</option>--]
                    [#--<option value="TAXI" [@mc.selected status "TAXI"/]>出租车</option>--]
                    [#--</select>--]
                    [#--[@spring.showErrors "driverType"/]--]
                        <input type="text" id="form-field-1" class="form-control"
                               value="${(billing.driverType.getName())!}" readonly/>
                    </div>
                </div>

                <div class="form-group carType">
                    [#if billing.carType??]
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 专车类型* </label>
                        <div class="col-sm-9">
                        [#--<select class="form-control" id="carType" name="carType" required readonly>--]
                        [#--[#assign status = (billing.carType!command.carType)?default("") /]--]
                        [#--<option value="">请选择</option>--]
                        [#--<option value="ECONMY" [@mc.selected status "ECONMY"/]>经济型</option>--]
                        [#--<option value="COMFORT" [@mc.selected status "COMFORT"/]>舒适型</option>--]
                        [#--<option value="BUSINESS" [@mc.selected status "BUSINESS"/]>商务型</option>--]
                        [#--<option value="LUXURY" [@mc.selected status "LUXURY"/]>豪华型</option>--]
                        [#--</select>--]
                            <input type="text" id="form-field-1" class="form-control"
                                   value="${(billing.carType.getName())!}" readonly/>
                        </div>
                    [/#if]
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
[/@override]
[@extends name="/decorator.ftl"/]
