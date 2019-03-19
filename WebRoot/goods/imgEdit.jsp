<%@page import="xm.chen.service.impl.GoodServiceImpl"%>
<%@page import="xm.chen.service.GoodService"%>
<%@page import="xm.chen.service.impl.ImagesServiceImpl"%>
<%@page import="xm.chen.service.ImagesService"%>
<%@page import="xm.chen.modal.Goods"%>
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
	$(function() {
		var goodid = $("input[type='hidden']").val();
		$.ajax({
			url : "../doImgServlet",
			data : {
				id : goodid,
				type : 'getgoodinfo'
			},
			type : 'post',
			dataType : 'html',
			success : function(data) {
				$(".info").append(data);
			},
			error : function(data) {
				alert("错误");
			}
		});
	});

	function preview(file, li) {
		var prevDiv0 = document.getElementById(li);
		if (file.files && file.files[0]) {
			var reader = new FileReader();
			reader.onload = function(evt) {
				var data = evt.target.result;
				var image = new Image();
				image.src = data;
				prevDiv0.innerHTML = '<img style=\"width:150px;height:150px;\" src="'
					+ evt.target.result + '" />';
			}
			reader.readAsDataURL(file.files[0]);
		}
	}
	function delImgs(id, tu) {
		if (confirm("您确定删除该图片!")) {
			$.ajax({
				url : "../doImgServlet",
				data : {
					type : 'delImg',
					id : id
				},
				type : "POST",
				dataType : "html",
				success : function(data) {
					if (data > 0) {
						alert("图片删除成功!");
						$("#" + tu).remove();
						
					} else {
						alert("删除失败!");
					}
				},
				error : function(data) {
					alert(error);
				}
			});
		}

	}
</script>
<style type="text/css">
li {
	list-style: none;
}

.addtype {
	padding: 20px;
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
	height: 35px;
	line-height: 35px;
	margin-left: 100px;
	margin-top: 10px;
	background-color: #00bb00;
	color: #fff;
}

.style1>input:HOVER {
	background-color: green;
}

.imgupload {
	width: 1024px;
}

.tu li {
	border: 1px solid #dedede;
	width: 150px;
	height: 150px;
	margin: 2px;
	float: left;
}

.tu li img {
	width: 100%;
	height: 100%;
}

.del li {
	margin-left: 5px;
	width: 150px;
	float: left;
}

