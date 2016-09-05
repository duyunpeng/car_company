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
            <ul class="am-data">
                <li><span class="text2">发送人</span><span>${message.sendBaseUser.userName!}</span></li>
                <li><span class="text2">收件人</span><span>${message.receiveBaseUser.userName!}</span></li>
                <li><span class="text2">发送时间</span><span>${message.sendDate!}</span></li>
                <li><span class="text2">发送内容</span><span>${message.content!}</span></li>
            </ul>
            <div class="btn-group">
                <a class="btn btn-success btn-lg" href="/message/list?searchType=${searchType}">返回列表</a>
            </div>
        </div>
    </div>
</div>
[/@override]

[@extends name="/decorator.ftl"/]