<%@page import="xm.chen.service.impl.GoodServiceImpl"%>
<%@page import="xm.chen.service.GoodService"%>
<%@page import="xm.chen.modal.Goods"%>
<%@page import="xm.chen.modal.Images"%>
<%@page import="xm.chen.service.impl.ImagesServiceImpl"%>
<%@page import="xm.chen.service.ImagesService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>商品详情</title>
<link rel="stylesheet" href="../css/shouye.css">
<script src="../js/jquery-1.8.2.js"></script>
<script src="../js/modernizr-custom-v2.7.1.min.js"></script>
<script src="../js/goodsDetail.js"></script>
</head>
<%
	User user = (User) session.getAttribute("user");
	int gid = Integer.parseInt(request.getParameter("gid"));
	ImagesService idao = new ImagesServiceImpl();
	List<Images> imglist = idao.getImgByCondition("goodid=" + gid);
	GoodService gs = new GoodServiceImpl();
	Goods good = gs.getGoodById(gid);
%>
<body>
	<!-- 头部 -->

	<%@include file="../common/header.jsp"%>
	<div>
		<div style="font-size:30px;padding-left: 120px;">商品介绍</div>
		<hr>
	</div>
	<div class="Xcontent">
		<input type="hidden" value="<%=good.getId()%>" id="goodid" />
		<!-- 图片展示 -->
		<ul class="Xcontent01">
			<div class="Xcontent06">
				<img src="<%="upload/" + imglist.get(0).getImgName()%>">
			</div>
			<ol class="Xcontent08">
				<div class="Xcontent07">
					<img src="<%="upload/" + imglist.get(0).getImgName()%>">
				</div>
				<div class="Xcontent09">
					<img src="<%="upload/" + imglist.get(1).getImgName()%>">
				</div>
				<div class="Xcontent10">
					<img src="<%="upload/" + imglist.get(2).getImgName()%>">
				</div>
				<div class="Xcontent11">
					<img src="<%="upload/" + imglist.get(3).getImgName()%>">
				</div>
				<div class="Xcontent12">
					<img src="<%="upload/" + imglist.get(4).getImgName()%>">
				</div>
			</ol>
			<!-- 商品介绍 -->
			<ol class="Xcontent13">
				<div class="Xcontent14">
					<a href="#"><p>商品详情</p></a>
				</div>
				<div class="Xcontent16">
					<p><%=good.getGoodName()%></p>
				</div>
				<div class="Xcontent17">
					<p class="Xcontent18">售价</p>
					<p class="Xcontent19">
						￥<span><%=good.getPrice()%></span>
					</p>
					<div class="Xcontent20">
						<p class="Xcontent21">配置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
						<p class="Xcontent22"><%=good.getConfig()%></p>
					</div>
					<div class="Xcontent23">
						<p class="Xcontent24">服务</p>
						<p class="Xcontent25">30天无忧退货&nbsp;&nbsp;&nbsp;&nbsp; 48小时快速退款
							&nbsp;&nbsp;&nbsp;&nbsp; 满88元免邮</p>
					</div>
				</div>
				<div class="Xcontent26">
					<p class="Xcontent27">颜色</p>
					<div class="Xcontent28" onclick="count('-');">
						<img src="<%="upload/" + imglist.get(0).getImgName()%>">
					</div>
					<div class="Xcontent29" onclick="count('+');">
						<img src="<%="upload/" + imglist.get(5).getImgName()%>">
					</div>
					<p class="Xcontent27a">
						库存<label class="label"><%=good.getCount()%></label>
					</p>
				</div>
				<div class="Xcontent30">
					<p class="Xcontent31">数量</p>
					<div class="Xcontent32">
						<img src="images/shoppingdetail/X15.png">
					</div>
					<form>
						<input class="input" name="count" value="1">
					</form>
					<div class="Xcontent33">
						<img src="images/shoppingdetail/16.png">
					</div>
				</div>
				<div class="Xcontent34">
					<%
						if (user == null) {
					%>
					<a href="user/login.jsp"><img
						src="images/shoppingdetail/X17.png"></a>
					<%
						} else {
					%>
					<a href="javascript:count()" class="buyshop"><img
						src="images/shoppingdetail/X17.png"></a>
					<%
						}
					%>
				</div>
				<div class="Xcontent35">
					<%
						if (user == null) {
					%>
					<a href="user/login.jsp"><img
						src="images/shoppingdetail/X18.png"></a>
					<%
						} else {
					%>
					<a class="addcart"><img src="images/shoppingdetail/X18.png"></a>
					<%
						}
					%>
				</div>
			</ol>
		</ul>
	</div>
	<!-- 评论区域 -->	
	<div class="commentinfo">
		<div style="font-size:30px;width:1200px;margin: 0px auto;">
			<label>商品评论</label>
		</div>
		<hr>			
	</div>
	<!-- 底部 -->
	<%@include file="../common/foot.jsp"%>
</body>
</html>
