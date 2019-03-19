<%@page import="xm.chen.service.impl.CommentServiceImpl"%>
<%@page import="xm.chen.service.CommentService"%>
<%@page import="xm.chen.service.impl.GoodServiceImpl"%>
<%@page import="xm.chen.service.GoodService"%>
<%@page import="xm.chen.service.impl.GoodsTypeServiceImpl"%>
<%@page import="xm.chen.service.GoodTypeService"%>
<%@page import="xm.chen.service.impl.OrderServiceImpl"%>
<%@page import="xm.chen.service.OrderService"%>
<%@page import="xm.chen.modal.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理者后台</title>
<link href="../css/bootstrap.min.css" title="" rel="stylesheet" />
<link title="" href="../css/style.css" rel="stylesheet" type="text/css" />
<link title="green" href="../css/dermagreen.css" rel="stylesheet"
	type="text/css" />
<script src="../js/jquery-1.8.2.js"></script>
<script src="../js/jquery.cookie.js" type="text/javascript"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
</head>
<%@include file="../common/Usercheck1.jsp"%>
<%
	User admin = (User) session.getAttribute("user");
%>
<body>
	<nav class="nav navbar-default navbar-mystyle navbar-fixed-top">
		<div class="navbar-header">
			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a href="../index.jsp" class="navbar-brand mystyle-brand"><span
				class="glyphicon glyphicon-home"></span></a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="li-border"><a class="mystyle-color"
					href="../user/adminmanager.jsp">后台管理</a></li>
			</ul>

			<ul class="nav navbar-nav pull-right">
				<li class="li-border dropdown"><a class="mystyle-color"
					data-toggle="dropdown"> <span
						class="glyphicon glyphicon-search"></span> 搜索
				</a></li>
				<li class="dropdown li-border"><a
					class="dropdown-toggle mystyle-color" data-toggle="dropdown"><span
						class=" glyphicon glyphicon-magnet"></span>&nbsp;&nbsp;<%=admin.getRealname()%></a>
				</li>
				<li class="dropdown"><a href="../user/loginout.jsp"
					class="dropdown-toggle mystyle-color" style="padding-right:80px;"
					data-toggle="dropdown"><span class=" glyphicon glyphicon-off"></span>&nbsp;&nbsp;注销</a></li>
			</ul>
		</div>
	</nav>
	<div class="down-main">
		<div class="left-main left-full">
			<div class="sidebar-fold">
				<span class="glyphicon glyphicon-menu-hamburger"></span>
			</div>
			<div class="subNavBox">
				<div class="sBox">
					<div class="subNav sublist-down">
						<span class="title-icon glyphicon glyphicon-chevron-down"></span><span
							class="sublist-title">消息中心</span>
					</div>
					<ul class="navContent" style="display:block">
						<li class="active">
							<div class="showtitle" style="width:100px;">
								<img src="img/leftimg.png" />数据详情
							</div> <a href="../user/adminmanager.jsp"><span
								class="sublist-icon glyphicon glyphicon-envelope"></span><span
								class="sub-title">数据详情</span></a>
						</li>
						<li>
							<div class="showtitle" style="width:100px;">
								<img src="img/leftimg.png" />账号管理
							</div> <a href="../user/userlist.jsp"><span
								class="sublist-icon glyphicon glyphicon-user"></span><span
								class="sub-title">账号管理</span></a>
						</li>
					</ul>
				</div>
				<div class="sBox">
					<div class="subNav sublist-up">
						<span class="title-icon glyphicon glyphicon-chevron-up"></span><span
							class="sublist-title">商品类型管理</span>
					</div>
					<ul class="navContent" style="display:none">
						<li>
							<div class="showtitle" style="width:100px;">
								<img src="img/leftimg.png" />添加商品类型
							</div> <a href="../goodsType/addGoodsType.jsp"><span
								class="sublist-icon glyphicon glyphicon-cloud-upload"></span><span
								class="sub-title">添加商品类型</span></a>
						</li>
						<li>
							<div class="showtitle" style="width:100px;">
								<img src="img/leftimg.png" />查询&修改&删除
							</div> <a href="../goodsType/listGoodsType.jsp"><span
								class="sublist-icon glyphicon glyphicon-search"></span><span
								class="sub-title">查询&修改&删除</span></a>
						</li>
					</ul>
				</div>
				<div class="sBox">
					<div class="subNav sublist-up">
						<span class="title-icon glyphicon glyphicon-chevron-up"></span><span
							class="sublist-title">商品管理</span>
					</div>
					<ul class="navContent" style="display:none">
						<li>
							<div class="showtitle" style="width:100px;">
								<img src="img/leftimg.png" />添加商品
							</div> <a href="../goods/addGoods.jsp"><span
								class="sublist-icon glyphicon glyphicon-plus-sign"></span><span
								class="sub-title">添加商品</span></a>
						</li>
						<li>
							<div class="showtitle" style="width:100px;">
								<img src="img/leftimg.png" />商品列表
							</div> <a href="../goods/listGoods.jsp"><span
								class="sublist-icon glyphicon glyphicon-list"></span><span
								class="sub-title">商品列表</span></a>
						</li>
					</ul>
				</div>
				<div class="sBox">
					<div class="subNav sublist-up">
						<span class="title-icon glyphicon glyphicon-chevron-up"></span><span
							class="sublist-title">订单管理</span>
					</div>
					<ul class="navContent" style="display:none">
						<li>
							<div class="showtitle" style="width:100px;">
								<img src="img/leftimg.png" />商品订单
							</div> <a href="../order/orderList.jsp"><span
								class="sublist-icon glyphicon glyphicon-paperclip"></span><span
								class="sub-title">商品订单</span></a>
						</li>
					</ul>
				</div>
				<div class="sBox">
					<div class="subNav sublist-up">
						<span class="title-icon glyphicon glyphicon-chevron-up"></span><span
							class="sublist-title">评论管理</span>
					</div>
					<ul class="navContent" style="display:none">
						<li>
							<div class="showtitle" style="width:100px;">
								<img src="img/leftimg.png" />商品评论
							</div> <a href="../comment/commentList.jsp"><span
								class="sublist-icon glyphicon glyphicon-comment"></span><span
								class="sub-title">商品评论</span></a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="right-product my-index right-full">
			<div class="container-fluid">
				<div class="info-center">
					<!---title----->
					<div class="info-title">
						<div class="pull-left">
							<h4>
								<strong>Administrator，</strong>
							</h4>
							<p>欢迎登录管理系统！</p>
						</div>
						<%
							Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   
							int year = c.get(Calendar.YEAR);
							int month = c.get(Calendar.MONTH) + 1;
							int date = c.get(Calendar.DATE);
							int hour = c.get(Calendar.HOUR_OF_DAY);
							int minute = c.get(Calendar.MINUTE);
							int mWay = c.get(Calendar.DAY_OF_WEEK) - 1;
							GoodTypeService gt = new GoodsTypeServiceImpl();
							OrderService o = new OrderServiceImpl();
							GoodService g = new GoodServiceImpl();
							CommentService com = new CommentServiceImpl();
							int typecount = gt.getcount("");//获取数量
							int ocount = o.getcount("");
							int gcount = g.Count("");
							int comcount = com.Count("");
						%>
						<div class="time-title pull-right">
							<div class="year-month pull-left">
								<p>
									星期<%=mWay%></p>
								<p>
									<span><%=year%></span>年<%=month%>月<%=date%>日
								</p>
							</div>
							<div class="hour-minute pull-right">
								<strong><%=hour%>:<%=minute%></strong>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div style="height:30px;"></div>
					<!--重要数据显示 -->
					<div class="content-list">
						<div class="row">
							<div class="col-md-3">
								<div class="content">
									<div class="w30 left-icon pull-left">
										<span class="glyphicon glyphicon-file blue"></span>
									</div>
									<div class="w70 right-title pull-right">
										<div class="title-content">
											<p>商品类型</p>
											<h3 class="number"><%=typecount%></h3>
										</div>
									</div>
									<div class="clearfix" style="height:110px;"></div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="content">
									<div class="w30 left-icon pull-left">
										<span class="glyphicon glyphicon-file violet"></span>
									</div>
									<div class="w70 right-title pull-right">
										<div class="title-content">
											<p>商品数量</p>
											<h3 class="number"><%=gcount%></h3>

										</div>
									</div>
									<div class="clearfix" style="height:110px;"></div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="content">
									<div class="w30 left-icon pull-left">
										<span class="glyphicon glyphicon-file orange"></span>
									</div>
									<div class="w70 right-title pull-right">
										<div class="title-content">
											<p>订单数量</p>
											<h3 class="number"><%=ocount%></h3>

										</div>
									</div>
									<div class="clearfix" style="height:110px;"></div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="content">
									<div class="w30 left-icon pull-left">
										<span class="glyphicon glyphicon-file green"></span>
									</div>
									<div class="w70 right-title pull-right">
										<div class="title-content">
											<p>评论数量</p>
											<h3 class="number"><%=comcount%></h3>

										</div>
									</div>
									<div class="clearfix" style="height:110px;"></div>
								</div>
							</div>
						</div>
						<!-- 内部内容 -->
						<div class="page-container">
							<div id="container"
								style="width:1080px;height:300px;padding-left:15px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="../js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="../js/huitu/layer.js"></script>
	<script type="text/javascript" src="../js/huitu/H-ui.min.js"></script>
	<script type="text/javascript" src="../js/huitu/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript" src="../js/huitu/highcharts.js"></script>
	<script type="text/javascript" src="../js/huitu/exporting.js"></script>
	<script type="text/javascript">
		$(function() {
			$.post("../doOrderServlet", {
				type : 'tj'
			}, function(data) {
				var obj = eval(data);
				var x1 = "[";
				var y1 = "[{name:'2017.12-2018.1',data:[";
				$.each(obj, function(index, item) {
					x1 += "'" + item.time.substr(5, 5) + "',";
					y1 += item.zje + ',';
				});
				x1 = x1.substr(0, x1.length - 1) + "]";
				y1 = y1.substr(0, y1.length - 1) + "]}]";
				$("#x").append(y1);
	
	
				Highcharts.chart('container', {
					title : {
						text : '每天的下单金额',
						x : -20 //center
					},
					subtitle : {
						text : 'Source: 来源数据库xiaomi',
						x : -20
					},
					xAxis : {
						categories : eval(x1)
					},
					yAxis : {
						title : {
							text : '总金额 (元)'
						},
						plotLines : [ {
							value : 0,
							width : 2,
							color : '#808080'
						} ]
					},
					tooltip : {
						valueSuffix : '元'
					},
					legend : {
						layout : 'vertical',
						align : 'right',
						verticalAlign : 'middle',
						borderWidth : 0
					},
					series : eval(y1)
				});
			});
		});
	</script>

	<script type="text/javascript">
		$(function() {
			/*换肤*/
			$(".dropdown .changecolor li").click(function() {
				var style = $(this).attr("id");
				$("link[title!='']").attr("disabled", "disabled");
				$("link[title='" + style + "']").removeAttr("disabled");
	
				$.cookie('mystyle', style, {
					expires : 7
				}); // 存储一个带7天期限的 cookie 
			})
			var cookie_style = $.cookie("mystyle");
			if (cookie_style != null) {
				$("link[title!='']").attr("disabled", "disabled");
				$("link[title='" + cookie_style + "']").removeAttr("disabled");
			}
			/*左侧导航栏显示隐藏功能*/
			$(".subNav").click(function() {
				/*显示*/
				if ($(this).find("span:first-child").attr('class') == "title-icon glyphicon glyphicon-chevron-down") {
					$(this).find("span:first-child").removeClass("glyphicon-chevron-down");
					$(this).find("span:first-child").addClass("glyphicon-chevron-up");
					$(this).removeClass("sublist-down");
					$(this).addClass("sublist-up");
				}
				/*隐藏*/
				else {
					$(this).find("span:first-child").removeClass("glyphicon-chevron-up");
					$(this).find("span:first-child").addClass("glyphicon-chevron-down");
					$(this).removeClass("sublist-up");
					$(this).addClass("sublist-down");
				}
				// 修改数字控制速度， slideUp(500)控制卷起速度
				$(this).next(".navContent").slideToggle(300).siblings(".navContent").slideUp(300);
			})
			/*左侧导航栏缩进功能*/
			$(".left-main .sidebar-fold").click(function() {
	
				if ($(this).parent().attr('class') == "left-main left-full") {
					$(this).parent().removeClass("left-full");
					$(this).parent().addClass("left-off");
	
					$(this).parent().parent().find(".right-product").removeClass("right-full");
					$(this).parent().parent().find(".right-product").addClass("right-off");
	
				} else {
					$(this).parent().removeClass("left-off");
					$(this).parent().addClass("left-full");
	
					$(this).parent().parent().find(".right-product").removeClass("right-off");
					$(this).parent().parent().find(".right-product").addClass("right-full");
	
				}
			})
	
			/*左侧鼠标移入提示功能*/
			$(".sBox ul li").mouseenter(function() {
				if ($(this).find("span:last-child").css("display") == "none") {
					$(this).find("div").show();
				}
			}).mouseleave(function() {
				$(this).find("div").hide();
			})
		})
	</script>
</body>
</html>
