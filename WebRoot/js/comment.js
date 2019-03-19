/*页面分页，修改评论*/

//商品信息分页
function fenye(x) {
	$(".com tr:gt(0)").html(""); //清空tr标签之后的tr标签里面的内容，防止重复
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
	//通过post异步请求获取数据(获取所有商品评论详情)
	$.ajax({
		url : "doCommentServlet",
		data : {
			type : 'getcom',
			pagesize : pagesize,
			page : pages,
			uid : 1
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
				var x1 = "<tr class=\"info\"><td style=\"text-align:center;\">" + obj[i].id
					+ "</td><td style=\"text-align:center;\">" + obj[i].orderid
					+ "</td><td style=\"text-align:center;\">" + obj[i].commentTime
					+ "</td><td style=\"text-align:center;\">" + obj[i].comments
					+ "</td><td style=\"text-align:center;\">" + sta
					+ "</td><td style=\"text-align:center;\"><a class=\"a1\" href='javascript:editcom(" + obj[i].id
					+ ")'>修改</a><a class=\"a1\" href='javascript:delcom(" + obj[i].id
					+ ")'>删除</a></td></tr>";
				$(".com>table").append(x1);
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

function editcom(cid) {
	$(".ddid1 label").html(cid);
	$(".m-modal").css("display", "block");
	$('.m-modal').children('.m-modal-dialog').animate({
		"margin-top" : "160px"
	}, 500);
	$.post("doCommentServlet", {
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

/*修改评论内容*/
$(function() {
	$(".m-btn-sure1").click(function() {
		var comment = $(".text").val();
		var cid = $(".ddid1 label").html();
		if (comment == null || comment == "") {
			alert("请输入评论内容!");
			return;
		}
		$.ajax({
			url : 'doCommentServlet',
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
					window.location.href = "comment/comment.jsp";
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
		$.post("doCommentServlet", {
			type : 'delcom',
			comid : cid
		},
			function(data) {
				if (data > 0) {
					alert("评论删除成功!");
					$(function() {
						$(".info"+cid).remove();
					})
				} else {
					alert("评论删除失败!");
				}
			});
	}
}








// star choose
jQuery.fn.rater = function(options) {

	// 默认参数
	var settings = {
		enabled : true,
		url : '',
		method : 'post',
		min : 1,
		max : 5,
		step : 1,
		value : null,
		after_click : null,
		before_ajax : null,
		after_ajax : null,
		title_format : null,
		info_format : null,
		image : 'images/comment/stars.jpg',
		imageAll : 'images/comment/stars-all.gif',
		defaultTips : true,
		clickTips : true,
		width : 24,
		height : 24
	};

	// 自定义参数
	if (options) {
		jQuery.extend(settings, options);
	}

	//外容器
	var container = jQuery(this);

	// 主容器
	var content = jQuery('<ul class="rater-star"></ul>');
	content.css('background-image', 'url(' + settings.image + ')');
	content.css('height', settings.height);
	content.css('width', (settings.width * settings.step) * (settings.max - settings.min + settings.step) / settings.step);
	//显示结果区域
	var result = jQuery('<div class="rater-star-result"></div>');
	container.after(result);
	//显示点击提示
	var clickTips = jQuery('<div class="rater-click-tips"><span>点击星星就可以评分了</span></div>');
	if (!settings.clickTips) {
		clickTips.hide();
	}
	container.after(clickTips);
	//默认手形提示
	var tipsItem = jQuery('<li class="rater-star-item-tips"></li>');
	tipsItem.css('width', (settings.width * settings.step) * (settings.max - settings.min + settings.step) / settings.step);
	tipsItem.css('z-index', settings.max / settings.step + 2);
	if (!settings.defaultTips) { //隐藏默认的提示
		tipsItem.hide();
	}
	content.append(tipsItem);
	// 当前选中的
	var item = jQuery('<li class="rater-star-item-current"></li>');
	item.css('background-image', 'url(' + settings.image + ')');
	item.css('height', settings.height);
	item.css('width', 0);
	item.css('z-index', settings.max / settings.step + 1);
	if (settings.value) {
		item.css('width', ((settings.value - settings.min) / settings.step + 1) * settings.step * settings.width);
	}
	;
	content.append(item);


	// 星星
	for (var value = settings.min; value <= settings.max; value += settings.step) {
		item = jQuery('<li class="rater-star-item"><div class="popinfo"></div></li>');
		if (typeof settings.info_format == 'function') {
			//item.attr('title' , settings.title_format(value));
			item.find(".popinfo").html(settings.info_format(value));
			item.find(".popinfo").css("left", (value - 1) * settings.width)
		} else {
			item.attr('title', value);
		}
		item.css('height', settings.height);
		item.css('width', (value - settings.min + settings.step) * settings.width);
		item.css('z-index', (settings.max - value) / settings.step + 1);
		item.css('background-image', 'url(' + settings.image + ')');

		if (!settings.enabled) { // 若是不能更改，则隐藏
			item.hide();
		}

		content.append(item);
	}

	content.mouseover(function() {
		if (settings.enabled) {
			jQuery(this).find('.rater-star-item-current').hide();
		}
	}).mouseout(function() {
		jQuery(this).find('.rater-star-item-current').show();
	})
	// 添加鼠标悬停/点击事件
	var shappyWidth = (settings.max - 2) * settings.width;
	var happyWidth = (settings.max - 1) * settings.width;
	var fullWidth = settings.max * settings.width;
	content.find('.rater-star-item').mouseover(function() {
		jQuery(this).prevAll('.rater-star-item-tips').hide();
		jQuery(this).attr('class', 'rater-star-item-hover');
		jQuery(this).find(".popinfo").show();

		//当3分时用笑脸表示
		if (parseInt(jQuery(this).css("width")) == shappyWidth) {
			jQuery(this).addClass('rater-star-happy');
		}
		//当4分时用笑脸表示
		if (parseInt(jQuery(this).css("width")) == happyWidth) {
			jQuery(this).addClass('rater-star-happy');
		}
		//当5分时用笑脸表示
		if (parseInt(jQuery(this).css("width")) == fullWidth) {
			jQuery(this).removeClass('rater-star-item-hover');
			jQuery(this).css('background-image', 'url(' + settings.imageAll + ')');
			jQuery(this).css({
				cursor : 'pointer',
				position : 'absolute',
				left : '0',
				top : '0'
			});
		}
	}).mouseout(function() {
		var outObj = jQuery(this);
		outObj.css('background-image', 'url(' + settings.image + ')');
		outObj.attr('class', 'rater-star-item');
		outObj.find(".popinfo").hide();
		outObj.removeClass('rater-star-happy');
		jQuery(this).prevAll('.rater-star-item-tips').show();
	//var startTip=function () {
	//outObj.prevAll('.rater-star-item-tips').show();
	//};
	//startTip();
	}).click(function() {
		//jQuery(this).prevAll('.rater-star-item-tips').css('display','none');
		jQuery(this).parents(".rater-star").find(".rater-star-item-tips").remove();
		jQuery(this).parents(".goods-comm-stars").find(".rater-click-tips").remove();
		jQuery(this).prevAll('.rater-star-item-current').css('width', jQuery(this).width());
		if (parseInt(jQuery(this).prevAll('.rater-star-item-current').css("width")) == happyWidth || parseInt(jQuery(this).prevAll('.rater-star-item-current').css("width")) == shappyWidth) {
			jQuery(this).prevAll('.rater-star-item-current').addClass('rater-star-happy');
		} else {
			jQuery(this).prevAll('.rater-star-item-current').removeClass('rater-star-happy');
		}
		if (parseInt(jQuery(this).prevAll('.rater-star-item-current').css("width")) == fullWidth) {
			jQuery(this).prevAll('.rater-star-item-current').addClass('rater-star-full');
		} else {
			jQuery(this).prevAll('.rater-star-item-current').removeClass('rater-star-full');
		}
		var star_count = (settings.max - settings.min) + settings.step;
		var current_number = jQuery(this).prevAll('.rater-star-item').size() + 1;
		var current_value = settings.min + (current_number - 1) * settings.step;

		//显示当前分值
		if (typeof settings.title_format == 'function') {
			jQuery(this).parents().nextAll('.rater-star-result').html(current_value + '分&nbsp;' + settings.title_format(current_value));
		}
		$("#StarNum").val(current_value);
	//jQuery(this).parents().next('.rater-star-result').html(current_value);
	//jQuery(this).unbind('mouseout',startTip)
	})

	jQuery(this).html(content);

}

// 星星打分
$(function() {
	var options = {
		max : 5,
		title_format : function(value) {
			var title = '';
			switch (value) {
			case 1:
				title = '很不满意';
				break;
			case 2:
				title = '不满意';
				break;
			case 3:
				title = '一般';
				break;
			case 4:
				title = '满意';
				break;
			case 5:
				title = '非常满意';
				break;
			default:
				title = value;
				break;
			}
			return title;
			return status;
		},
		info_format : function(value) {
			var info = '';
			switch (value) {
			case 1:
				info = '<div class="info-box">1分&nbsp;很不满意<div>商品样式和质量都非常差，太令人失望了！</div></div>';
				break;
			case 2:
				info = '<div class="info-box">2分&nbsp;不满意<div>商品样式和质量不好，不能满足要求。</div></div>';
				break;
			case 3:
				info = '<div class="info-box">3分&nbsp;一般<div>商品样式和质量感觉一般。</div></div>';
				break;
			case 4:
				info = '<div class="info-box">4分&nbsp;满意<div>商品样式和质量都比较满意，符合我的期望。</div></div>';
				break;
			case 5:
				info = '<div class="info-box">5分&nbsp;非常满意<div>我很喜欢！商品样式和质量都很满意，太棒了！</div></div>';
				break;
			default:
				info = value;
				break;
			}
			return info;
		}
	}
	$('#rate-comm-1').rater(options);
});