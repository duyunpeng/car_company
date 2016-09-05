[@override name="title"]救援列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
救援列表
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
                                    <label>申请人<input type="text" value="${command.applyUser!}"
                                                     name="applyUser"/></label>

                                    <label>救援类型
                                        <select name="rescueType">
                                            [#assign type = (command.rescueType!)?default("") /]
                                            <option value="">全部</option>
                                            <option value="CHILDREN" [@mc.selected type "CHILDREN"/]>留守儿童</option>
                                            <option value="OLD_MAN" [@mc.selected type "OLD_MAN"/]>空巢老人</option>
                                            <option value="OLD_MAN" [@mc.selected type "WOMAN"/]>留守妇女</option>
                                            <option value="OTHER" [@mc.selected type "OTHER"/]>SOS紧急</option>
                                        </select>
                                    </label>

                                    <label>救援状态
                                        <select name="status">
                                            [#assign status = (command.status!)?default("") /]
                                            <option value="">全部</option>
                                            <option value="WAIT_RESCUE" [@mc.selected status "WAIT_RESCUE"/]>待救援
                                            </option>
                                            <option value="IN_RESCUE" [@mc.selected status "IN_RESCUE"/]>救援中</option>
                                            <option value="WAIT_AUDIT" [@mc.selected status "WAIT_AUDIT"/]>完成待审核
                                            </option>
                                            <option value="SUCCESS_RESCUE" [@mc.selected status "SUCCESS_RESCUE"/]>
                                                完成救援
                                            </option>
                                            <option value="CANCEL_RESCUE" [@mc.selected status "CANCEL_RESCUE"/]>取消救援
                                            </option>
                                        </select>
                                    </label>
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
                            <th>申请人</th>
                            <th>申请时间</th>
                            <th>申请类型</th>
                            <th>救援司机</th>
                            <th>申请说明</th>
                            <th>救援状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>


                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                            [#if pagination.data??]
                                [#list pagination.data as rescue ]
                                <tr class="even">
                                    <td>${rescue.applyUser.userName!}</td>
                                    <td>${rescue.applyTime!}</td>
                                    <td>${(rescue.rescueType.getName())!}</td>
                                    <td>${rescue.driver.userName!}</td>
                                    <td>${rescue.description!}</td>
                                    <td>${(rescue.status.getName())!}</td>
                                    <td>
                                        <div class="btn-group">
                                            [#if rescue.status == "WAIT_RESCUE"]
                                                <a class="btn btn-success"
                                                   href="[@spring.url '/rescue/deal/${rescue.id}'/]">处理救援</a>
                                            [/#if]
                                            [#if rescue.status == "WAIT_AUDIT"]
                                                <a class="btn btn-success"
                                                   href="[@spring.url '/rescue/finish?id=${rescue.id}&version=${rescue.version}'/]">通过审核</a>
                                            [/#if]
                                            <a class="btn btn-success"
                                               href="[@spring.url '/rescue/info?id=${rescue.id}'/]">查看详情</a>
                                        </div>
                                    </td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>

                    [#if pagination??]
                        [@mc.showPagination '/rescue/list' /]
                    [/#if]

                </div>
            </div>
        </div>
    </div>
</div>
[/@override]

[@extends name="/decorator.ftl"/]