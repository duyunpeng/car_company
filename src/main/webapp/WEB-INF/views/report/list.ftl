[@override name="title"]举报列表[/@override]

[@override name="topResources"]
    [@super /]
<link rel="stylesheet" type="text/css"
      href="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.css'/]"/>
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
举报列表
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            [@mc.showAlert /]
            <div class="table-responsive">
                <div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
                    <!-- 查询条件 -->
                    <div class="row">
                        <form>
                            <div class="col-sm-12">
                                <div id="sample-table-2_length" class="dataTables_length">
                                    <label>开始<input type="text" value="${command.startDealTime!}" id="startDealTime" name="startDealTime"/></label>

                                    <label>结束<input type="text" value="${command.endDealTime!}" id="endDealTime"
                                                    name="endDealTime"/></label>

                                    <label>举报人<input type="text" value="${command.reportUser!}"
                                                     name="reportUser"/></label>
                                    <label>举报订单<input type="text" value="${command.order!}"
                                                      name="order"/></label>
                                    <label>订单状态
                                        <select name="status">
                                            [#assign status = (command.status!)?default("") /]
                                            <option value="">全部</option>
                                            <option value="PENDING" [@mc.selected status "PENDING"/]>待处理</option>
                                            <option value="IN_PROCESS" [@mc.selected status "IN_PROCESS"/]>正在处理
                                            </option>
                                            <option value="FIGURE_OUT" [@mc.selected status "FIGURE_OUT"/]>处理完成</option>
                                        </select>
                                    </label>
                                    <label>
                                        <button type="submit" class="btn btn-app btn-sm btn-success">查询</button>
                                    </label>
                                </div>
                            </div>
                        </form>
                    </div>
                    <table id="sample-table-2"
                           class="table table-striped table-bordered table-hover dataTable text-center">
                        <thead>
                        <tr role="row">
                            <th>举报人</th>
                            <th>举报订单</th>
                            <th>举报时间</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>说明</th>
                            <th>处理结果</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>


                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                            [#if pagination.data??]
                                [#list pagination.data as report ]
                                <tr class="even">
                                    <td>${report.reportUser.userName!}</td>
                                    <td>${report.order.orderNumber!}</td>
                                    <td>${report.reportTime!}</td>
                                    <td>${report.startDealTime!}</td>
                                    <td>${report.endDealTime!}</td>
                                    <td>${report.description!}</td>
                                    <td>${report.handleResult!}</td>
                                    <td>${(report.status.getName())!}</td>
                                    <td>
                                    [#--<div class="btn-group">--]
                                    [#--<ul>--]
                                    [#--<li>--]
                                    [#--<a class="blue" href="[@spring.url '/report/show/${report.id!}'/]">查看</a>--]
                                    [#--</li>--]
                                    [#--</ul>--]
                                    [#--</div>--]


                                        <div class="btn-group">
                                            <ul>
                                                <li>
                                                    <a class="blue" href="[@spring.url '/report/show/${report.id!}'/]">查看</a>
                                                </li>

                                                [#if report.status != "FIGURE_OUT"]
                                                    [#if report.status == "PENDING"]
                                                        <li>
                                                            <a class="pink"
                                                               href="[@spring.url '/report/deal?id=${report.id!}&version=${report.version}'/]">处理</a>

                                                        </li>
                                                    [#else ]
                                                        <li>
                                                            <a class="pink"
                                                               onclick="EV_modeAlert('dialog', '${report.id!}', '${report.version}')">完成处理</a>
                                                        </li>
                                                    [/#if]
                                                [/#if]
                                            </ul>
                                        </div>

                                    </td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>

                    [#if pagination??]
                        [@mc.showPagination '/report/list?reportUser=${command.reportUser!}&order=${command.order}&status=${command.status!}' /]
                    [/#if]

                </div>
            </div>
        </div>
    </div>

</div>


<div id="dialog"
     style="width:40%; background: oldlace ;   padding: 10px 10px;   color: #333; left: 10%;   border: 1px solid #eee;    min-height: 200px;    display: none;">
    <div class="clearfix">
        <div style="padding-bottom: 20px;">
            <span>处理结果</span>
            <button class="pull-right btn" onclick="EV_closeAlert()">关闭</button>
        </div>
        <div class="col-md-12">
            <form class="form-horizontal" action="/report/finish" method="POST">
                <div class="form-group">
                    <div id="form_hidden"></div>
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 处理结果* </label>

                    <div class="col-sm-12">
                        <textarea type="text" style="height: 150px;" id="form-field-1" name="handleResult"
                                  class="col-xs-10 col-sm-9"
                                  required>${report.handleResult!command.handleResult}</textarea>
                    </div>
                </div>
                <div class="col-xs-12 center">
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
<script src="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.full.js'/]"></script>
<script type="text/javascript">
    //用来记录要显示的DIV的ID值
    var EV_MsgBox_ID = ""; //重要

    //弹出对话窗口(msgID-要显示的div的id)
    function EV_modeAlert(msgID, id, version) {

        var append = "<input type=\"hidden\" name=\"id\" value=\"" + id + "\"/>"
                + "<input type=\"hidden\" name=\"version\" value=\"" + version + "\"/>";

        $("#form_hidden").append(append);

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

    $.datetimepicker.setLocale('en');
    $('#startDealTime').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
    $('#endDealTime').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
</script>
[/@override]

[@extends name="/decorator.ftl"/]