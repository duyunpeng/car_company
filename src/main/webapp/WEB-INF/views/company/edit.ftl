[@override name="title"]修改公司信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
<a href="/company/info">公司信息</a>
<span class="glyphicon glyphicon-chevron-right"></span>
修改公司信息
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        <div class="col-md-12">
            [@mc.showAlert /]
            <form class="form-horizontal" action="/company/edit" method="post">

                <input type="hidden" name="id" value="${company.id!command.id}"/>
                <input type="hidden" name="version" value="${company.version!command.version}"/>

                [@spring.bind "command.email"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 邮箱* </label>

                    <div class="col-sm-9">
                        <input type="text" id="form-field-1" name="email" value="${company.email!command.email}"
                               placeholder="邮箱" class="col-xs-10 col-sm-5" required/>
                        [@spring.showErrors "email"/]
                    </div>
                </div>

                [@spring.bind "command.name"/]
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 公司名称* </label>

                    <div class="col-sm-9">
                        <input type="text" id="form-field-1" name="name" value="${company.name!command.name}"
                               placeholder="公司名称" class="col-xs-10 col-sm-5" required/>
                        [@spring.showErrors "name"/]
                    </div>
                </div>

                <div class="form-group col-xs-8">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        修改
                    </button>
                    <button class="btn" type="reset">
                        <i class="icon-undo bigger-110"></i>
                        重置
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
[/@override]

[@extends name="/decorator.ftl"/]