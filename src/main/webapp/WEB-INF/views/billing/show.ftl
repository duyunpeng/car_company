[@override name="title"]计费模板信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
公司信息
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        [@mc.showAlert /]
        <div class="col-md-12">
            <ul class="am-data">
                <li><span class="text2">每公里收费</span><span>${billing.kmBilling!}</span></li>
                <li><span class="text2">起步公里</span><span>${billing.startKm!}</span></li>
                <li><span class="text2">每分钟收费</span><span>${billing.minuteBilling!}</span></li>
                <li><span class="text2">起步分钟</span><span>${billing.startMin!}</span></li>
                <li><span class="text2">起步价</span><span>${billing.startingPrice!}</span></li>
                <li><span class="text2">司机类型</span><span>${(billing.driverType.getName())!}</span></li>
                <li><span class="text2">专车类型</span><span>${(billing.carType.getName())!}</span></li>
                <li><span class="text2">状态</span><span>${(billing.status.getName())!}</span></li>
            </ul>
        </div>

    </div>
</div>
</div>
[/@override]

[@extends name="/decorator.ftl"/]