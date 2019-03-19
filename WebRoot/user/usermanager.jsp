<%@page import="xm.chen.service.impl.UserServiceImpl"%>
<%@page import="xm.chen.service.UserService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>个人信息</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.info {
	width: 1080px;
	height: 400px;
	background-color: #f0f0f0;
	box-shadow: 3px 2px 2px 2px #808080;
	margin: auto;
}

.info div {
	width: 400px;
	margin: auto;
	padding-top: 10px;
	padding-bottom: 10px;
	font-weight: bolder;
	font-size: 18px;
	font-weight: bolder;
	text-align: center;
}

.info div span {
	font-size: 25px;
	font-weight: bolder;
	color: #ff8000;
	font-size: 25px;
}

label {
	display: inline-block;
	width: 200px;
	background-color: #fff;
	text-align: center;
}
</style>
</head>
<%
	User us = (User) session.getAttribute("user");	
	User us1=new UserServiceImpl().getUserByCondition("id='"+us.getId()+"'").get(0);
	pageContext.setAttribute("us", us1);
%>
<body>
	<%@include file="../common/Usercheck.jsp"%>
	<%@include file="../common/header1.jsp"%>
	<%@include file="../common/navUser.jsp" %>
	<%
		if (us != null) {
	%>
	<div class="info">
		<div>
			<span>个人信息管理</span>
		</div>
		<div>
			<label>真实姓名:</label><label>${us.realname }</label>
		</div>
		<div>
			<label>手机号码:</label><label>${us.phone }</label>
		</div>
		<div>
			<label>登录密码:</label><label>${us.pwd }</label>
		</div>
		<div>
			<label>账户余额:</label><label>${us.balance }</label>
		</div>
		<div>
			<label>收货地址:</label><label>${us.address }</label>
		</div>
		<div>
			<label>性别:</label><label>${us.sex }</label>
		</div>
	</div>
	<%
		}
	%>
	<%@include file="../common/foot1.jsp"%>
</body>
</html>
