[@override name="title"]待审核司机列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
待审核司机列表
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            [@mc.showAlert /]
            <div class="table-responsive">
                <div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
                    <table id="sample-table-2"
                           class="table table-striped table-bordered table-hover dataTable text-center">
                        <thead>
                        <tr role="row">
                            <th>账号</th>
                            <th>性别</th>
                            <th>类型</th>
                            <th>认证状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>


                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                            [#if pagination.data??]
                                [#list pagination.data as driver ]
                                <tr class="even">
                                    <td>${driver.userName!}</td>
                                    <td>${(driver.sex.getName())!}</td>
                                    <td>${(driver.driverType.getName())!}</td>
                                    <td>${(driver.authStatus.getName())!}</td>
                                    <td>
                                        <div class="btn-group">
                                            <a class="btn btn-success"
                                               href="[@spring.url '/driver/show/${driver.id!}'/]">查看</a>
                                            [#if driver.authStatus == "AUTH_WAIT"]
                                                <a class="btn btn-success"
                                                   href="[@spring.url '/driver/auth_driver?id=${driver.id!}&version=${driver.version!}'/]">审核通过</a>
                                            [/#if]
                                        </div>
                                    </td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>

                    [#if pagination??]
                        [@mc.showPagination '/driver/authlist' /]
                    [/#if]

                </div>
            </div>
        </div>
    </div>
</div>
[/@override]

[@extends name="/decorator.ftl"/]