<%@page import="java.awt.Window"%>
<%@page import="xm.chen.modal.User"%>
<%@page import="xm.chen.service.impl.GoodsTypeServiceImpl"%>
<%@page import="xm.chen.service.GoodTypeService"%>
<%@page import="xm.chen.modal.GoodType"%>
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
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript"
	src="../js/jquery.validate.messages_cn.js"></script>
<script type="text/javascript"></script>
<style type="text/css">
input.error {
	border: solid 1px #ff2200;
}

form label.error {
	color: #ff2200;
	font-size: 0.8em;
	font-weight: bolder;
}

.addgoods {
	padding: 20px;
}

.ad {
	padding: 3px 0px;
	color: green;
}

.ty-title {
	padding-left: 70px;
	padding-bottom: 10px;
	color: green;
	font-weight: bolder;
}

.style1>input {
	border: 0px;
	width: 80px;
	margin-left: 100px;
	margin-top: 10px;
	background-color: #00bb00;
	color: #fff;
}

.style1>input:HOVER {
	background-color: green;
}
</style>
</head>
<%@include file="../common/Usercheck1.jsp"%>
<%
	User admin1 = (User) session.getAttribute("user");
	GoodTypeService gts = new GoodsTypeServiceImpl();
	List<GoodType> list = gts.getGoodType("1=1");
%>
<body>
	<%@include file="../common/Usercheck.jsp"%>
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
						class=" glyphicon glyphicon-magnet"></span>&nbsp;&nbsp;<%=admin1.getRealname()%></a>
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
					<div class="addgoods">
						<div class="ty-title">
							<h3>商品添加</h3>
						</div>
						<form action="../doGoodsServlet?type=addgood" method="post">
							<div class="ad">
								<span>选择类型：</span> <select name="goodstype">
									<option value="0">请选择</option>
									<%
										for (GoodType gt : list) {
									%>
									<option value="<%=gt.getId()%>"><%=gt.getTypeName()%></option>
									<%
										}
									%>
								</select>
							</div>
							<div class="ad">
								<span>商品名称：</span> <input type="text" name="name">
							</div>
							<div class="ad">
								<span>商品价格：</span> <input type="text" name="price">
							</div>
							<div class="ad">
								<span>商品数量：</span> <input type="text" name="count">
							</div>
							<div class="ad">
								<span>上市时间：</span> <input type="text" name="time">
							</div>
							<div class="ad">
								<span>产品配置：</span><br>
								<textarea rows="1" cols="30" name="config">	
								 </textarea>
							</div>
							<div class="ad">
								<span>商品简介：</span><br>
								<textarea rows="2" cols="30" name="intro">	
								 </textarea>
							</div>
							<div class="ad">
								<span>商品描述：</span><br>
								<textarea rows="2" cols="30" name="remark">
			        </textarea>
							</div>
							<div class="style1">
								<input type="submit" class="btn" value="提交">
							</div>
						</form>
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
			$("form").validate({
				rules : {
					name : {
						required : true,
						maxlength : 50
					},
					price : {
						required : true,
						number : true,
						min : 0.1
					},
					count : {
						required : true,
						number : true,
						min : 1
					},
					goodstypeid : {
						required : true
					},
					config : {
						required : true,
						maxlength : 100
					},
					time : {
						required : true,
						date : true
					},
					introduction : {
						maxlength : 100
					},
					remark : {
						maxlength : 200
					}
				},
				messages : {
					name : {
						required : "请输入商品名称!"
					},
					price : {
						required : "请输入商品价格!",
						number : "商品价格只能输入数字!",
						range : "商品价格只能大于0!"
					},
					goodstypeid : {
						required : "请选择商品类型!"
					},
					count : {
						required : "请输入商品数量!",
						number : "商品数量只能输入数字!",
						range : "商品数量只能大于0!"
					},
					config : {
						required : "请输入商品的配置!",
						maxlenght : "最大长度为100位!"
					},
					time : {
						required : "请输入上市时间!",
						date : "请输入正确的时间格式!"
					},
					introduction : {
						maxlength : "最大长度为100位!"
					},
					remark : {
						maxlength : "最大长度为200位!"
					}
				}
			}
			);
		})
	</script>
</body>
</html>
