<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>购物车的实现</title>
<link rel="stylesheet" type="text/css" href="bootstrap.min.css">
<script src="../js/jquery-1.8.2.js" type="text/javascript"
	charset="utf-8"></script>
<script src="index.js" type="text/javascript" charset="utf-8"></script>
<script src="angular.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body ng-app>

	<div
		style="margin-left:auto; margin-right:auto; width:600px; padding-top:30px">
		<input type="text" name="" value="" ng-model="abc"
			placeholder="输入关键字快速查找">{{abc}}
	</div>

	<div ng-controller="cartController" class="container">
		<table class="table" ng-show="cart.length">
			<thead>
				<tr>
					<th>产品编号</th>
					<th>产品名字</th>
					<th>购买数量</th>
					<th>产品单价</th>
					<th>产品总价</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="item in cart| filter:abc">
					<td ng-bind="item.id"></td>
					<td ng-bind="item.name"></td>
					<td>
						<button type="button" ng-click="reduce(item.id)"
							class="btn btn-primary btn-sm">-</button> <input type="text"
						ng-model="item.quantity" style="text-align: center;">
						<button type="button" ng-click="add(item.id)"
							class="btn btn-primary btn-sm">+</button>
					</td>
					<td ng-bind="item.price"></td>
					<td ng-bind="item.price*item.quantity"></td>
					<td><button type="button" ng-click="remove(item.id)"
							class="btn btn-danger btn-sm">移除</button></td>
				</tr>
				<tr>
					<td>总购物价</td>
					<td ng-bind="totalPrice()"></td>
					<td>总购买数量</td>
					<td ng-bind="totalQuantity()"></td>
					<td colspan="2"><button type="button" ng-click="cart = {}"
							class="btn btn-danger btn-sm">清空购物车</button></td>
				</tr>
			</tbody>
		</table>
		<p ng-show="!cart.length">您的购物车已空</p>
	</div>
</body>
</html>