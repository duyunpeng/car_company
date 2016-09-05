[@override name="title"]修改车辆信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
修改车辆信息
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        <div class="col-md-12">
            [@mc.showAlert /]
            <form class="form-horizontal" action="/car/edit" method="post">

                <input type="hidden" name="id" value="${car.id!command.id}">
                <input type="hidden" name="version" value="${car.version!command.version}">

                [@spring.bind "command.name"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="name"> 车辆名称* </label>

                    <div class="col-sm-9">
                        <input type="text" id="name" name="name"
                               value="${car.name!command.name}"
                               placeholder="车辆名称" class="form-control" required/>
                        [@spring.showErrors "name"/]
                    </div>
                </div>

                [@spring.bind "command.carNumber"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="carNumber"> 车牌号* </label>

                    <div class="col-sm-9">
                        <input type="text" id="carNumber" name="carNumber"
                               value="${car.carNumber!ommand.carNumber}"
                               placeholder="车牌号" class="form-control" required/>
                        [@spring.showErrors "carNumber"/]
                    </div>
                </div>

                [@spring.bind "command.carType"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="carType"> 车辆类型* </label>

                    <div class="col-sm-9">
                        <select id="carType" name="carType" class="form-control" required>
                            [#assign carType = (car.carType!command.carType)?default("") /]
                            <option value="">请选择</option>
                            <option value="ECONOMY" [@mc.selected carType "ECONOMY"/]>经济型</option>
                            <option value="COMFORT" [@mc.selected carType "COMFORT"/]>舒适型</option>
                            <option value="BUSINESS" [@mc.selected carType "BUSINESS"/]>商务型</option>
                            <option value="LUXURY" [@mc.selected carType "LUXURY"/]>豪华型</option>
                        </select>
                        [@spring.showErrors "carType"/]
                    </div>
                </div>


                <div>
                    <button class="btn btn-info" type="submit">
                        修改
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
[/@override]
[@extends name="/decorator.ftl"/]