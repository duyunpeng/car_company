[@override name="title"]司机列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
司机列表
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
                                    <label>司机<input type="text" value="${command.userName!}"
                                                    name="userName"/></label>
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
                            <th>用户名</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>余额</th>
                            <th>等级</th>
                            <th>举报次数</th>
                            <th>类型</th>
                            <th>司机状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                            [#if pagination.data??]
                                [#list pagination.data as driver ]
                                <tr class="even">
                                    <td>${driver.userName!}</td>
                                    <td>${driver.name!}</td>
                                    <td>${driver.sex!}</td>
                                    <td>${driver.balance!}</td>
                                    <td>${driver.level!}</td>
                                    <td>${driver.reportCount!}</td>
                                    <td>${(driver.driverType.getName())!}</td>
                                    <td>${(driver.status.getName())!}</td>
                                    <td>
                                        <div class="btn-group">
                                            <a class="btn btn-success"
                                               href="[@spring.url '/driver/show/${driver.id!}'/]">查看</a>
                                            [#if driver.driverType != "GENERATION"]
                                                <a class="btn btn-success"
                                                   href="[@spring.url '/car/show/${driver.id!}'/]">查看车辆</a>
                                            [/#if]
                                            <a class="btn btn-success"
                                               href="[@spring.url '/driver/edit/${driver.id}'/]">编辑</a>
                                        </div>
                                    </td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>

                    [#if pagination??]
                        [@mc.showPagination '/driver/list?name=${command.name!}' /]
                    [/#if]

                </div>
            </div>
        </div>
    </div>
</div>
[/@override]

[@extends name="/decorator.ftl"/]