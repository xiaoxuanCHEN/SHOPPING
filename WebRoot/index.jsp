<%@page import="xm.chen.service.impl.UserServiceImpl"%>
<%@page import="xm.chen.service.UserService"%>
<%@page import="xm.chen.service.impl.CommentServiceImpl"%>
<%@page import="xm.chen.service.CommentService"%>
<%@page import="xm.chen.modal.Goods"%>
<%@page import="xm.chen.service.impl.GoodServiceImpl"%>
<%@page import="xm.chen.service.GoodService"%>
<%@page import="xm.chen.modal.Images"%>
<%@page import="xm.chen.modal.HotComGoods"%>
<%@page import="xm.chen.service.impl.ImagesServiceImpl"%>
<%@page import="xm.chen.service.ImagesService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大米商城</title>
<link rel="stylesheet" href="css/main.css">
<script src="js/jquery-1.8.2.js"></script>
<style type="text/css">
.showinfo {
	padding-top: 60px;
	width: 248px;
	background-color: #fff;
	border: 1px solid #dedede;
	border-top-style: none;
	border-top-left-radius: 20px;
}

.search {
	padding: 3px 8px;
}

.search:HOVER {
	background-color: #dedede;
}

.search:HOVER {
	color: #fff;
}
</style>
<%
	ImagesService imgs = new ImagesServiceImpl();
	GoodService gs = new GoodServiceImpl();
	UserService us = new UserServiceImpl();
%>
</head>

