[@override name="title"]订单信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
订单信息
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        [@mc.showAlert /]
        <div class="col-md-12">
            <div class="btn-group">
                <a class="btn btn-success btn-lg" href="/order/list">返回列表</a>
            </div>
            <ul class="am-data">
                <li><span class="text2">订单号</span><span class="text3">${order.orderNumber!}</span></li>
                <li><span class="text2">下单人</span><span class="text3">${order.orderUser.userName!}</span></li>
                <li><span class="text2">下单时间</span><span class="text3">${order.createDate!}</span></li>
                <li><span class="text2">接单人</span><span class="text3">${order.receiveUser.userName!}</span></li>
                <li><span class="text2">接单时间</span><span class="text3">${order.receiveDate!}</span></li>
                <li><span class="text2">预约时间</span><span class="text3">${order.subscribeDate!}</span></li>
                <li><span class="text2">开始时间</span><span class="text3">${order.beginTime!}</span></li>
                <li><span class="text2">类型</span><span class="text3">${(order.driverType.getName())!}</span></li>
                <li><span class="text2">订单完成时间</span><span class="text3">${order.endTime!}</span></li>
                <li><span class="text2">应付</span><span class="text3">${order.shouldMoney!}</span></li>
                <li><span class="text2">调度费</span><span class="text3">${order.extraMoney!}</span></li>
                <li><span class="text2">支付时间</span><span class="text3">${order.payTime!}</span></li>
                <li><span class="text2">订单状态</span><span class="text3">${(order.orderStatus.getName())!}</span></li>
                <li><span class="text2">评价状态</span><span class="text3">${(order.evaluateStatus.getName())!}</span></li>
                <li><span class="text2">开始地址</span><span class="text3">${order.startAddress!}</span></li>
                <li><span class="text2">结束地址</span><span class="text3">${order.endAddress!}</span></li>
            </ul>
        </div>
    </div>
</div>
[/@override]

[@extends name="/decorator.ftl"/]