<%@page import="xm.chen.modal.OrderStatus"%>
<%@page import="xm.chen.modal.Orders"%>
<%@page import="xm.chen.service.impl.OrderServiceImpl"%>
<%@page import="xm.chen.service.OrderService"%>
<%@page import="xm.chen.service.impl.GoodServiceImpl"%>
<%@page import="xm.chen.service.GoodService"%>
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
<link rel="stylesheet" type="text/css" href="../css/pay.css" />
<script src="../js/jquery-1.8.2.js"></script>
<script src="../js/jquery.cookie.js" type="text/javascript"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
<script src="../js/houtai.js" type="text/javascript"></script>
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
							class="sublist-title">用户中心</span>
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
					<!-- 内部内容 -->
					<%
						User user = (User) session.getAttribute("user");
						if (user == null) {
							response.sendRedirect("../user/login.jsp");
							return;
						}
						GoodService g = new GoodServiceImpl();
						OrderService o = new OrderServiceImpl();

						//分页
						int currentpage = 1;//当前页变量
						int pagesize = 10;//页大小

						int total = o.getcount("");//总条数
						int maxpage = (total + pagesize - 1) / pagesize;
						//防止页码不是数字报异常
						try {
							currentpage = Integer.parseInt(request.getParameter("page"));
						} catch (Exception e) {
						}
						//限制范围
						if (currentpage < 1)
							currentpage = 1;
						if (currentpage > maxpage)
							currentpage = maxpage;
						//获取分页数据
						List<Orders> orderlist = null;
						orderlist = o.getOrdersByConditionPage(pagesize, currentpage, "1=1", true);
					%>
					<div>
						<div>
							<h3>订单详情</h3>
						</div>
						<div class="order">
							<div class="ord">
								<table border="1" cellpadding="0" cellspacing="1">
									<tr class="title">
										<th>订单ID</th>
										<th>下单时间</th>
										<th>订单状态</th>
										<th>订单总金额</th>
										<th>详情</th>
									</tr>
									<%
										for (Orders order : orderlist) {
									%>
									<tr class="info">
										<input type="hidden" value="<%=order.getId()%>" />
										<td><%=order.getId()%></td>
										<td><%=order.getOrderTime()%></td>
										<td class="sta<%=order.getId()%>"><%=OrderStatus.values()[order.getOrderstatus()].toString()%></td>
										<td>&yen;<%=order.getTotalmoney()%></td>
										<td>
											<%
												if (order.getOrderstatus() == OrderStatus.未付款.ordinal()) {
											%><label>等待付款</label> <%
 										} else if (order.getOrderstatus() == OrderStatus.等待发货.ordinal()
 										|| order.getOrderstatus() == OrderStatus.已发货.ordinal()
 										|| order.getOrderstatus() == OrderStatus.配送中.ordinal()
 										|| order.getOrderstatus() == OrderStatus.运送中.ordinal()) {
 										%> <a class="a1" href="javascript:edit('<%=order.getId()%>')">修改订单状态</a> <%
 										} else if (order.getOrderstatus() == OrderStatus.已收货.ordinal()) {
 										%> <label>等待评价</label> <%
 										} else if (order.getOrderstatus() == OrderStatus.已评价.ordinal()) {
										%> <label>订单完成</label> <%
 										}
 										%>
										</td>
									</tr>
									<%
										}
									%>
								</table>
							</div>
						</div>
						<!--订单表结束-->
						<div class="page">
							<ul>
								<li><a href="orderList.jsp?page=1">首页</a></li>
								<li><a href="orderList.jsp?spage=<%=currentpage - 1%>">上一页</a></li>
								<%
									int k = currentpage + 3;
									if (currentpage + 1 > maxpage || currentpage + 2 > maxpage || currentpage + 3 > maxpage)
										k = maxpage;
									for (int j = currentpage; j <= k; j++) {
										if (currentpage == j) {
								%>
								<li id="currentpage"><a href="orderList.jsp?page=<%=j%>"><%=j%></a></li>
								<%
									} else {
								%>
								<li><a href="orderList.jsp?page=<%=j%>"><%=j%></a></li>
								<%
									}
									}
								%>
								<li><a href="orderList.jsp?page=<%=currentpage + 1%>">下一页</a></li>
								<li><a href="orderList.jsp?page=<%=maxpage%>">尾页</a></li>
								<li id="pagestatistics">共<%=maxpage%>页
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 详情 -->
	<div class="m-modal">
		<div class="m-modal-dialog">
			<div class="m-top">
				<h4 class="m-modal-title">订单状态修改</h4>
				<span class="m-modal-close">&times;</span>
			</div>
			<div class="m-middle">
				<!--content-->
				<p>
					订单ID:<span class="ddid"></span>
				</p>
				<p>
					<select name="edit">
						<option value="已发货">已发货</option>
						<option value="运送中">运送中</option>
						<option value="配送中">配送中</option>
					</select>
				</p>
			</div>
			<div class="m-bottom">
				<button class="m-btn-sure" onclick="stastuschange()">确定</button>
				<button class="m-btn-cancel">取消</button>
			</div>
		</div>
	</div>
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
