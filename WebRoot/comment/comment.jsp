<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>我的评论</title>
<link rel="stylesheet" type="text/css" href="../css/order.css">
<script src="../js/jquery-1.8.2.js"></script>
<script src="../js/comment.js"></script>
</head>
<body>
	<%@include file="../common/Usercheck.jsp"%>
	<%@include file="../common/header1.jsp"%>
	<%@include file="../common/navUser.jsp"%>
	<div class="comment">
		<div class="com">
			<table border="1" cellpadding="0" cellspacing="1">
				<tr class="titlecom">
					<th style="width:150px;">评论编号</th>
					<th style="width:150px;">订单编号</th>
					<th>评论时间</th>
					<th style="width:300px;">评论内容</th>
					<th>评论状态</th>
					<th>操作</th>
				</tr>
			</table>
		</div>
		<div id="content">
			<div class="pages" style="position: absolute;left:550px;top:530px">
				<a href="javascript:fenye('a')">首页</a>&nbsp;&nbsp; <a
					href="javascript:fenye('b')">上一页</a>&nbsp;&nbsp; <a
					href="javascript:fenye('c')">下一页</a>&nbsp;&nbsp; <a
					href="javascript:fenye('d')">尾页</a>&nbsp;&nbsp; <span
					id="stucurrent"></span> /<span id="totalpages"></span> <input
					type="text" style="width:30px;" size="2" name="pages"> <input
					type="button" onclick="fenye('e')" value="跳转">
			</div>
		</div>
	</div>
	<%@include file="../common/foot1.jsp"%>
	<!-- 评论修改 -->
	<div class="m-modal">
		<div class="m-modal-dialog">
			<div class="m-top">
				<h4 class="m-modal-title">评论修改</h4>
				<span class="m-modal-close">&times;</span>
			</div>
			<div class="m-middle">
				<div class="goods-comm">
					<div class="ddid1">
						<span>评论编号：</span> <label>0</label>
					</div>
					<div class="goods-comm-stars">
						<span class="star_l">满意度：<label>满意</label></span>
					</div>
				</div>
				<div class="l_text">
					<label class="m_flo" style="position: relative;top:-40px;">内
						容：</label>
					<textarea name="" id="comment" class="text"
						style="width:320px;height:50px;margin-top: 5px;"></textarea>
					<span class="tr" style="position: relative;left:250px;">字数限制为5-200个</span>
				</div>
			</div>
			<div class="m-bottom">
				<button class="m-btn-sure1">确定</button>
				<button class="m-btn-cancel">取消</button>
			</div>
		</div>
	</div>
</body>
</html>
