[@override name="title"]车辆管理-查看车辆信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/car/list'/]">车辆管理</a></li>
<li class="active">查看车辆</li>
[/@override]

[@override name="pageHeaderTitle"]
查看车辆信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            [#if car??]
                <div class="col-xs-12">
                    <li><a href="/car/edit/${car.id!}" class="btn btn-primary btn-block">修改</a></li>
                </div>
                <ul class="am-data">
                    <li><span class="text2">车辆名称</span><span>${car.name!}</span></li>
                    <li><span class="text2">车牌号</span><span>${car.carNumber!}</span></li>
                    <li><span class="text2">司机</span><span>${car.driver.userName!}</span></li>
                    <li><span class="text2">类型</span><span>${(car.carType.getName())!}</span></li>
                </ul>
            [#else]
                <div>
                    <h1>没有车辆信息</h1>
                    <li><a class="btn btn-primary" href="[@spring.url '/car/create'/]">添加车辆</a></li>
                </div>
            [/#if]

        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/driver/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]