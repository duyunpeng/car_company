[@override name="title"]公司信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
救援信息
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        [@mc.showAlert /]
        <div class="col-md-12">
            <ul class="am-data">
                <li><span class="text2">申请人</span><span class="text3">${rescue.applyUser.userName!}</span></li>
                <li><span class="text2">申请时间</span><span  class="text3">${rescue.applyTime!}</span></li>
                <li><span class="text2">救援类型</span><span  class="text3">${(rescue.rescueType.getName())!}</span></li>
                <li><span class="text2">救援说明</span><span  class="text3">${rescue.description!}</span></li>
                <li><span class="text2">救援司机</span><span  class="text3">${rescue.driver.userName}</span></li>
                <li><span class="text2">救援时间</span><span  class="text3">${rescue.rescueTime!}</span></li>
                <li><span class="text2">救援状态</span><span  class="text3">${(rescue.status.getName())!}</span></li>
                <li><span class="text2">救援完成时间</span><span  class="text3">${rescue.finishTime!}</span></li>
                <li><span class="text2">救援图片</span><span  class="text3"><img src="${rescue.images!}"></span></li>
                <li><span class="text2">救援地址</span><span  class="text3">${rescue.rescueAddress!}</span></li>
            </ul>
        </div>
    </div>
</div>
[/@override]

[@extends name="/decorator.ftl"/]