<%@page import="xm.chen.service.impl.UserServiceImpl"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>用户信息修改</title>
<link rel="stylesheet" type="text/css" href="../css/pay.css" />
<script type="text/javascript" src="../js/jquery-1.8.2.js"></script>
<script type="text/javascript">
	$(function() {
		$(".m-modal-close").click(function() {
			var $modal = $('.m-modal');
			$modal.children('.m-modal-dialog').animate({
				"margin-top" : "-200px"
			}, 800);
			$modal.fadeOut(500);
		});
		$(".m-btn-cancel").click(function() {
			var $modal = $('.m-modal');
			$modal.children('.m-modal-dialog').animate({
				"margin-top" : "-200px"
			}, 800);
			$modal.fadeOut(500);
		});
		$(".btn1").click(function() {
			$(".m-modal").css("display", "block");
			$('.m-modal').children('.m-modal-dialog').animate({
				"margin-top" : "200px"
			}, 500);
		});
		$(".m-btn-sure").click(function() {
			var money = $("input[name='money']").val();
			var pwd = $("input[name='pwd1']").val();
			if (money == null || money == "") {
				alert("请输入充值金额!");
				return;
			} else if (money == 0) {
				alert("充值金额不能为0!");
				return;
			} else if (pwd == null || pwd == "") {
				alert("请输入交易密码!");
				return;
			}
			$.ajax({
				url : "doEditUserServlet",
				data : {
					type : 'recharge',
					money : money,
					pwd : pwd
				},
				type : 'post',
				datatype : 'html',
				success : function(data) {
					var row = data.split(",")[0];
					var money = data.split(",")[1];
					if (row == 1) {
						alert("充值成功!");
						$(".balance").html(money);
						$(".m-modal").fadeOut(1000);
						$("input[name='pwd1']").val("");
					} else if (row == 2) {
						alert("密码不正确!");
					} else {
						alert("充值失败!");
					}
				},
				error : function(data) {
					alert(error);
				}
			}
			);
		});
	});
</script>
<style type="text/css">
.info {
	width: 980px;
	height: 450px;
	background-color: #f0f0f0;
	box-shadow: 2px 3px 3px 3px #808080;
	margin: auto;
	margin-top: 30px;
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

.info input[type='text'],.info input[type='password'] {
	height: 27px;
	line-height: 27px;
	width: 200px;
	border: 0px;
	text-align: center;
	font-size: 14px;
	font-weight: bolder;
	padding: 0px;
}

input[type='radio'] {
	width: 74px;
}

#btn {
	width: 100px;
	height: 28px;
	border: 0px;
	background-color: rgb(255, 88, 24);
	color: #fff;
	cursor: pointer;
}

#btn:HOVER {
	background-color: rgb(255, 66, 66);
	color: #000;
}
</style>
</head>
<body>
	<%@include file="../common/Usercheck.jsp"%>
	<%@include file="../common/header1.jsp"%>
	<%
		User us = (User) session.getAttribute("user");
		User us1 = new UserServiceImpl().getUserByCondition("id='" + us.getId() + "'").get(0);
		pageContext.setAttribute("us", us1);
	%>
	<c:if test="${us!=null }">
		<div class="info">
			<form action="doEditUserServlet?type=editUser" method="post">
				<div>
					<span>个人信息修改</span>
				</div>
				<div>
					<label>真实姓名:</label><label>${us['realname'] }</label>
				</div>
				<div>
					<label>手机号码:</label><label>${us['phone'] }</label>
				</div>
				<div>
					<label>登录密码:</label><input type="password" name="pwd"
						value="${us['pwd'] }" />
				</div>
				<div>
					<label>账户余额:</label><label class="balance">${us['balance'] }</label><input
						type="button" value="充值" class="btn1" />
				</div>
				<div>
					<label>收货地址:</label><input type="text" name="address"
						value="${us['address']}" />
				</div>
				<div>
					<label>性别:</label><input type="radio"
						${us['sex']=='男'?'checked="checked"':''} name="sex" value="男">男<input
						type="radio" ${us['sex']=='女'?'checked="checked"':''} name="sex"
						value="女">女
				</div>
				<div class="btn">
					<input type="submit" id="btn" value="修改" />
				</div>
			</form>
		</div>
	</c:if>
	<%@include file="../common/foot1.jsp"%>
	<div class="m-modal">
		<div class="m-modal-dialog">
			<div class="m-top">
				<h4 class="m-modal-title">账户充值</h4>
				<span class="m-modal-close">&times;</span>
			</div>
			<div class="m-middle">
				<!--content-->
				<p>
					充值金额：<input type="text" name="money"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
				</p>
				<p>
					充值密码：<input type="password" name="pwd1" />
				</p>
			</div>
			<div class="m-bottom">
				<button class="m-btn-sure">确定</button>
				<button class="m-btn-cancel">取消</button>
			</div>
		</div>
	</div>
</body>
</html>
