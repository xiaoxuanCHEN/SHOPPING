$(function() {
	/*模态窗关闭*/
	$(".m-modal-close").click(function() {
		var $modal = $('.m-modal');
		$modal.children('.m-modal-dialog').animate({
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
});


/*状态修改框*/
function edit(oid) {
	$(".m-modal").css("display", "block");
	$('.m-modal').children('.m-modal-dialog').animate({
		"margin-top" : "160px"
	}, 500);
	$(".ddid").html(oid);



}
/*订单状态修改*/
function stastuschange() {
	var id = $(".ddid").html();
	var sta = $("select[name='edit']").val();
	if ($(".sta" + id).html() == "等待发货" && (sta == "配送中" || sta == "运送中")) {
		alert("商品未发货!");
		return;
	} else if ($(".sta" + id).html() == sta) {
		alert("商品已经是该状态!");
		return;
	} else if ($(".sta" + id).html() == "运送中" && sta == "已发货") {
		alert("商品运送中!");
		return;
	} else if ($(".sta" + id).html() == "配送中" && (sta == "已发货" || sta == "运送中")) {
		alert("商品配送中!");
		return;
	}
	$.ajax({
		url : '../doOrderServlet',
		data : {
			oid : id,
			status : sta,
			type : "edit"
		},
		type : 'post',
		dataType : 'html',
		success : function(data) {
			alert("修改成功!");
			$(".sta" + id).html(sta);
		},
		error : function(data) {
			alert("错误!");
		}
	});
}
;