.del li a {
	text-decoration: none;
}
</style>
</head>
<%@include file="../common/Usercheck1.jsp"%>
<%
	User admin = (User) session.getAttribute("user");
	int id = 0;
	Goods goods = null;
	ImagesService imgdao = new ImagesServiceImpl();
	try {
		id = Integer.parseInt(request.getParameter("id"));
		GoodService gdao = new GoodServiceImpl();
		goods = gdao.getGoodById(id);
	} catch (Exception e) {
		System.out.print("id不正确！");
	}
	//判断商品是否存在 不存在返回商品列表
	if (goods == null) {
		response.sendRedirect("listGoods.jsp");
	}
	String[] imglist = imgdao.goodsImg(id);
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
					<div class="addtype">
						<input type="hidden" value="<%=id%>" name="gid" />
						<div class="ty-title">
							<h3>商品图片上传</h3>
						</div>
						<div class="info"></div>
						<div class="imgupload">
							<ul class="tu">
								<%
									if (!imglist[0].equals("没有图片请上传")) {
								%>
								<li><img alt="" id="0" src="<%=imglist[0]%>"></li>
								<%
									} else {
								%>
								<li><img alt="" src="../images/phone/imgadd.png"></li>
								<%
									}
								%>
								<%
									if (!imglist[1].equals("没有图片请上传")) {
								%>
								<li><img alt="" id="1" src="<%=imglist[1]%>"></li>
								<%
									} else {
								%>
								<li><img alt="" src="../images/phone/imgadd.png"></li>
								<%
									}
								%>
								<%
									if (!imglist[2].equals("没有图片请上传")) {
								%>
								<li><img alt="" id="2" src="<%=imglist[2]%>"></li>
								<%
									} else {
								%>
								<li><img alt="" src="../images/phone/imgadd.png"></li>
								<%
									}
								%>
								<%
									if (!imglist[3].equals("没有图片请上传")) {
								%>
								<li><img alt="" id="3" src="<%=imglist[3]%>"></li>
								<%
									} else {
								%>
								<li><img alt="" src="../images/phone/imgadd.png"></li>
								<%
									}
								%>
								<%
									if (!imglist[4].equals("没有图片请上传")) {
								%>
								<li><img alt="" id="4" src="<%=imglist[4]%>"></li>
								<%
									} else {
								%>
								<li><img alt="" src="../images/phone/imgadd.png"></li>
								<%
									}
								%>
								<%
									if (!imglist[5].equals("没有图片请上传")) {
								%>
								<li><img alt="" id="5" src="<%=imglist[5]%>"></li>
								<%
									} else {
								%>
								<li><img alt="" src="../images/phone/imgadd.png"></li>
								<%
									}
								%>
							</ul>
							<div style="clear: both"></div>
							<ul class="del">
								<%
									if (imglist[0].equals("没有图片请上传")) {
								%>
								<li><label>没有图片请上传!</label></li>
								<%
									} else {
								%>
								<li><a href="javascript:delImgs('<%=imglist[6]%>',0)">删除</a></li>
								<%
									}
								%>
								<%
									if (imglist[1].equals("没有图片请上传")) {
								%>
								<li><label>没有图片请上传!</label></li>
								<%
									} else {
								%>
								<li><a href="javascript:delImgs('<%=imglist[7]%>',1)">删除</a></li>
								<%
									}
								%>
								<%
									if (imglist[2].equals("没有图片请上传")) {
								%>
								<li><label>没有图片请上传!</label></li>
								<%
									} else {
								%>
								<li><a href="javascript:delImgs('<%=imglist[8]%>',2)">删除</a></li>
								<%
									}
								%>
								<%
									if (imglist[3].equals("没有图片请上传")) {
								%>
								<li><label>没有图片请上传!</label></li>
								<%
									} else {
								%>
								<li><a href="javascript:delImgs('<%=imglist[9]%>',3)">删除</a></li>
								<%
									}
								%>
								<%
									if (imglist[4].equals("没有图片请上传")) {
								%>
								<li><label>没有图片请上传!</label></li>
								<%
									} else {
								%>
								<li><a href="javascript:delImgs('<%=imglist[10]%>',4)">删除</a></li>
								<%
									}
								%>
								<%
									if (imglist[5].equals("没有图片请上传")) {
								%>
								<li><label>没有图片请上传!</label></li>
								<%
									} else {
								%>
								<li><a href="javascript:delImgs('<%=imglist[11]%>',5)">删除</a></li>
								<%
									}
								%>
							</ul>
							<div style="clear: both"></div>
						</div>
						<form action="../doImgServlet?type=imgsc" method="post"
							enctype="multipart/form-data">
							<input type="hidden" value="<%=id%>" name="gid" />
							<div class="imgupload">
								<ul class="tu">
									<li id="li0"><img alt="" src="../images/phone/imgadd.png"></li>
									<li id="li1"><img alt="" src="../images/phone/imgadd.png"></li>
									<li id="li2"><img alt="" src="../images/phone/imgadd.png"></li>
									<li id="li3"><img alt="" src="../images/phone/imgadd.png"></li>
									<li id="li4"><img alt="" src="../images/phone/imgadd.png"></li>
									<li id="li5"><img alt="" src="../images/phone/imgadd.png"></li>
								</ul>
								<div style="clear: both"></div>
								<ul class="del">
									<li><input type="file" id="myfile" name="upfile"
										onchange="preview(this,'li0')"></li>
									<li><input type="file" id="myfile" name="upfile"
										onchange="preview(this,'li1')"></li>
									<li><input type="file" id="myfile" name="upfile"
										onchange="preview(this,'li2')"></li>
									<li><input type="file" id="myfile" name="upfile"
										onchange="preview(this,'li3')"></li>
									<li><input type="file" id="myfile" name="upfile"
										onchange="preview(this,'li4')"></li>
									<li><input type="file" id="myfile" name="upfile"
										onchange="preview(this,'li5')"></li>
								</ul>
								<div style="clear: both"></div>
							</div>
							<div class="style1">
								<input type="submit" id="btn" value="提交">
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
		})
	</script>
</body>
</html>
