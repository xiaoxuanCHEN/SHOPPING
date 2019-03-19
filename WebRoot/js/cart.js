$(
	function() {
		jisuan();
	}
);

//全选按钮
function quanxuan(obj) {
	var array = $(".check");
	for (var i = 0; i < array.length; i++)
		array[i].checked = obj.checked
}
//计算数量和价格
function jisuan() {
	var array = $(".check");
	var total = 0;
	var s = 0;
	for (var i = 0; i < array.length; i++) {
		if (array[i].checked) {
			var price = $("#price" + array[i].value).text().substring(1);
			var count = $("#txtcount" + array[i].value).val();
			s = s + parseInt(count);
			var sum = parseFloat(price) * count;
			total += sum;
			$("#xiaoji" + array[i].value).html("&yen;" + sum);
		}
	}
	$("#count").html(s);
	$("#totalmoney").html("&yen;" + total);
}


//按钮控制商品数量
function calculate(fuhao, max, id) {
	var obj = document.getElementById(id);
	var x = 0;
	if (isNaN(obj.value)) {
		x = 0;
	} else {
		x = parseInt(obj.value);
	}
	if (fuhao == "+" && x < max) {
		++x;
	} else if (fuhao == "-" && x > 1) {
		--x;
	}
	obj.value = x;jisuan();
}

/*移除商品*/
function del(gid) {
	if (confirm("确定删除该商品?")) {
		$.ajax({
			url : "doMyCartServlet",
			data : {
				gid : gid,
				type : "delCartBygid"
			},
			type : 'POST',
			datatype : 'html',
			success : function(data) {
				if (data > 0) {
					alert("删除成功!");
					$("#good" + gid).remove();
					$(function() {
						jisuan();
					});
				} else {
					alert("删除失败!");
				}
			},
			error : function(data) {
				alert(error);
			}
		}
		);
	}
}
/*移除购物车中所有的信息*/
function delCart() {
	if (confirm("确定移除购物车中所有商品?")) {
	$.ajax({
		url : "doMyCartServlet",
		data : {
			type : "delCartByuid"
		},
		type : 'POST',
		datatype : 'html',
		success : function(data) {
			if (data > 0) {
				alert("删除成功!");
				$(".cartinfo").remove();
				$("#count").html(0);
				$("#totalmoney").html("&yen;" + 0);
				$("input[type='submit']").attr("disabled","disabled");
				$("button").attr("disabled","disabled");
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
/*购物车中商品数量修改*/
function editCount(gid, cid) {
	var count = $("#txtcount" + cid).val();
	$.post("doMyCartServlet", {
		type : 'updateCart',
		gid : gid,
		cid : cid,
		count : count
	}, function(date) {}
	)
}

//function add(x) {
//	var i = $("#txtcount1"+x).val();
//	var num = parseInt(i) + 1;
//	if (num == 0) {
//		return;
//	}
//	$("#txtcount1"+x).val(num);
//	var price = $("#price"+x).text().substring(1);
//	var xiaoji = $("#xiaoji"+x).text().substring(1);
//	$("#xiaoji"+x).text("￥" + price * num);
//	jisuan();
//}
//
//function jian(x) {
//	var i = $("#txtcount1").val();
//	var num = parseInt(i) -1;
//	if (num == 0) {
//		return;
//	}
//	$("#txtcount1").val(num);
//	var price = $("#price"+x).text().substring(1);
//	var xiaoji = $("#xiaoji"+x).text().substring(1);
//	$("#xiaoji"+x).text("￥" + price * num);
//	jisuan();
//}