jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

$(function() {
	$("form").validate(
		{
			rules : {
				realname : {
					required : true
				},
				phone : {
					required : true,
					number : true,
					isMobile:true,
					//rangelength : [ 7, 11 ],
					//远程验证
					remote : {
						type : "POST",
						url : "checkPhoneServlet",
						data : {
							phone : function() {
								return $("input[name='phone']").val();
							}
						}
					}
				},
				pwd : {
					required : true,
					rangelength : [ 6, 18 ]
				},
				realpwd : {
					required : true,
					rangelength : [ 6, 18 ],
					equalTo : "#pwd"
				},
				transpwd : {
					required : true,
					number : true,
					rangelength : [ 6, 6 ]
				},
				address : {
					required : true,
					maxlength : 20
				}
			},
			messages : {
				realname : {
					required : "请输入真实姓名!"
				},
				phone : {
					required : "请输入手机号!",
					isisMobile:"请正确填写您的手机号码!",
					remote : "该手机号已经注册!"
				},
				pwd : {
					required : "请输入密码!",
					rangelength : "密码长度为6-18位!"
				},
				realpwd : {
					required : "请重复输入密码!",
					rangelength : "密码长度为6-18位!",
					equalTo : "重复密码与密码不一致!"
				},
				transpwd : {
					required : "请输入交易密码!",
					number : "交易密码只能为数字!",
					rangelength : "交易密码长度只能为6位!"
				},
				address : {
					required : "请输入地址!",
					maxlength : "地址最大长度为20位!"
				}
			}
		}
	);
});