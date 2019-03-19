<%@page import="xm.chen.service.impl.UserServiceImpl"%>
<%@page import="xm.chen.service.UserService"%>
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
<%
	User admin = (User) session.getAttribute("user");
	if (admin == null || admin.getId() < 0) {
		response.sendRedirect("login.jsp");
	}
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
					<script type="text/javascript">
						function fenye(x) {
							$(".usinfo tr:gt(0)").html(""); //清空tr标签之后的tr标签里面的内容，防止重复
							var pages = 1; //设置默认页码为1
							var showpage = $("#stucurrent").html(); //得到当前页码
							if (showpage != "")
								var pages = parseInt(showpage);
							var pagesize = 10; //设定页大小
							var maxpage = $("#totalpages").html(); //获取最大页码数
							if (x == "a") //a参数代表首页
								pages = 1;
							if (x == "b" && pages > 1) pages--; //b参数代表上一页
							if (x == "c" && pages < maxpage) pages++; //c参数代表下一页
							if (x == "d") //d代表尾页
								pages = maxpage;
							if (x == "e") { //e代表根据输入页数进行分页
								var k = $("input[name='pages']").val();
								if (k > 0 && k <= maxpage) {
									pages = k;
								} else {
									alert("页码输入错误!");
								}
							}
							//通过post异步请求获取数据(获取所有商品评论详情)
							$.ajax({
								url : "../doLoiginServlet",
								data : {
									type : 'uslist',
									pagesize : pagesize,
									page : pages
								},
								type : 'post',
								dataType : 'html',
								success : function(data) {
									var obj = eval(data);
									for (var i = 0; i < obj.length - 1; i++) {
										var x1 = "<tr class=\"info\"><td style=\"text-align:center;\">" +obj[i].realname.replace(/.(?=.)/g,'*')
											+ "</td><td style=\"text-align:center;\">" + obj[i].phone.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2')
											+ "</td><td style=\"text-align:center;\">" + obj[i].address
											+ "</td></tr>";
										$(".usinfo>table").append(x1);
									}
									$("input[name='pages']").val(obj[obj.length - 1].current); //重置文本框中的页码
									$("#stucurrent").html((obj[obj.length - 1].current)); //重置当前页码
									$("#totalpages").html((obj[obj.length - 1].totalpages)); //重置总页数
								},
								error : function(data) {
									alert("错误");
								}
							});
						}
						$(function() {
							fenye("a");
						});
					</script>
					<div class="us">
						<div class="usinfo">
							<table border="1" style="width:1080px;margin:2px 3px;">
								<tr>
									<th style="text-align:center;width:50px;">用户名</th>
									<th style="text-align:center;width:50px;">手机号</th>
									<th style="text-align:center;width:100px;">地址</th>
								</tr>
							</table>
						</div>
						<div id="content">
							<div class="pages"
								style="position: absolute;left:400px;top:530px">
								<a href="javascript:fenye('a')">首页</a>&nbsp;&nbsp; <a
									href="javascript:fenye('b')">上一页</a>&nbsp;&nbsp; <a
									href="javascript:fenye('c')">下一页</a>&nbsp;&nbsp; <a
									href="javascript:fenye('d')">尾页</a>&nbsp;&nbsp; <span
									id="stucurrent"></span> /<span id="totalpages"></span> <input
									type="text" style="width:30px;" size="2" name="pages">
								<input type="button" onclick="fenye('e')" value="跳转">
							</div>
						</div>
					</div>
				</div>
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
