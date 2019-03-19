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

<script type="text/javascript">
	//商品信息分页
	function fenye(x) {
		$(".goodinfo tr:gt(0)").html(""); //清空tr标签之后的tr标签里面的内容，防止重复
		var pages = 1; //设置默认页码为1
		var showpage = $("#stucurrent").html(); //得到当前页码
		if (showpage != "")
			var pages = parseInt(showpage);
		var pagesize = 8; //设定页大小
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
		//通过post异步请求获取数据(获取所有商品详情)
		$.ajax({
			url : "../doGoodsServlet",
			data : {
				type : 'listgood',
				pagesize : pagesize,
				page : pages
			},
			type : 'post',
			dataType : 'html',
			success : function(data) {
				var obj = eval(data);
				for (var i = 0; i < obj.length - 1; i++) {
					var x1 = "<tr><td style=\"text-align:center;\">" + obj[i].goodName
						+ "</td><td style=\"text-align:center;\">" + obj[i].price
						+ "</td><td style=\"text-align:center;\">" + obj[i].config
						+ "</td><td style=\"text-align:center;\">" + obj[i].productDate
						+ "</td><td style=\"text-align:center;\">" + obj[i].count
						+ "</td><td style=\"text-align:center;\">" + obj[i].introduction
						+ "</td><td style=\"text-align:center;\">" + obj[i].description
						+ "</td><td style=\"text-align:center;\"><a class=\"a1\" href='editGoods.jsp?id=" + obj[i].id
						+ "'>修改</a><a class=\"a1\" href='javascript:delety(" + obj[i].id
						+ ")'>删除</a><a class=\"a1\" href='imgEdit.jsp?id=" + obj[i].id
						+ "'>上传图片</a></td></tr>";
					$(".goodinfo>table").append(x1);
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
	//对商品进行删除
	function delety(id) {
		if (confirm("确定删除该商品?")) {
			$.post(
				"../doGoodsServlet",
				{
					id : id,
					type : "delgood"
				},
				function(data) {
					if (data > 0) {
						alert("商品删除成功!");
						window.location.href = 'listGoods.jsp';
					} else {
						alert("商品删除失败!");
						window.location.href = 'listGoods.jsp';
					}
				}
			);
		}
	}
</script>
<style type="text/css">
.ty-title h3 {
	padding-bottom: 10px;
	color: green;
	font-weight: bolder;
}

table {
	margin-top: 30px;
}

table th, td {
	padding: 2px 0px;
}

#content a {
	text-decoration: none;
	border: 1px solid #dedede;
	padding: 2px 3px;
}

#content input[type='text'] {
	text-align: center;
}

.a1 {
	color: #fff;
	margin: 0px 5px;
	padding: 2px 10px;
	background-color: rgb(245, 102, 0);
	border-radius: 3px;
}

.a1:HOVER {
	text-decoration: none;
	color: #000;
	background-color: rgb(255, 66, 66);
}
</style>
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
					<div>
						<div class="ty-title">
							<h3>商品详情</h3>
							<div class="goodinfo">
								<table border="1">
									<tr>
										<th style="text-align:center;width:130px;">商品名称</th>
										<th style="text-align:center;width:100px;">商品价格</th>
										<th style="text-align:center;width:160px;">商品配置</th>
										<th style="text-align:center;width:150px;">商品生产日期</th>
										<th style="text-align:center;width:70px;">商品数量</th>
										<th style="text-align:center;width:200px;">商品介绍</th>
										<th style="text-align:center;width:200px;">商品描述</th>
										<th style="text-align:center;width:260px;">商品操作</th>
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
										id="stucurrent"></span> /<span id="totalpages"></span>&nbsp;&nbsp;<input
										type="text" style="width:30px;" size="2" name="pages">
									<input type="button" onclick="fenye('e')" value="跳转">
								</div>
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
