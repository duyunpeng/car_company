[@override name="title"]订单路线[/@override]

[@override name="topResources"]
    [@super /]
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=eYf9sA6yVTFHlh9ytU4a0EYY"></script>
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
订单路线
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
                <div id="allmap" style="height: 600px"></div>
            </ul>
        </div>
    </div>
</div>
[/@override]
[@override name="bottomResources"]
<script type="text/javascript">

    // 百度地图API功能
    var map = new BMap.Map("allmap");

    map.enableScrollWheelZoom(true);

    var p1 = new BMap.Point(${wayPoints[0].lon},${wayPoints[0].lat});
    var p2 = new BMap.Point(${wayPoints[wayPoints?size -1].lon},${wayPoints[wayPoints?size-1].lat});

    var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});
    driving.search(p1,p2,{waypoints:[
        [#list wayPoints as point]
            new BMap.Point(${point.lon},${point.lat}),
        [/#list]
    ]})
</script>
[/@override]
[@extends name="/decorator.ftl"/]