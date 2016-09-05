[@override name="title"]订单列表[/@override]

[@override name="topResources"]
    [@super /]
<link rel="stylesheet" type="text/css"
      href="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.css'/]"/>
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
订单列表
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
                                    <label>订单号<input type="text" value="${command.orderNumber!}"
                                                     name="orderNumber"/></label>
                                    <label>订单状态
                                        <select name="orderStatus">
                                            [#assign status = (command.orderStatus!)?default("") /]
                                            <option value="">全部</option>
                                            <option value="WAIT_ORDER" [@mc.selected status "WAIT_ORDER"/]>待接单</option>
                                            <option value="HAS_ORDER" [@mc.selected status "HAS_ORDER"/]>已接单</option>
                                            <option value="START_ORDER" [@mc.selected status "START_ORDER"/]>已开始
                                            </option>
                                            <option value="WAIT_PAY" [@mc.selected status "WAIT_PAY"/]>待支付</option>
                                            <option value="SUCCESS" [@mc.selected status "SUCCESS"/]>完成</option>
                                            <option value="INVALID" [@mc.selected status "INVALID"/]>作废</option>
                                        </select>
                                    </label>
                                    <label>司机类型
                                        <select name="driverType">
                                            [#assign status = (command.driverType!)?default("") /]
                                            <option value="">全部</option>
                                            <option value="GENERATION" [@mc.selected status "GENERATION"/]>代驾</option>
                                            <option value="LIMOUSINE" [@mc.selected status "LIMOUSINE"/]>专车</option>
                                            <option value="TAXI" [@mc.selected status "TAXI"/]>出租车</option>
                                        </select>
                                    </label>
                                    <label>车辆类型
                                        <select name="carType">
                                            [#assign status = (command.carType!)?default("") /]
                                            <option value="">全部</option>
                                            <option value="ECONOMY" [@mc.selected status "ECONOMY"/]>经济型</option>
                                            <option value="COMFORT" [@mc.selected status "COMFORT"/]>舒适型</option>
                                            <option value="BUSINESS" [@mc.selected status "BUSINESS"/]>商务型</option>
                                            <option value="LUXURY" [@mc.selected status "LUXURY"/]>豪华型</option>
                                        </select>
                                    </label>
                                    <label>
                                        开始<input type="text" value="${command.startCreateDate!}" id="startCreateDate"
                                                 name="startCreateDate"/>
                                    </label>
                                    <label>
                                        结束<input type="text" value="${command.endCreateDate!}" id="endCreateDate"
                                                 name="endCreateDate"/>
                                    </label>
                                    <label>
                                        <button type="submit" class="btn btn-app btn-sm btn-success">查询</button>
                                    </label>
                                    <label><a
                                            href="/order/export_excel?orderNumber=${command.orderNumber!}&orderStatus=${command.orderStatus!}&driverType=${command.driverType!}&carType=${command.carType!}&startCreateDate=${command.startCreateDate}&endCreateDate=${command.endCreateDate}"
                                            type="submit" class="btn btn-app btn-sm btn-success">导出</a></label>
                                </div>
                            </div>
                        </form>
                    </div>
                    <table id="sample-table-2"
                           class="table table-striped table-bordered table-hover dataTable text-center">
                        <thead>
                        <tr role="row">
                            <th>订单号</th>
                            <th>下单人</th>
                            <th>接单人</th>
                            <th>下单时间</th>
                            <th>订单金额</th>
                            <th>订单状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>


                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                            [#if pagination.data??]
                                [#list pagination.data as order ]
                                <tr class="even">
                                    <td>${order.orderNumber!}</td>
                                    <td>${order.orderUser.userName!}</td>
                                    <td>${order.receiveUser.userName!}</td>
                                    <td>${order.createDate!}</td>
                                    <td>${order.shouldMoney!}</td>
                                    <td>${(order.orderStatus.getName())!}</td>
                                    <td>
                                        <div class="btn-group">
                                            <a class="btn btn-success btn-group-sm"
                                               href="[@spring.url '/order/show/${order.id!}'/]">查看</a>
                                            [#if order.orderStatus.getValue()==4 || order.orderStatus.getValue()==5]
                                                <a class="btn btn-success btn-group-sm"
                                                   href="[@spring.url '/order/way/${order.id!}'/]">线路</a>
                                            [/#if]
                                        </div>
                                    </td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>

                    [#if pagination??]
                        [@mc.showPagination '/order/list?orderNumber=${command.orderNumber!}&orderStatus=${command.orderStatus!}&driverType=${command.driverType!}&carType=${command.carType!}&startCreateDate=${command.startCreateDate!}&endCreateDate=${command.endCreateDate!}' /]
                    [/#if]

                </div>
            </div>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.full.js'/]"></script>
<script type="text/javascript">
    $.datetimepicker.setLocale('en');
    $('#startCreateDate').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
    $('#endCreateDate').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
</script>
[/@override]

[@extends name="/decorator.ftl"/]