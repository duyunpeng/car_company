[@override name="title"]站内信创建[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
站内信创建
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        [@mc.showAlert /]
        <form class="form-horizontal" action="/message/create" method="post">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="content"> 站内信内容* </label>

                <div class="col-sm-9">
                    <textarea id="content" name="content" class="form-control" rows="5"
                              maxlength="500" placeholder="在此编辑信息"></textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 发送司机* </label>

                <div class="col-sm-9">
                    <button type="button"class="form-control modal-search-modal">点击选择司机</button>
                    <div class="show-permission">

                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-10">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        发送
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

<div class="modal fade alert-add" id="modalSearch" tabindex="-1" role="dialog" aria-labelledby="new-event"
     aria-hidden="true">
    <div class="large-dialog">
        <div class="change-bg">
            <div class="modal-header">
                <p class="modal-title thin">司机列表--勾选添加到已选权限列表
                    <small class="text-muted"></small>
                </p>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-9">
                        <section class="tile color transparent-black">
                            <div class="tile-header">
                                <div class="controls">
                                    <a href="#" class="minimize"><i class="fa fa-chevron-down"></i></a>
                                    <a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
                                    <a href="#" class="remove"><i class="fa fa-times"></i></a>
                                </div>
                            </div>
                            <div class="tile-body">
                                <form class="form-horizontal" role="form"
                                      action="[@spring.url '/permission/permission_list' /]">
                                    <div class="input-list">
                                        <ul>
                                            <li>
                                                <label>司机用户名:</label>
                                                    <span>
                                                        <input type="text" class="form-control" id="permissionName"
                                                               name="userName"
                                                               value="${command.userName!}">
                                                    </span>
                                            </li>

                                            <li>
                                                <label>状态:</label>
                                                    <span>
                                                       <select class="chosen-transparent form-control"
                                                               id="driverStatus" name="status">
                                                           [#assign status = (command.status!)?default("ENABLE") /]
                                                           <option value="ENABLE" [@mc.selected status "ENABLE"/]>启用
                                                           </option>
                                                           <option value="DISABLE" [@mc.selected status "DISABLE"/]>禁用
                                                           </option>
                                                       </select>
                                                    </span>
                                            </li>

                                            <li>
                                                <button type="button" class="btn btn-dutch margin-left-15"
                                                        id="permissionFind">查询
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </form>
                                <!-- tile body -->
                                <div class="tile-body nopadding">
                                    <table class="table table-bordered table-sortable table-hover">
                                        <thead></thead>
                                        <tbody></tbody>
                                    </table>
                                </div>
                                <!-- tile footer -->
                                <div class="tile-footer bg-transparent-black-2 rounded-bottom-corners">
                                    <div class="row">
                                        <div class="col-sm-4 text-center">
                                            <small class="inline table-options paging-info paging-permission">
                                            </small>
                                        </div>
                                        <div class="col-sm-4 text-right sm-center">
                                            <ul class="pagination pagination-xs nomargin pagination-custom pagination-permission">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <!-- /tile footer -->
                        </section>
                    </div>

                    <div class="col-md-3">
                        <section class="tile color transparent-black">
                            <div class="tile-header">
                                <h3><strong>已选</strong>列表</h3>

                                <div class="controls">
                                    <a href="#" class="minimize"><i class="fa fa-chevron-down"></i></a>
                                    <a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
                                    <a href="#" class="remove"><i class="fa fa-times"></i></a>
                                </div>
                            </div>
                            <div class="tile-body selector-box modal-search-selector">
                                <button class="btn margin-top-15 btn-green modal-search-hide-modal">确定</button>
                                <button class="btn margin-top-15 btn-info selector-remove-all">删除全部</button>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
[/@override]
[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/assets/js/modal-search-optimize.js' /]" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        function hasLength(ele, content) {
            var myEle = ele.children();
            var createLabel = ele.prev();
            if (myEle.length > 0) {
                createLabel.text(content)
            } else {
                createLabel.text('')
            }
            ;
        };
        var showTdPermission = $(".show-permission");
        $("#role-app-key").on("change", function () {
            $("#permission-app-key").val("");
            var appKey = $(this).val();
            if (appKey != "") {
                $("#permission-app-key").val(appKey);
            }
        });
        var modalSearch = new ModalSearch({
            url: "/driver/driver_list",
            pageSize: 6,
            isSingle: false,
            header: ['司机用户名', '司机姓名'],
            rowData: ["userName", "name"],
            selectorData: ["userName"],
            hideModalHandler: function (jsonDataArr) {
                showTdPermission.empty();
                for (var key in jsonDataArr) {
                    showTdPermission.append("<div class=\"check-td-info\">" + jsonDataArr[key].userName + "</div>");
                    showTdPermission.append("<input type=\"hidden\" name=\"receiveBaseUser\" value=\"" + jsonDataArr[key].id + "\" />")
                }
            }
        });
    })
</script>
[/@override]
[@extends name="/decorator.ftl"/]