/**
 * 登录的js
 */

var vm;

$(function(){
	vm = new Vue({
		el:'#login',
		data:{
			username:'',
			pwd:''
		},
		methods:{
			// 登录方法
			login:function(){
				$.ajax({
					// 请求地址
					url:basePath + 'login',
					// 请求类型
					type:'post',
					// 请求数据
					data:{
						username:this.username,
						pwd:this.pwd
					},
					// 响应方式
					dataType:'json',
					// 响应数据
					success:function(data){
						if (data.code == 1) {
							// 登录成功
							alert('登录成功');
							// session 保存登录信息到浏览器中
								// sessionStorage 会话级别的,缓存,浏览器关闭会清理数据
								// localStorage 本地缓存的,即使浏览器关闭数据也不会丢失
							sessionStorage.setItem('loginUser', JSON.stringify(data.loginUser)); // JSON.stringify将Object转成json字符串
							
							window.location.href = 'index.html';
						} else {
							// 登录失败
							alert('登录失败');
						}
					}
				})
			}
		}
	})
})