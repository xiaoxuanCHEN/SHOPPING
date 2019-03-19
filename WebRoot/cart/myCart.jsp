<%@page import="xm.chen.modal.Goods"%>
<%@page import="xm.chen.service.impl.GoodServiceImpl"%>
<%@page import="xm.chen.service.GoodService"%>
<%@page import="xm.chen.modal.MyCart"%>
<%@page import="xm.chen.service.impl.mycartServiceIpml"%>
<%@page import="xm.chen.service.mycartService"%>
<%@page import="xm.chen.modal.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>

<title>我的购物车</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<style type="text/css">
a:HOVER {
	text-decoration: none;
	color: #ff0000;
}
</style>
<script src="../js/jquery-1.8.2.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript" src="../js/cart.js"></script>
</head>
<%
	User u1 = (User) session.getAttribute("user");
	if (u1 == null) {
		response.sendRedirect("../user/login.jsp");
		return;
	}
	mycartService mycart = new mycartServiceIpml();
	GoodService goods = new GoodServiceImpl();
	List<MyCart> list = mycart.getAllCart(u1.getId());
%>
<body>
	<%@include file="../common/Usercheck.jsp"%>
	<%@include file="../common/header1.jsp"%>
	<%@include file="../common/navUser.jsp"%>
	<div class="container" style="background-color:#f0f0f0;width:1080px;">
		<form action="doOrderServlet?type=carbuy" method="post">
			<table class="table">
				<thead>
					<tr>
						<th style="width:100px;"><input
							onchange="quanxuan(this),jisuan()" type="checkbox" />&nbsp;&nbsp;<label>全选</label></th>
						<th style="width:100px;">商品编号</th>
						<th style="width:230px;">商品名称</th>
						<th style="width:230px;">购买数量</th>
						<th style="width:150px;">商品单价</th>
						<th style="width:150px;">商品总价</th>
						<th style="width:120px;">操作</th>
					</tr>
				</thead>
				<tbody>
					<%
						if (list == null || list.isEmpty()) {
					%>
					<tr class="gobuy">
						<td colspan="7" style="padding-left: 300px;"><img alt=""
							src="images/gwc.jpg"> <label
							style="position:relative;top:-100px;left:30px; font-style: oblique;font-family:微软雅黑;
						 font-size: 25px;font-weight: bold;text-shadow:8px 5px 3px #adadad;">还没有商品!
								<br> <a class="buy" href="index.jsp">立即去购买</a>
						</label></td>
					</tr>
					<%
						} else {
							for (MyCart cart : list) {
								Goods g = goods.getGoodById(cart.getGoodid());
					%>
					<tr id="good<%=g.getId()%>" class="cartinfo">
						<td><input onchange="jisuan()" checked="checked"
							class="check" name="check" type="checkbox"
							value="<%=cart.getId()%>" /></td>
						<td><%=cart.getGoodid()%></td>
						<td><%=g.getGoodName()%></td>
						<td><button type="button"
								onclick="calculate('-',<%=g.getCount()%>,'txtcount<%=cart.getId()%>'),editCount('<%=g.getId()%>','<%=cart.getId()%>');"
								class="btn btn-primary btn-sm">-</button> <input type="text"
							name="incount<%=cart.getId()%>" onkeypress="jisuan()"
							id="txtcount<%=cart.getId()%>" style="text-align: center;"
							value="<%=cart.getCount()%>">
							<button type="button"
								onclick="calculate('+',<%=g.getCount()%>,'txtcount<%=cart.getId()%>'),editCount('<%=g.getId()%>','<%=cart.getId()%>');"
								class="btn btn-primary btn-sm">+</button></td>
						<td class="price" id="price<%=cart.getId()%>">&yen;<%=g.getPrice()%></td>
						<td class="xiaoji" id="xiaoji<%=cart.getId()%>">&yen;</td>
						<td><a href="javascript:del('<%=g.getId()%>')"><button
									type="button" class="btn btn-danger btn-sm">移除</button></a></td>
					</tr>
					<%
						}
						}
					%>
					<tr>
						<td>总购物价</td>
						<td><span id="totalmoney">&yen;0</span></td>
						<td>总购买数量</td>
						<td><span id="count">0</span></td>
						<%
							if (list == null || list.isEmpty()) {
						%>
						<td><input disabled="disabled" class="btn btn-danger btn-sm"
							type="submit" value="结算" /></td>
						<td colspan="2"><a href="javascript:delCart()"><button
									disabled="disabled" type="button" class="btn btn-danger btn-sm">清空购物车</button></a></td>

						<%
							} else {
						%>
						<td><input class="btn btn-danger btn-sm" type="submit"
							value="结算" /></td>
						<td colspan="2"><a href="javascript:delCart()"><button
									type="button" class="btn btn-danger btn-sm">清空购物车</button></a></td>
						<%
							}
						%>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<%@include file="../common/foot1.jsp"%>
</body>
</html>
