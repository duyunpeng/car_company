<!DOCTYPE html>
<html lang="en">
<head>
[@block name="Meta"]
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content=""/>
    <meta name="author" content="YJH"/>
[/@block]
    <title>Car 公司用户系统 - 注册成功</title>


    <link href="[@spring.url '/resources/assets/style/bootstrap.css'/]" rel="stylesheet">
    <link rel="stylesheet" href="[@spring.url '/resources/assets/style/font-awesome.css'/]">
    <link href="[@spring.url '/resources/assets/style/style.css'/]" rel="stylesheet">
    <script src="[@spring.url '/resources/assets/js/jquery.js'/]"></script>
</head>

<body>

</body>
<script>
    $(document).ready(function () {
        alert("注册成功,我们将审核你的信息,通过后将通知您,您就可以使用了");
        location.href = "/logout";
    })
</script>
</html>