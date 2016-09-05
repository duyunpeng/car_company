[@override name="title"]计费模板列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
计费模板列表
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            [@mc.showAlert /]
            <div class="table-responsive">
                <div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
                    <div class="row">
                        <div class="btn-group">
                            <a class="btn btn-success btn-lg" href="/billing/create">创建计费模板</a>
                        </div>
                    </div>
                    <table id="sample-table-2"
                           class="table table-striped table-bordered table-hover dataTable text-center">
                        <thead>
                        <tr role="row">
                            <th>每公里计费</th>
                            <th>起步公里</th>
                            <th>每分钟计费</th>
                            <th>起步分钟</th>
                            <th>起步价</th>
                            <th>司机类型</th>
                            <th>专车类型</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                            [#if pagination.data??]
                                [#list pagination.data as billing ]
                                <tr class="even">
                                    <td>${billing.kmBilling!}</td>
                                    <td>${billing.startKm!}</td>
                                    <td>${billing.minuteBilling!}</td>
                                    <td>${billing.startMin!}</td>
                                    <td>${billing.startingPrice!}</td>
                                    <td>${(billing.driverType.getName())!}</td>
                                    <td>${(billing.carType.getName())!}</td>
                                    <td>${(billing.status.getName())!}</td>
                                    <td>
                                        <div class="btn-group">
                                            <a class="btn btn-success"
                                               href="[@spring.url '/billing/show/${billing.id!}'/]">查看</a>
                                            <a class="btn btn-success"
                                               href="[@spring.url '/billing/edit/${billing.id}'/]">编辑</a>
                                        </div>
                                    </td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>

                    [#if pagination??]
                        [@mc.showPagination '/driver/list?company=${command.company!}&name=${command.name!}' /]
                    [/#if]

                </div>
            </div>
        </div>
    </div>
</div>
[/@override]

[@extends name="/decorator.ftl"/]