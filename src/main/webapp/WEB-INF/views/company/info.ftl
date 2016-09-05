[@override name="title"]公司信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
公司信息
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        [@mc.showAlert /]
        <div class="col-md-12">
            <div class="btn-group">
                <a class="btn btn-success btn-lg" href="/company/edit">修改信息</a>
                <a class="btn btn-danger btn-lg" href="/company/update_password">修改密码</a>
            </div>
            <ul class="am-data">
                <li><span class="text2">用户名</span><span class="text3">${company.userName!}</span></li>
                <li><span class="text2">邮件</span><span  class="text3">${company.email!}</span></li>
                <li><span class="text2">公司名称</span><span  class="text3">${company.name!}</span></li>
                <li><span class="text2">公司注册时间</span><span  class="text3">${company.registerDate!}</span></li>
                <li><span class="text2">公司注册地点</span><span  class="text3">[@mc.showCascade company.registerAddress/]</span></li>
                <li><span class="text2">公司运营地点</span><span  class="text3">[@mc.showCascade company.operateAddress/]</span></li>
                <li><span class="text2">公司等级</span><span  class="text3">${company.level!}</span></li>
                <li><span class="text2">公司资质</span><span  class="text3"><img src="${company.folder!}"/></span></li>
            </ul>
        </div>
    </div>
</div>
[/@override]

[@extends name="/decorator.ftl"/]