<!DOCTYPE html>
<!--
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
-->
<html lang="zh_cn" xmlns="http://www.w3.org/1999/html">
<head>
[@block name="Meta"]
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content=""/>
    <meta name="author" content="YJH"/>
[/@block]
    <title>Car 公司平台 - [@block name="title"][/@block]</title>
[@block name = "topResources"]
    <link href="[@spring.url '/resources/assets/style/bootstrap.css'/]" rel="stylesheet">
    <link rel="stylesheet" href="[@spring.url '/resources/assets/style/font-awesome.css'/]">
    <link href="[@spring.url '/resources/assets/style/style.css'/]" rel="stylesheet">
    <link href="[@spring.url '/resources/assets/style/bootstrap.min.css'/]" rel="stylesheet">
    <link rel="stylesheet" href="[@spring.url '/resources/assets/style/reset.css/' /]"/>
    <link href="[@spring.url '/resources/assets/style/minimal.css'/]" rel="stylesheet">
    <script src="[@spring.url '/resources/assets/js/common.js'/]"></script>
[/@block]
</head>

<body>
[@block name="header"]
    [#include "shared/header.ftl"/]
[/@block]
<div class="center">

    <!-- 侧边栏 -->
[#include "/shared/slider-bar.ftl"]

    <div class="mainbar">
        <div class="page-head">
            <h2 class="pull-left">[@block name="pageHeaderTitle"][/@block]</h2>
            <div class="clearfix"></div>
        </div>
    [@block name="subContent"]

    [/@block]
    </div>
</div>

[@block name="footer"]
    [#include "shared/footer.ftl"/]
[/@block]

[@block name="bottomResources"]
<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='/resources/assets/js/jquery-2.0.3.min.js'>" + "<" + "script>");
</script>
<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='resources/assets/js/jquery-1.10.2.min.js'>" + "<" + "script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='resources/assets/js/jquery.mobile.custom.min.js'>" + "<" + "script>");
</script>
<script src="[@spring.url '/resources/assets/js/bootstrap.min.js'/]"></script>
<script src="[@spring.url '/resources/assets/js/typeahead-bs2.min.js'/]"></script>

<!--[if lte IE 8]>
<script src="[@spring.url '/resources/assets/js/excanvas.min.js'/]"></script>
<![endif]-->

[/@block]
</body>
</html>
