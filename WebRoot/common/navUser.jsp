<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'navUser.jsp' starting page</title>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
	list-style: none;
	text-decoration: none;
}

.nav1 {
	width: 1080px;
	margin: 0 auto;
	height: 30px;
	background-color: #e0e0e0;
	/*box-shadow: 0px 5px 3px 3px #808080;*/
	margin-top: 40px;
}

.dh ul li {
	float: left;
	width: 100px;
	text-align: center;
	line-height: 30px;
}

.dh ul li a {
	color: #3c3c3c;
}

.dh ul li a:HOVER {
	color: #fff;
}

.dh ul li:HOVER {
	background-color: #bebebe;
}
</style>
</head>

<body>
	<div class="nav1">
		<div class="dh">
			<ul>
				<li><a href="index.jsp">首页</a></li>
				<li><a href="cart/myCart.jsp">我的购物车</a></li>
				<li><a href="order/order.jsp">我的订单</a></li>
				<li><a href="comment/comment.jsp">我的评论</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
