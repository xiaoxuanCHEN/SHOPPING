/*后台评论脚本*/
//商品信息分页
function fenye1(x) {
	$(".goodinfo tr:gt(0)").html(""); //清空tr标签之后的tr标签里面的内容，防止重复
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
	//通过post异步请求获取数据(获取所有商品详情)
	$.ajax({
		url : "../doCommentServlet",
		data : {
			type : 'getcom',
			pagesize : pagesize,
			page : pages,
			uid : 0
		},
		type : 'post',
		dataType : 'html',
		success : function(data) {
			var obj = eval(data);
			var sta = "";
			for (var i = 0; i < obj.length - 1; i++) {
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
				var x1 = "<tr class=\"cominfo(" + obj[i].id + ")\"><td style=\"text-align:center;\">" + obj[i].id
					+ "</td><td style=\"text-align:center;\">" + obj[i].orderid
					+ "</td><td style=\"text-align:center;\">" + obj[i].commentTime
					+ "</td><td style=\"text-align:center;\">" + obj[i].comments
					+ "</td><td style=\"text-align:center;\">" + sta
					+ "</td><td style=\"text-align:center;\"><a class=\"a1\" href='javascript:editcom1(" + obj[i].id
					+ ")'>修改</a><a class=\"a1\" href='javascript:delcom(" + obj[i].id
					+ ")'>删除</a></td></tr>";
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
	fenye1("a");
	$(".m-modal-close").click(function() {
		var $modal = $('.m-modal');
		$modal.children('.m-modal-dialog').animate({
			"margin-top" : "-200px"
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
});

/*修改评论*/
function editcom1(cid) {
	$(".ddid1 label").html(cid);
	$(".m-modal").css("display", "block");
	$('.m-modal').children('.m-modal-dialog').animate({
		"margin-top" : "160px"
	}, 500);
	$.post("../doCommentServlet", {
		type : 'getcombycid',
		cid : cid
	},
		function(data) {
			var obj = eval(data);
			var sta = "";
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
				$(".star_l label").html(sta);
				$(".text").text(obj[i].comments);
			}
		});
}
/*过滤html代码*/
function delHtmlTag(str) {
	return str.replace(/<[^>]+>/g, "");
}

$(function() {
	$(".m-btn-sure1").click(function() {
		var comment = delHtmlTag($(".text").val());
		var cid = $(".ddid1 label").html();
		if (comment == null || comment == "") {
			alert("请输入评论内容!");
			return;
		}
		$.ajax({
			url : '../doCommentServlet',
			data : {
				type : "updatecom",
				cid : cid,
				comment : comment
			},
			type : 'post',
			dataType : 'html',
			success : function(data) {
				if (data > 0) {
					alert("评论修改成功!");
					window.location.href = "commentList.jsp";
				} else {
					alert("评论修改失败!");
				}
			},
			error : function() {
				alert(error);
			}
		});
	});
});

/*删除评论*/

function delcom(cid) {
	if (confirm("确定删除该条评论?")) {
		$.post("../doCommentServlet", {
			type : 'delcom',
			comid : cid
		},
			function(data) {
				if (data > 0) {
					alert("评论删除成功!");
					$(function() {
						$(".cominfo" + cid).remove();
					})
				} else {
					alert("评论删除失败!");
				}
			});
	}
}