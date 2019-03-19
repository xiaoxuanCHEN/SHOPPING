
/*评论信息*/

$(function() {
	var goodid = $("#goodid").val();
	$.ajax({
		url : "doCommentServlet",
		data : {
			type : "getcomment",
			gid : goodid
		},
		type : "POST",
		datatype : "html",
		success : function(data) {
			if (data.length > 2) {
				var obj = eval(data);
				for (var i = 0; i < obj.length; i++) {
					if (obj[i].commentStatus == 1) {
						sta = "很不满意";
					} else if (obj[i].commentStatus == 2) {
						sta = "不满意";
					} else if (obj[i].commentStatus == 3) {
						sta = "一般";
					} else if (obj[i].commentStatus == 4) {
						sta = "满意";
					} else if (obj[i].commentStatus == 5) {
						sta = "非常满意";
					}
					var x = "<div>"
						+ "<div style=\"width:980px;margin: 0px auto;font-weight:bolder;\">"
						+ "用户编号：<label style=\"color:#adadad;\">" + obj[i].uid + "</label>&nbsp;&nbsp;&nbsp;&nbsp;<br>"
						+ "评论状态：<label style=\"color:#ff2d2d;\">" + sta + "</label><br>"
						+ "评论时间：<label style=\"color:#adadad;\">" + obj[i].commentTime + "</label><br>"
						+ "<hr align=\"left\" style=\"width:220px;\"/>"
						+ "</div>"
						+ "<div style=\"width:980px;margin: 20px auto;font-weight:bolder;\">"
						+ "评论内容：<p style=\"width:200px;color:#ff5809;\">" + obj[i].comments + "</p>"
						+ "</div>"
						+ "<div style=\"border:1px dotted #000; width:980px;margin: 0px auto;margin-bottom: 20px;\"></div>"
						+ "</div>";
					$(".commentinfo").append(x);
				}
			} else {
				var x1 = "<div style=\"width:980px;font-size:30px;color:red;margin: 20px auto;font-weight:bolder;\">暂无评论!</div>";
				$(".commentinfo").append(x1);
			}
		},
		error : function(data) {
			alert("error");
		}
	});
});
/*ajax数据查询和修改js*/
$(function() {
	$(".addcart").click(function() {
		var goodid = $("#goodid").val();
		var count = $("input[name='count']").val();
		if (!isNaN(count) && count > 0) {
			$.ajax({
				url : "doMyCartServlet",
				data : {
					goodid : goodid,
					count : count,
					type : "addCart"
				},
				type : "POST",
				datatype : "html",
				success : function(data) {
					if (data > 0) {
						alert("加入购物车成功!");
					} else {
						alert("加入购物车失败!");
					}
				},
				error : function(data) {
					alert(error);
				}
			});
		} else {
			alert("商品数量有误!");
		}
	});

	$(".buyshop").click(function() {
		var goodid = $("#goodid").val();
		var count = $("input[name='count']").val();
		$.ajax({
			url : "doOrderServlet",
			data : {
				gid : goodid,
				count : count,
				type : "zjbuy"
			},
			type : "POST",
			datatype : "html",
			success : function(data) {
				if (data > 0) {
					window.location.href = "order/order.jsp";
				} else {
					alert(data);
				}
			},
			error : function(data) {
				alert("error");
			}
		});
	});
});













/*页面特效js*/
$(document).ready(function() {
	var $miaobian = $('.Xcontent08>div');
	var $huantu = $('.Xcontent06>img');
	var $miaobian1 = $('.Xcontent26>div');
	$miaobian.mousemove(function() {
		miaobian(this);
	});
	$miaobian1.click(function() {
		miaobian1(this);
	});
	function miaobian(thisMb) {
		for (var i = 0; i < $miaobian.length; i++) {
			$miaobian[i].style.borderColor = '#dedede';
		}
		thisMb.style.borderColor = '#cd2426';

		$huantu[0].src = thisMb.children[0].src;
	}
	function miaobian1(thisMb1) {
		for (var i = 0; i < $miaobian1.length; i++) {
			$miaobian1[i].style.borderColor = '#dedede';
		}
		//		thisMb.style.borderColor = '#cd2426';
		$miaobian.css('border-color', '#dedede');
		thisMb1.style.borderColor = '#cd2426';
		$huantu[0].src = thisMb1.children[0].src;
	}
	$(".Xcontent33").click(function() {
		var value = parseInt($('.input').val()) + 1;
		$('.input').val(value);
		var num = $(".input").val();
		if (num > 0) {
			$(".Xcontent32 img").attr("src", "images/shoppingdetail/X15.1.png");
		}
	})

	$(".Xcontent32").click(function() {
		var num = $(".input").val();
		if (num > 0) {
			$(".input").val(num - 1);
		} else {
			$(".Xcontent32 img").attr("src", "images/shoppingdetail/X15.png");
		}
	})
	if ($(".input").val() > 0) {
		$(".Xcontent32 img").attr("src", "images/shoppingdetail/X15.1.png");
	}
});