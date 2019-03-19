<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>欢迎注册</title>
<link rel="stylesheet" type="text/css" href="css/register.css">
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/jquery.validate.messages_cn.js"></script>
<script type="text/javascript" src="js/register.js"></script>
<style type="text/css">
input.error {
	border: solid 1px #ff2200;
}

form label.error {
	color: #ff2200;
	font-size: 0.8em;
	font-weight: bolder;
}

.error {
	margin-left: 225px;
}
</style>
</head>

<body>
	<form action="doRegisterServlet" method="post" class="register">
		<div class="img">
			<a href="index.jsp" title="首页"><img alt=""
				src="images/milogo.png"></a>
		</div>
		<p>注册账号</p>
		<br> <input type="text" name="realname" placeholder="真实姓名"><br>
		<input type="text" name="phone" placeholder="电话"> <br> <input
			type="password" id="pwd" name="pwd" placeholder="密码"> <br>
		<input type="password" id="realpwd" name="realpwd" placeholder="重复密码">
		<br> <input type="password" name="transpwd" placeholder="交易密码">
		<br> <input type="text" name="address" placeholder="地址">
		<br> <input type="radio" name="rdo" checked="checked" value="男"><span>男&nbsp;&nbsp;</span><input
			type="radio" name="rdo" value="女"><span>女 </span><br>
		<div class="btn">
			<input type="reset" value="重置"> <input type="submit"
				value="立即注册"><br>
		</div>
		<div class="bz">注册帐号即表示您同意并愿意遵守用户协议和隐私政策</div>
	</form>
	<div class="foot">
		<div class="language">
			<ul>
				<li><a href="#">简体</a>&nbsp;|</li>
				<li>&nbsp;<a href="#">繁体</a>&nbsp;|
				</li>
				<li>&nbsp;<a href="#">English</a>&nbsp;|
				</li>
				<li>&nbsp;<a href="#">常见问题</a></li>
			</ul>
			<div style="clear: both;"></div>
		</div>
		<div class="f">
			小米公司版权所有-京ICP备10046444-<img src="images/ghs.png">京公网安备11010802020134号-京ICP证110507号
		</div>
	</div>
</body>
</html>
