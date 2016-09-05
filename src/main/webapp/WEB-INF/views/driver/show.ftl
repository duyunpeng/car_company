[@override name="title"]司机信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="pageHeaderTitle"]
<a href="/">首页</a>
<span class="glyphicon glyphicon-chevron-right"></span>
司机信息
[/@override]

[@override name="subContent"]
<div class="container">
    <div class="row">
        [@mc.showAlert /]
        <div class="col-md-12">
            <ul class="am-data">
                <li><span class="text2">用户名</span><span>${driver.userName!}</span></li>
                <li><span class="text2">昵称</span><span>${driver.name!}</span></li>
                <li><span class="text2">电话</span><span>${driver.phone!}</span></li>
                <li style="min-height: 300px" class="img-box"><span class="text2">头像</span><span><img
                        src="${driver.head!}"/></span></li>
                <li style="min-height: 300px" class="img-box"><span class="text2">身份证照片</span><span><img
                        src="${driver.identityCardPic!}"/></span></li>
                <li style="min-height: 300px" class="img-box"><span class="text2">驾驶证照片</span><span><img
                        src="${driver.drivingLicencePic!}"/></span></li>
                <li><span class="text2">性别</span><span>${(driver.sex.getName())!}</span></li>
                <li><span class="text2">余额</span><span>${driver.balance!}</span></li>
                <li><span class="text2">等级</span><span>${driver.level!}</span></li>
                <li><span class="text2">举报次数</span><span>${driver.reportCount!}</span></li>
                <li><span class="text2">是否在线</span><span>${driver.online!}</span></li>
                <li><span class="text2">类型</span><span>${(driver.driverType.getName())!}</span></li>
                [#if driver.driverType == "LIMOUSINE"]
                    <li style="min-height: 300px" class="img-box"><span class="text2">行驶证</span><span><img
                            src="${driver.travelPic!}"/></span></li>
                [#elseif driver.driverType == "GENERATION"]
                    <li><span class="text2">驾照类型</span><span>${driver.drivingLicenceType!}</span></li>
                [#else]
                    <li style="min-height: 300px" class="img-box"><span class="text2">行驶证</span><span><img
                            src="${driver.travelPic!}"/></span></li>
                    <li style="min-height: 300px" class="img-box"><span class="text2">营业资格证</span><span><img
                            src="${driver.businessPic!}"/></span></li>
                    <li style="min-height: 300px" class="img-box"><span class="text2">从业资格证</span><span><img
                            src="${driver.workPic!}"/></span></li>
                [/#if]
                <li><span class="text2">银行卡号</span><span>${driver.bankCardNo!}</span></li>
                <li><span class="text2">银行名称</span><span>${driver.bankName!}</span></li>
            </ul>
        </div>
    </div>
</div>
[/@override]

[@extends name="/decorator.ftl"/]