<body>
	<!-- 头部区域 -->
	<%@include file="common/header.jsp"%>
	<!-- header区 -->
	<div class="header">
		<div class="container">
			<div class="header-logo">
				<a href="index.jsp" class="logo"></a>
				<div class="advertising">
					<a href="#"></a>
				</div>
			</div>
			<div class="header-nav">
				<ul class="navlist clearfix">
					<li class="all">
						<div class="side-nav">
							<ul class="sidenav-list">
								<li class="side-item"><a href="#">手机 <i
										class="iconfont">&#xe6a7;</i></a>
									<div class="children-nav">
										<ul class="children-list">
											<li><a href="goods/goodsDetail.jsp?gid=2" class="link"><img
													src="upload/6blcak-12616976267.jpg" class="icon-80"><span
													class="title">小米6</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=5" class="link"><img
													src="upload/xm5X-12f4deda88d.jpg" class="icon-80"><span
													class="title">小米5X</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=6" class="link"><img
													src="upload/xmNote3 -150aa544c4a.jpg" class="icon-80"><span
													class="title">小米Note3</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=4" class="link"><img
													src="upload/xm5C-1695bc518e1.jpg" class="icon-80"><span
													class="title">小米手机5c</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=8" class="link"><img
													src="upload/Note4X -1830ff012bf.jpg" class="icon-80"><span
													class="title">大米 Note4</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=9" class="link"><img
													src="upload/MIX2 -1c02941d07c.jpg" class="icon-80"><span
													class="title">大米MIX2</span></a></li>
										</ul>
									</div></li>
								<li class="side-item"><a href="#">笔记本<i
										class="iconfont">&#xe6a7;</i></a>
									<div class="children-nav">
										<ul class="children-list">
											<li><a href="goods/goodsDetail.jsp?gid=19" class="link"><img
													src="upload/13.3 I5-7200U 8G 256G-19e02873a81.jpg"><span
													class="title">小米pro</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=21" class="link"><img
													src="upload/i5 4G 256GB-199541e2a10.jpg"><span
													class="title">小米mi</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=22" class="link"><img
													src="upload/I5-7200U 8G 256G-1b35dee2fff.jpg"><span
													class="title">小米Air</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=23" class="link"><img
													src="upload/bjb-1.1873f47a6a3.jpg"><span
													class="title">小米air 焦显</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=24" class="link"><img
													src="upload/bjb-2.161f41199d9.jpg"><span
													class="title">小米pro 独显</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=25" class="link"><img
													src="upload/bjb-3.1ab3c8de74d.jpg"><span
													class="title">小米air543</span></a></li>
										</ul>
									</div></li>
								<li class="side-item"><a href="#">电视<i class="iconfont">&#xe6a7;</i></a>
									<div class="children-nav">
										<ul class="children-list">
											<li><a href="goods/goodsDetail.jsp?gid=11" class="link"><img
													src="upload/65 2.0  HDR-1db2c90109e.jpg"><span
													class="title">超薄HDR</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=12" class="link"><img
													src="upload/MI 4A L32M5-AZ 32-180c4c27f87.jpg"><span
													class="title">Mi4A</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=13" class="link"><img
													src="upload/MI 4A L43M5-AZ-1d80661adc5.jpg"><span
													class="title">65 2.0</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=14" class="link"><img
													src="upload/MI 4A L65M5-AZ 65 -120d7f311f2.jpg"><span
													class="title">Mi 4C</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=17" class="link"><img
													src="upload/MI 4C 55-1ed8f43a0de.jpg"><span
													class="title">MI 4C55</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=18" class="link"><img
													src="upload/4C 55-19ddd99ba3d.jpg"><span
													class="title">4C55</span></a></li>
										</ul>
									</div></li>

								<li class="side-item"><a href="#">耳机<i class="iconfont">&#xe6a7;</i></a>
									<div class="children-nav">
										<ul class="children-list">
											<li><a href="goods/goodsDetail.jsp?gid=43" class="link"><img
													src="upload/jnej-1306d6d28bb.jpg"><span class="title">胶囊耳机</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=44" class="link"><img
													src="upload/MI-139fc0615ef.jpg"><span class="title">头戴式</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=45" class="link"><img
													src="upload/5X-62f1f444e2d.jpg"><span class="title">小米5X</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=46" class="link"><img
													src="upload/m6-10bc3efb7ca.jpg"><span class="title">小米6</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=47" class="link"><img
													src="upload/MIX2-11e10a44ed8.jpg"><span class="title">MIX2</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=49" class="link"><img
													src="upload/MiLanYa-5609d794709.jpg"><span
													class="title">蓝牙防水</span></a></li>
										</ul>
									</div></li>
								<li class="side-item"><a href="#">保护套 贴膜<i
										class="iconfont">&#xe6a7;</i></a>
									<div class="children-nav">
										<ul class="children-list">
											<li><a href="goods/goodsDetail.jsp?gid=27" class="link"><img
													src="upload/ESR  -1dd57f9831b.jpg"><span
													class="title">小米6壳</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=28" class="link"><img
													src="upload/NOTE4X-23d2ec9ca15.jpg"><span
													class="title">NOTE4X壳</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=29" class="link"><img
													src="upload/5C-1987df671d2.jpg"><span class="title">米5C壳</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=35" class="link"><img
													src="upload/6-79544b8ff9c.jpg"><span class="title">米6手机膜</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=36" class="link"><img
													src="upload/5A-6e35d4ca773.jpg"><span class="title">红米5A膜</span></a></li>
											<li><a href="goods/goodsDetail.jsp?gid=37" class="link"><img
													src="upload/MI NOTE3 blue-1d13276b4b8.jpg"><span
													class="title">小米NOTE3膜</span></a></li>
										</ul>
									</div></li>
								<li class="side-item"><a href="#">路由器<i
										class="iconfont">&#xe6a7;</i></a>
									<div class="children-nav">
										<ul class="children-list">
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">路由器1</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">路由器2</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">路由器3</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">路由器4</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">路由器5</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">路由器6</span></a></li>
										</ul>
									</div></li>
								<li class="side-item"><a href="#">移动电源 电池<i
										class="iconfont">&#xe6a7;</i></a>
									<div class="children-nav">
										<ul class="children-list">
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">移动电源1</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">移动电源2</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">移动电源3</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">移动电源4</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">移动电源5</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">移动电源6</span></a></li>
										</ul>
									</div></li>
								<li class="side-item"><a href="#">线材 支架 存储卡<i
										class="iconfont">&#xe6a7;</i></a>
									<div class="children-nav">
										<ul class="children-list">
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
										</ul>
									</div></li>
								<li class="side-item"><a href="#">箱包 服饰 鞋 眼镜<i
										class="iconfont">&#xe6a7;</i></a>
									<div class="children-nav">
										<ul class="children-list">
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
										</ul>
									</div></li>
								<li class="side-item"><a href="#">米兔 生活周边<i
										class="iconfont">&#xe6a7;</i></a>
									<div class="children-nav">
										<ul class="children-list">
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
											<li><a href="#" class="link"><img
													src="img/icon/holder_40.png"><span class="title">大米6</span></a></li>
										</ul>
									</div></li>
							</ul>
						</div>
					</li>
					<li class="item"><a href="#">大米手机</a></li>
					<li class="item"><a href="#">红米</a></li>
					<li class="item"><a href="#">平板·笔记本</a></li>
					<li class="item"><a href="#">电视</a></li>
					<li class="item"><a href="#">盒子·影音</a></li>
					<li class="item"><a href="#">路由器</a></li>
					<li class="item"><a href="#">智能硬件</a></li>
					<li class="item"><a href="#">服务</a></li>
					<li class="item"><a href="#">社区</a></li>
				</ul>
			</div>
			<script type="text/javascript">
				$(function() {
					$(".showinfo").hide();
					$("#sertxt").keyup(function() {
						if ($(this).val() == "") {
							$(".showinfo").hide();
						} else {
							var key = $("#sertxt").val();
							$.post("doGoodsServlet", {
								key : key,
								type : "bykey"
							}, function(data) {
								//每次找到结果前先清空
								$(".showinfo").html("");
								var info = "<ul>\n"
								for (i = 0; i < data.length; i++) {
									info += "<a href=\'goods/goodsDetail.jsp?gid="
										+ data[i].id + "\'><li class=\"search\">" + data[i].goodName + "</li></a>\n";
								}
								info += "</ul>"
								$(".showinfo").append(info);
								if (data != null && data.length > 0) {
									$(".showinfo").show();
								} else {
									$(".showinfo").hide();
								}
							}, "json");
						}
					});
				});
			</script>
			<div class="header-search">
				<form action="#" class="search-form">
					<input type="text" id="sertxt" class="search-text"
						placeholder="&nbsp;&nbsp;搜&nbsp;索" />
					<div class="showinfo"></div>
					<input type="submit" class="search-btn iconfont" value="&#xe652;">
				</form>
			</div>
		</div>
		<!-- 导航显示商品 -->
	</div>
	<!-- 幻灯片区 -->
	<div class="carousel container">
		<div class="carousel-inner">
			<img src="images/lunbo/bj1.jpg" class="item first"><img
				src="images/lunbo/bj2.jpg" class="item"> <img
				src="images/lunbo/bj3.jpg" class="item"> <img
				src="images/lunbo/bj4.jpg" class="item"> <img
				src="images/lunbo/bj5.jpg" class="item"> <img
				src="images/lunbo/bj6.jpg" class="item">
		</div>
		<div class="carousel-btn">
			<a href="javascript:" class="btn active"></a> <a href="javascript:"
				class="btn"></a> <a href="javascript:" class="btn"></a> <a
				href="javascript:" class="btn"></a> <a href="javascript:"
				class="btn"></a> <a href="javascript:" class="btn"></a>
		</div>
		<a href="javascript:" class="prev"><i class="iconfont">&#xe697;</i></a>
		<a href="javascript:" class="next"><i class="iconfont">&#xe6a7;</i></a>
	</div>
	<!-- 大米功能区  -->
	<div class="home clearfix">
		<div class="container">
			<div class="features">
				<ul class="features-list clearfix">
					<li class="features-item"><a href="#"><i class="iconfont">&#xe6fd;</i>选购手机</a></li>
					<li class="features-item"><a href="#"><i class="iconfont">&#xe6fa;</i>企业团购</a></li>
					<li class="features-item"><a href="#"><i class="iconfont">&#xe6f7;</i>一元活动</a></li>
					<li class="features-item"><a href="#"><i class="iconfont">&#xe77c;</i>米粉卡</a></li>
					<li class="features-item"><a href="#"><i class="iconfont">&#xe6f2;</i>以旧换新</a></li>
					<li class="features-item"><a href="#"><i class="iconfont">&#xe6f5;</i>话费充值</a></li>
				</ul>
			</div>
			<div class="hot-promo">
				<ul class="promo-list clearfix">
					<li class="promo-item"><a href="goods/goodsDetail.jsp?gid=7"><img
							src="images/lunbo/hot1.jpg" alt=""></a></li>
					<li class="promo-item"><a href="goods/goodsDetail.jsp?gid=5"><img
							src="images/lunbo/hot2.jpg" alt=""></a></li>
					<li class="promo-item"><a href="goods/goodsDetail.jsp?gid=51"><img
							src="images/lunbo/hot3.jpg" alt=""></a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 热评产品 -->
	<div class="hot-review">
		<div class="title">
			<span class="page-title">热评产品</span>
		</div>
		<div class="col-md-12 ">
			<ul class="hotrew-list clearfix">
				<%
					CommentService com = new CommentServiceImpl();
					List<HotComGoods> listcom = com.findhotgoodcom();
					for (HotComGoods hot : listcom) {
						String[] img1 = imgs.goodsImg(Integer.parseInt(hot.getGoodid()));
						Goods good = gs.getGoodById(Integer.parseInt(hot.getGoodid()));
						User uscom = us.getUserByCondition("id='" + hot.getUid() + "'").get(0);
				%>
				<li class="goods-item goods-item-h">
					<div class="hotrew-thumb">
						<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"><img
							src="<%="upload/" + img1[0]%>" alt=""></a>
					</div>
					<div class="review">
						<a href="" class="review-text"><%=hot.getComments()%></a>
					</div>
					<p class="form-user">
						来自于 <span class="user-name"><%=uscom.getRealname()%></span> 的评价
					</p>
					<div class="title">
						<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"
							class="goods-title"><%=good.getGoodName()%></a> <span class="ver">|</span>
						<span class="goods-price"><%=good.getPrice()%>元</span>
					</div>
				</li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
	<!-- 大米主要内容 -->
	<div class="page-main">
		<div class="container">
			<!-- 家电 -->
			<div class="home-ce clearfix">
				<div class="title">
					<span class="page-title">家电</span>
					<div class="more">
						<a href="javascript:" class="more-text active">手机</a> <a
							href="javascript:" class="more-text">电视</a> <a href="javascript:"
							class="more-text">电脑</a>
					</div>
				</div>
				<div class="col-md-4">
					<ul class="lg-thumb-list clearfix">
						<li class="goods-item left-s-thumb"><a
							href="goods/goodsDetail.jsp?gid=53" class="s-thumb"><img
								width="234px" height="614px" src="images/xmjsq.jpg" alt=""></a></li>
					</ul>
				</div>
				<div class="col-md-8">
					<!-- 手机 -->
					<ul class="goods-list list-active hot-list clearfix">
						<%
							List<Goods> list = gs.getGoodByCondition("typeid=1");
							for (Goods good : list) {
								String[] img = imgs.goodsImg(good.getId());
						%>
						<li class="goods-item goods-item-m">
							<div class="goods-thumb">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"><img
									src="<%="upload/" + img[0]%>"></a>
							</div>
							<div class="goods-title">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"
									class="title"><%=good.getGoodName()%></a>
							</div>
							<p class="goods-info"><%=good.getIntroduction()%></p>
							<p class="goods-price"><%=good.getPrice()%>元
							</p>
						</li>
						<%
							}
						%>
					</ul>
					<!-- 手机 -->
					<ul class="goods-list list tv-list clearfix">
						<%
							List<Goods> list1 = gs.getGoodByCondition("typeid=3");
							for (Goods good1 : list1) {
								String[] img = imgs.goodsImg(good1.getId());
						%>
						<li class="goods-item goods-item-m">
							<div class="goods-thumb">
								<a href="goods/goodsDetail.jsp?gid=<%=good1.getId()%>"><img
									src="<%="upload/" + img[0]%>"></a>
							</div>
							<div class="goods-title">
								<a href="goods/goodsDetail.jsp?gid=<%=good1.getId()%>"
									class="title"><%=good1.getGoodName()%></a>
							</div>
							<p class="goods-info"><%=good1.getIntroduction()%></p>
							<p class="goods-price"><%=good1.getPrice()%>元
							</p>
							<div class="product-label">
								<div class="goods-label">新品</div>
							</div>
						</li>
						<%
							}
						%>
					</ul>
					<!-- 电脑 -->
					<ul class="goods-list list pc-list clearfix">
						<%
							List<Goods> list2 = gs.getGoodByCondition("typeid=4");
							for (Goods good1 : list2) {
								String[] img = imgs.goodsImg(good1.getId());
						%>
						<li class="goods-item goods-item-m">
							<div class="goods-thumb">
								<a href="goods/goodsDetail.jsp?gid=<%=good1.getId()%>"><img
									src="<%="upload/" + img[0]%>"></a>
							</div>
							<div class="goods-title">
								<a href="goods/goodsDetail.jsp?gid=<%=good1.getId()%>"
									class="title"><%=good1.getGoodName()%></a>
							</div>
							<p class="goods-info"><%=good1.getIntroduction()%></p>
							<p class="goods-price"><%=good1.getPrice()%>元
							</p>
						</li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
			<!-- 配件 -->
			<div class="parts clearfix">
				<div class="title">
					<span class="page-title">配件</span>
					<div class="more">
						<a href="javascript:" class="more-text active">耳机</a> <a
							href="javascript:" class="more-text">手机壳</a> <a
							href="javascript:" class="more-text">手机膜</a>
					</div>
				</div>
				<div class="col-md-4">
					<ul class="left-thumb-list clearfix">
						<li class="goods-item left-m-thumb"><a
							href="goods/goodsDetail.jsp?gid=48" class="m-thumb-link"><img
								src="images/lunbo/hot5.jpg" class="m-thumb"></a></li>
						<li class="goods-item left-m-thumb"><a
							href="goods/goodsDetail.jsp?gid=46" class="m-thumb-link"><img
								src="images/lunbo/h2.jpg" class="m-thumb"></a></li>
					</ul>
				</div>
				<div class="col-md-8">
					<ul class="goods-list list-active hot-list clearfix">
						<%
							List<Goods> list5 = gs.getGoodByCondition("typeid=12");
							for (Goods good : list5) {
								String[] img = imgs.goodsImg(good.getId());
						%>
						<li class="goods-item goods-item-m">
							<div class="goods-thumb">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"><img
									src="<%="upload/" + img[0]%>"></a>
							</div>
							<div class="goods-title">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"
									class="title"><%=good.getGoodName()%></a>
							</div>
							<p class="goods-info"><%=good.getIntroduction()%></p>
							<p class="goods-price"><%=good.getPrice()%>元
							</p>
						</li>
						<%
							}
						%>
					</ul>
					<ul class="goods-list list tv-list clearfix">
						<%
							List<Goods> list3 = gs.getGoodByCondition("typeid=10");
							for (Goods good : list3) {
								String[] img = imgs.goodsImg(good.getId());
						%>
						<li class="goods-item goods-item-m">
							<div class="goods-thumb">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"><img
									src="<%="upload/" + img[0]%>"></a>
							</div>
							<div class="goods-title">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"
									class="title"><%=good.getGoodName()%></a>
							</div>
							<p class="goods-info"><%=good.getIntroduction()%></p>
							<p class="goods-price"><%=good.getPrice()%>元
							</p>
						</li>
						<%
							}
						%>
					</ul>
					<ul class="goods-list list tv-list clearfix">
						<%
							List<Goods> list4 = gs.getGoodByCondition("typeid=11");
							for (Goods good : list4) {
								String[] img = imgs.goodsImg(good.getId());
						%>

						<li class="goods-item goods-item-m">
							<div class="goods-thumb">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"><img
									src="<%="upload/" + img[0]%>"></a>
							</div>
							<div class="goods-title">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"
									class="title"><%=good.getGoodName()%></a>
							</div>
							<p class="goods-info"><%=good.getIntroduction()%></p>
							<p class="goods-price"><%=good.getPrice()%>元
							</p>
						</li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
			<!-- 推荐 -->
			<div class="reco-goods">
				<div class="s-lg-title">
					<span class="page-title">为你推荐</span>
					<div class="title-more">
						<a href="javascript:" class="reco-prev btn-default"><i
							class="iconfont">&#xe697;</i></a> <a href="javascript:"
							class="reco-next"><i class="iconfont">&#xe6a7;</i></a>
					</div>
				</div>
				<div class="col-md-12">
					<ul class="reco-list clearfix">
						<%
							List<Goods> list6 = gs.getGoodByCondition("typeid=13");
							for (Goods good : list6) {
								String[] img = imgs.goodsImg(good.getId());
						%>
						<li class="goods-item goods-item-m">
							<div class="goods-thumb">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"><img
									src="<%="upload/" + img[0]%>"></a>
							</div>
							<div class="goods-title">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"
									class="title"><%=good.getGoodName()%></a>
							</div>
							<p class="goods-info"><%=good.getIntroduction()%></p>
							<p class="goods-price"><%=good.getPrice()%>元
							</p>
						</li>
						<%
							}
						%>
						<%
							List<Goods> list7 = gs.getGoodByCondition("typeid=14");
							for (Goods good : list7) {
								String[] img = imgs.goodsImg(good.getId());
						%>
						<li class="goods-item goods-item-m">
							<div class="goods-thumb">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"><img
									src="<%="upload/" + img[0]%>"></a>
							</div>
							<div class="goods-title">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"
									class="title"><%=good.getGoodName()%></a>
							</div>
							<p class="goods-info"><%=good.getIntroduction()%></p>
							<p class="goods-price"><%=good.getPrice()%>元
							</p>
						</li>
						<%
							}
						%>
						<%
							List<Goods> list8 = gs.getGoodByCondition("typeid=15");
							for (Goods good : list8) {
								String[] img = imgs.goodsImg(good.getId());
						%>
						<li class="goods-item goods-item-m">
							<div class="goods-thumb">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"><img
									src="<%="upload/" + img[0]%>"></a>
							</div>
							<div class="goods-title">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"
									class="title"><%=good.getGoodName()%></a>
							</div>
							<p class="goods-info"><%=good.getIntroduction()%></p>
							<p class="goods-price"><%=good.getPrice()%>元
							</p>
						</li>
						<%
							}
						%>
						<%
							List<Goods> list9 = gs.getGoodByCondition("typeid=16");
							for (Goods good : list9) {
								String[] img = imgs.goodsImg(good.getId());
						%>
						<li class="goods-item goods-item-m">
							<div class="goods-thumb">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"><img
									src="<%="upload/" + img[0]%>"></a>
							</div>
							<div class="goods-title">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"
									class="title"><%=good.getGoodName()%></a>
							</div>
							<p class="goods-info"><%=good.getIntroduction()%></p>
							<p class="goods-price"><%=good.getPrice()%>元
							</p>
						</li>
						<%
							}
						%>
						<%
							List<Goods> list10 = gs.getGoodByCondition("typeid=17");
							for (Goods good : list10) {
								String[] img = imgs.goodsImg(good.getId());
						%>
						<li class="goods-item goods-item-m">
							<div class="goods-thumb">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"><img
									src="<%="upload/" + img[0]%>"></a>
							</div>
							<div class="goods-title">
								<a href="goods/goodsDetail.jsp?gid=<%=good.getId()%>"
									class="title"><%=good.getGoodName()%></a>
							</div>
							<p class="goods-info"><%=good.getIntroduction()%></p>
							<p class="goods-price"><%=good.getPrice()%>元
							</p>
						</li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- 页脚 -->
	<%@include file="common/foot.jsp"%>

</body>
<script src="js/main.js"></script>
<script src="js/holder.min.js"></script>
</html>
