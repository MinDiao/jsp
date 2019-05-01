/**
 * 注册页的js
 */

// 用户名重复验证定义的全局变量(是否允许提交的标识)
var flag = false;

$(function(){
	var vm = new Vue({
		// 作用区域
		el:'#register_data',
		// 数据
		data:{
			// 用户名
			username:'',
			// 密码
			password:'',
			// 性别
			sex:'男',
			// 爱好
			hobbys:[],
			// 生日
			birthday:'',
			// 地址
			city:'济宁',
			// 邮箱
			my_email:'',
			// qq
			my_qq:'',
			
			// 验证用户名重复
			username_check:''
		},
		methods:{
			// 注册提交
			register:function(){
				// 判断用户名是否重复
				if (flag) {
					
					// 异步的ajax请求
					$.ajax({
						// 请求地址
						url:basePath+'reg',
						
						// 请求方法
						type:'post',
						
						// 请求数据
						data:{
							'username':this.username,
							'password':this.password,
							'sex':this.sex,
							'hobbys':this.hobbys + '',
							'birthday':this.birthday,
							'city':this.city,
							'my_email':this.my_email,
							'my_qq':this.my_qq
						},
						
						// 响应类型
						dataType:'json',
						
						// 响应数据
						success:function(data){
							// 判断是否注册成功
							if (data.code == "1") {
								alert("注册成功");
								// 页面跳转登录页
								window.location.href = basePath + '/page/login.html';
							} else {
								alert("注册失败");
							}
						}
					})
					
				} // 判断用户名是否重复
			},
			
			// 检查用户名重复
			checkUser:function(){
				// 获取当前的用户输入的用户名
				if (this.username != null && this.username != '') {
					$.ajax({
						// 请求地址
						url:basePath + 'reg?action=checkUser',
						// 请求方式
						type:'post',
						// 请求数据
						data:{
							'username':this.username
						},
						// 响应方式
						dataType:'json',
						// 响应数据
						success:function(data){
							if (data.code == 1) {
								// 代表用户名重复
								flag = false;
								// 提示重复
								vm.username_check = '用户名重复';
							} else {
								// 代表用户名不重复
								flag = true;  
								// 提示可用
								vm.username_check = '用户名可用';
							}
						}
					})
				}
			}
		}
	})
});
