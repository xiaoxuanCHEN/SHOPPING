<%@page import="xm.chen.modal.OrderStatus"%>
<%@page import="xm.chen.modal.Orders"%>
<%@page import="xm.chen.service.impl.OrderServiceImpl"%>
<%@page import="xm.chen.service.OrderService"%>
<%@page import="xm.chen.service.impl.GoodServiceImpl"%>
<%@page import="xm.chen.service.GoodService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的订单</title>
<link rel="stylesheet" type="text/css" href="../css/pay.css" />
<link rel="stylesheet" type="text/css" href="../css/order.css">
<link rel="stylesheet" type="text/css" href="../css/comment.css">
<script type="text/javascript" src="../js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="../js/order.js"></script>
<script type="text/javascript" src="../js/comment.js"></script>
</head>
<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
		response.sendRedirect("../user/login.jsp");
		return;
	}
	GoodService g = new GoodServiceImpl();
	OrderService o = new OrderServiceImpl();
	List<Orders> list = o.getOrdersByCondition("uid='" + user.getId() + "'");
%>
<body>
	<%@include file="../common/header1.jsp"%>
	<%@include file="../common/navUser.jsp"%>
	<div class="order">
		<div class="ord">
			<table border="1" cellpadding="0" cellspacing="1">
				<tr class="title">
					<th>订单ID</th>
					<th>下单时间</th>
					<th>订单状态</th>
					<th>订单总金额</th>
					<th>操作</th>
				</tr>
				<%
					for (Orders order : list) {
				%>
				<tr class="info" id="info<%=order.getId()%>">
					<input type="hidden" value="<%=order.getId()%>" />
					<td><%=order.getId()%></td>
					<td><%=order.getOrderTime()%></td>
					<td><%=OrderStatus.values()[order.getOrderstatus()].toString()%></td>
					<td>&yen;<%=order.getTotalmoney()%></td>
					<td>
						<%
							if (order.getOrderstatus() == OrderStatus.未付款.ordinal()) {
						%> <a
						href="javascript:pay('<%=order.getTotalmoney()%>','<%=order.getId()%>')"
						class="a1">付款</a> <%
 	} else if (order.getOrderstatus() == OrderStatus.等待发货.ordinal()) {
 %> <%
 	} else if (order.getOrderstatus() == OrderStatus.已发货.ordinal()
 				|| order.getOrderstatus() == OrderStatus.配送中.ordinal()
 				|| order.getOrderstatus() == OrderStatus.运送中.ordinal()) {
 %> <a href="javascript:shouhuo('<%=order.getId()%>')" class="a1">确认收货</a>
						<%
							} else if (order.getOrderstatus() == OrderStatus.已收货.ordinal()) {
						%> <a href="javascript:pj('<%=order.getId()%>')" class="a1">评价</a>
						<%
							} else if (order.getOrderstatus() == OrderStatus.已评价.ordinal()) {
						%> <a href="javascript:showpj('<%=order.getId()%>')" class="a1">查看评价</a>
						<%
							}
						%> <a href="javascript:xq('<%=order.getId()%>')" class="a1">详情</a>
						<a href="javascript:del('<%=order.getId()%>')" class="a1">删除</a>
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>
	<%@include file="../common/foot1.jsp"%>
	<!-- 支付 -->
	<div class="m-modal">
		<div class="m-modal-dialog">
			<div class="m-top">
				<h4 class="m-modal-title">账单支付</h4>
				<span class="m-modal-close">&times;</span>
			</div>
			<div class="m-middle">
				<!--content-->
				<p>
					支付金额：<input type="text" name="money" readonly="readonly" />
				</p>
				<p>
					支付密码：<input type="password" name="pwd1" />
				</p>
			</div>
			<div class="m-bottom">
				<button class="m-btn-sure">确定</button>
				<button class="m-btn-cancel">取消</button>
			</div>
		</div>
	</div>
	<!-- 详情 -->
	<div class="m-modal1">
		<div class="m-modal-dialog1">
			<div class="m-top1">
				<h4 class="m-modal-title1">订单详情</h4>
				<span class="m-modal-close1">&times;</span>
			</div>
			<div class="m-middle1">
				<!--content-->
				<p class="xqinfo"></p>
			</div>
		</div>
	</div>
	<!-- 进行评论 -->
	<div class="m-modal2">
		<div class="m-modal-dialog2">
			<div class="m-top2">
				<h4 class="m-modal-title2">我要评论</h4>
				<span class="m-modal-close2">&times;</span>
			</div>
			<div class="m-middle2">
				<div class="goods-comm">
					<div class="ddid1">
						<span>订单号：</span> <label>0</label>
					</div>
					<div class="goods-comm-stars">
						<span class="star_l">满意度：</span>
						<div id="rate-comm-1" class="rate-comm"></div>
					</div>
				</div>
				<div class="l_text">
					<label class="m_flo">内 容：</label>
					<textarea name="" id="comment" class="text"></textarea>
					<span class="tr">字数限制为5-200个</span>
				</div>
			</div>
			<div class="m-bottom">
				<button class="m-btn-sure2" onclick="tjpj();">确定</button>
				<button class="m-btn-cancel2">取消</button>
			</div>
		</div>
	</div>
	<!-- 评论信息 -->
	<div class="m-modal3">
		<div class="m-modal-dialog3">
			<div class="m-top3">
				<h4 class="m-modal-title3">评论详情</h4>
				<span class="m-modal-close3">&times;</span>
			</div>
			<div class="m-middle1">
				<!--content-->
				<p>
					订单编号：<label class="plxq"></label>
				</p>
				<p class="plinfo"></p>
			</div>
		</div>
	</div>
</body>
</html>
