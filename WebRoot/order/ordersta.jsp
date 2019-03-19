<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理者后台</title>
</head>

<body>
	<div class="page-container">
		<div id="container" style="width:1080px;height:300px"></div>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="../js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="../js/huitu/layer.js"></script>
	<script type="text/javascript" src="../js/huitu/H-ui.min.js"></script>
	<script type="text/javascript" src="../js/huitu/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript" src="../js/huitu/highcharts.js"></script>
	<script type="text/javascript" src="../js/huitu/exporting.js"></script>
	<script type="text/javascript">
		$(function() {
			$.post("../doOrderServlet", {
				type : 'tj'
			}, function(data) {
				var obj = eval(data);
				var x1 = "[";
				var y1 = "[{name:'2017年',data:[";
				$.each(obj, function(index, item) {
					x1 += "'" + item.time.substr(5, 5) + "',";
					y1 += item.zje + ',';
				});
				x1 = x1.substr(0, x1.length - 1) + "]";
				y1 = y1.substr(0, y1.length - 1) + "]}]";
				$("#x").append(y1);
	
	
				Highcharts.chart('container', {
					title : {
						text : '每天的销售金额',
						x : -20 //center
					},
					subtitle : {
						text : 'Source: 来源数据库northwind',
						x : -20
					},
					xAxis : {
						categories : eval(x1)
					},
					yAxis : {
						title : {
							text : '总金额 (元)'
						},
						plotLines : [ {
							value : 0,
							width : 2,
							color : '#808080'
						} ]
					},
					tooltip : {
						valueSuffix : '元'
					},
					legend : {
						layout : 'vertical',
						align : 'right',
						verticalAlign : 'middle',
						borderWidth : 0
					},
					series : eval(y1)
				});
			});
		});
	</script>
</body>
</html>