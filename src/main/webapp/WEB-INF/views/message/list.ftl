[@override name="title"]站内信列表[/@override]

[@override name="topResources"]
    [@super /]
<link rel="stylesheet" type="text/css"
      href="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.css'/]"/>
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
站内信列表
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
                                    <input hidden type="text" value="${command.searchType!}" name="searchType" />
                                    <label>开始<input type="text" value="${command.beginTime!}" id="beginTime" name="beginTime" /></label>
                                    <label>结束<input type="text" value="${command.endTime!}" id="endTime" name="endTime" /></label>
                                    <label>接收人<input type="text" value="${command.receiveBaseUser!}" name="receiveBaseUser" /></label>
                                    <label><button type="submit" class="btn btn-app btn-sm btn-success">查询</button></label>
                                </div>
                            </div>
                        </form>
                    </div>
                    <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                        <thead>
                        <tr role="row">
                            <th>发送人</th>
                            <th>发送内容</th>
                            <th>发送时间</th>
                            <th>接收人</th>
                            <th>操作</th>
                        </tr>
                        </thead>


                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                            [#if pagination.data??]
                                [#list pagination.data as message ]
                                <tr class="even">
                                    <td>${message.sendBaseUser.userName!}</td>
                                    <td>${message.content!}</td>
                                    <td>${message.sendDate!}</td>
                                    <td>${message.receiveBaseUser.userName!}</td>
                                    <td>
                                        <div class="btn-group">
                                            <ul>
                                                <li>
                                                    <a class="btn btn-success" href="[@spring.url '/message/show/${message.id!}?searchType=${command.searchType}'/]">查看</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>

                    [#if pagination??]
                        [@mc.showPagination '/message/list?searchType=${command.searchType!}&beginTime=${command.beginTime!}&endTime=${command.endTime!}&receiveBaseUser=${command.receiveBaseUser!}' /]
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
    $('#beginTime').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
    $('#endTime').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
</script>
[/@override]

[@extends name="/decorator.ftl"/]