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

<title>欢迎登录</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btn").click(function() {
			var txt = $(":input:eq(0)").val();
			var pwd = $(":input:eq(1)").val();
			if (txt == null || txt == "") {
				alert("请输入手机号!");
				$(":input:eq(0)").focus();
			} else if (pwd == null || pwd == "") {
				alert("请输入密码!")
				$(":input:eq(1)").focus();
			}
		});
	});
</script>
</head>
<body>
	<div class="main">
		<div class="logo">
			<a href="index.jsp" title="首页"><img alt="为发烧而生"
				src="images/logo-1.png"></a>
		</div>
		<div class="login_bj">
			<img alt="" src="images/login-bj.png">
		</div>
		<div class="login">
			<form action="doLoiginServlet?type=login" method="post">
				<span>账号登录</span><br> <input type="text" name="phone"
					placeholder="手机号"><br> <input type="password"
					name="pwd" placeholder="密码"><br> <input type="submit"
					id="btn" value="登录">
			</form>
			<div class="zc">
				<a href="user/register.jsp">立即注册</a>&nbsp;|&nbsp;<a href="#">忘记密码?</a>
			</div>
		</div>
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
	</div>
</body>
</html>
