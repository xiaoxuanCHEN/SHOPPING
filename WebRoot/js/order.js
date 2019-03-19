$(function() {
	/*模态窗关闭*/
	$(".m-modal-close").click(function() {
		var $modal = $('.m-modal');
		$modal.children('.m-modal-dialog').animate({
			"margin-top" : "-200px"
		}, 800);
		$modal.fadeOut(500);
	});
	$(".m-modal-close1").click(function() {
		var $modal = $('.m-modal1');
		$modal.children('.m-modal-dialog1').animate({
			"margin-top" : "-160px"
		}, 800);
		$modal.fadeOut(500);
	});
	$(".m-modal-close2").click(function() {
		var $modal = $('.m-modal2');
		$modal.children('.m-modal-dialog2').animate({
			"margin-top" : "-160px"
		}, 800);
		$modal.fadeOut(500);
	});
	$(".m-modal-close3").click(function() {
		var $modal = $('.m-modal3');
		$modal.children('.m-modal-dialog3').animate({
			"margin-top" : "-160px"
		}, 800);
		$modal.fadeOut(500);
	});
	$(".m-btn-cancel").click(function() {
		var $modal = $('.m-modal');
		$modal.children('.m-modal-dialog').animate({
			"margin-top" : "-200px"
		}, 800);
		$modal.fadeOut(500);
	});
	$(".m-btn-cancel2").click(function() {
		var $modal = $('.m-modal2');
		$modal.children('.m-modal-dialog2').animate({
			"margin-top" : "-200px"
		}, 800);
		$modal.fadeOut(500);
	});
});
/*支付窗*/
function pay(money, oid) {
	$(".m-modal").css("display", "block");
	$('.m-modal').children('.m-modal-dialog').animate({
		"margin-top" : "200px"
	}, 500);
	$("input[name='money']").val(money);
	$("input[type='hidden']").val(oid);
}
/*详情窗*/
function xq(oid) {
	$(".m-modal1").css("display", "block");
	$('.m-modal1').children('.m-modal-dialog1').animate({
		"margin-top" : "160px"
	}, 500);
	$.post(
		"doOrderDetailServlet",
		{
			oid : oid,
			type : "xq"
		},
		function(data) {
			$(".xqinfo").html(data);
		}
	);
}

/*进行pl*/
function pj(oid) {
	$(".ddid1 label").html(oid);
	$(".m-modal2").css("display", "block");
	$('.m-modal2').children('.m-modal-dialog2').animate({
		"margin-top" : "160px"
	}, 500);
}

function tjpj() {
	var sta = $(".rater-star-result").html();
	var comment = $("#comment").val();
	var oid = $(".ddid1 label").html();
	var status = 5;
	if (sta == "1分&nbsp;很不满意") {
		status = 1;
	} else if (sta == "2分&nbsp;不满意") {
		status = 2;
	} else if (sta == "3分&nbsp;一般") {
		status = 3;
	} else if (sta == "4分&nbsp;满意") {
		status = 4;
	} else if (sta == "5分&nbsp;非常满意") {
		status = 5;
	} else {
		alert("请给商品打分!");
		return;
	}
	$.post(
		"doCommentServlet",
		{
			oid : oid,
			comment : comment,
			status : status,
			ostatus : "已评价",
			type : "addcom"
		},
		function(data) {
			if (data > 0) {
				alert("评论成功!");
				window.location.href = "order/order.jsp";
			} else {
				alert("评论失败!");
			}
		}
	);
}



/*删除订单*/
function del(oid) {
	if (confirm("确定删除该订单?")) {
		$.post("doOrderServlet",
			{
				oid : oid,
				type : "del"
			},
			function(data) {
				if (data > 0) {
					alert("订单删除成功!");
					$(function() {
						$("#info" + oid).remove();
					});
				} else {
					alert("该订单正在处理!");
				}
			}
		);
	}
}

/*支付ajax*/
$(function() {
	$(".m-btn-sure").click(function() {
		var transpwd = $("input[name='pwd1']").val();
		var oid = $("input[type='hidden']").val();
		if (transpwd == null || transpwd == "") {
			alert("请输入支付密码!");
			return;
		}
		$.ajax({
			url : 'doOrderServlet',
			data : {
				oid : oid,
				transpwd : transpwd,
				type : "pay"
			},
			type : 'post',
			dataType : 'html',
			success : function(data) {
				alert(data);
				window.location.href = "order/order.jsp";
			},
			error : function() {
				alert(error);
			}
		});
	});
});

function shouhuo(oid) {
	if (confirm("您确定要收货吗?")) {
		$.ajax({
			url : 'doOrderServlet',
			data : {
				oid : oid,
				type : "edit",
				status : "已收货"
			},
			type : 'post',
			dataType : 'html',
			success : function(data) {
				alert("收货成功!");
				window.location.href = "order/order.jsp";
			},
			error : function(data) {
				alert(error);
			}
		});
	}
}

function showpj(oid) {
	$(".plinfo").html("");
	$(".plxq").html(oid);
	$(".m-modal3").css("display", "block");
	$('.m-modal3').children('.m-modal-dialog3').animate({
		"margin-top" : "160px"
	}, 500);
	/*$.post(
		"doCommentServlet",
		{
			oid : oid,
			type : "showcom"
		},
		function(data) {
			$(".plinfo").append(data);
		}
	);*/



	$.ajax({
		url : 'doCommentServlet',
		data : {
			oid : oid,
			type : "showcom"
		},
		type : 'post',
		dataType : 'html',
		success : function(data) {
			$(".plinfo").append(data);
		},
		error : function(data) {
			alert(error);
		}
	});
}