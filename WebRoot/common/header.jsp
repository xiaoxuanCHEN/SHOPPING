<%@page import="xm.chen.modal.Role"%>
<%@page import="xm.chen.modal.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>头部</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/main.css">

</head>
<%
	User u = (User) session.getAttribute("user");
%>
<body>
	<!-- 顶部导航条 -->
	<div class="topbar">
		<div class="container">
			<div class="topbar-nav">
				<a href="#">大米商城</a> <span class="ver">|</span> <a href="#">DAMI</a>
				<span class="ver">|</span> <a href="#">米聊</a> <span class="ver">|</span>
				<a href="#">游戏</a> <span class="ver">|</span> <a href="#">多看阅读</a> <span
					class="ver">|</span> <a href="#">云服务</a> <span class="ver">|</span>
				<a href="#">金融</a> <span class="ver">|</span> <a href="#">大米商城移动版</a>
				<span class="ver">|</span> <a href="#">问题反馈</a> <span class="ver">|</span>
				<a href="#">Select Refion</a>
			</div>
			<div class="topbar-cart">
				<a href="cart/myCart.jsp"><i></i>购物车</a>
			</div>
			<%
				if (u != null && u.getRole() == Role.用户.ordinal()) {
			%>
			<div class="topbar-info">
				<span class="realname" style="color: #ff5809;font-weight: bolder;">欢迎您&nbsp;--&nbsp;<%=u.getRealname()%></span>
				<a href="user/loginout.jsp" class="link">[注销]</a> <span class="ver">|</span>
				<a href="user/usermanager.jsp" class="link">个人信息</a>
			</div>
			<%
				} else if (u != null && u.getRole() == Role.管理员.ordinal()) {
			%>
			<div class="topbar-info">
				<span class="realname" style="color: #ff5809;font-weight: bolder;">管理员&nbsp;--&nbsp;<%=u.getRealname()%></span>
				<a href="user/loginout.jsp" class="link">[注销]</a> <span class="ver">|</span>
				<a href="user/adminmanager.jsp" class="link">我的管理</a>
			</div>
			<%
				} else {
			%>
			<div class="topbar-info">
				<a href="user/login.jsp" class="link">登录</a> <span class="ver">|</span>
				<a href="user/register.jsp" class="link">注册</a> <span class="ver">|</span>
				<a href="user/login.jsp" class="link">消息通知</a>
			</div>
			<%
				}
			%>
		</div>
	</div>

</body>
</html>
