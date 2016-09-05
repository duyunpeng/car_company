[@override name="title"]举报信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
举报信息
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        [@mc.showAlert /]
        <div class="col-md-12">
            <ul class="am-data">
                <li><span class="text2">举报人</span><span class="text3">${report.reportUser.userName!}</span></li>
                <li><span class="text2">举报订单</span><span class="text3">${report.order.orderNumber!}</span></li>
                <li><span class="text2">举报时间</span><span class="text3">${report.reportTime!}</span></li>
                <li><span class="text2">开始处理时间</span><span class="text3">${report.startDealTime!}</span></li>
                <li><span class="text2">处理完成时间</span><span class="text3">${report.endDealTime!}</span></li>
                <li><span class="text2">说明</span><span class="text3">${report.description!}</span></li>
                <li><span class="text2">状态</span><span class="text3">${report.status.getName()!}</span></li>
            </ul>
        </div>
    </div>
</div>
[/@override]

[@extends name="/decorator.ftl"/]