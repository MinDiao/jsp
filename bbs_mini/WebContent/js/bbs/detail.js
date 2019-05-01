/**
 * 详情页js
 */

// 获取要查看帖子详情的id
var msgid;
//var username;
var vm;


$(function(){
	
	// 方法三:获取url传参的id
	//var msgid2 = getUrlParam('msgid');
	//alert(msgid2);
	
	// 读取sesionstorage里帖子id的值
	msgid = sessionStorage.getItem('msgid');
	
	
	vm = new Vue({
		// 查询帖子详情的方法
		el:'#detail_data',
		data:{
			// 帖子详情
			msgVo:{},
			
			// 回帖列表(因为是List格式,所以用数组)
			replyVos:[],
			
			// 从浏览器缓存中获取用户实例
			loginUser:loginUser,
			
			// 回复内容框
			replycontents:''
			
		},
		
		methods:{
			// 查询贴子详情的方法
			query:function(){
				// ajax查询
				$.ajax({
					// 请求地址
					url:basePath + 'reply',
					
					// 请求方式
					type:'post',
					
					// 请求数据
					data:{
						msgid:msgid
					},
					
					// 响应类型
					dataType:'json',
					
					// 响应数据
					success:function(data){
						//查询帖子详情,并且包括作者信息
						vm.msgVo = data.msgVo;
						
						// 查询回帖列表
						vm.replyVos = data.replyVos;
					}
				});
			},
			
			
			// 回帖
			addDetail:function(){
				// ajax请求servlet
				$.ajax({
					// 请求地址,顺带用户的id值
					url:basePath + 'reply?action=addReply',
					
					// 请求类型
					type:'post',
					
					// 请求数据
					data:{
						// 用户id
						userid:this.loginUser.userid,
						// 贴子id
						msgid:msgid,
						// 帖子内容
						replycontents:this.replycontents
					},
					
					// 响应类型
					dataType:'json',
					
					// 响应数据
					success:function(data){
						if (data.result == 1) {
							alert("回复成功");
							// 刷新当前回帖页面
							vm.query();
							// 清空回复框
							vm.replycontents='';
						} else {
							alert("回复失败");
						}
					}
				})
			},
			
			
			// 回帖的退出登录
			loginOutDetail:function(){
				// 删除浏览器缓存中的用户信息
				sessionStorage.removeItem('loginUser');
				// 弹框
				alert("注销成功");
				// 跳转至首页
				window.location.href = basePath;
			},
			
			
			// 回帖头部论坛首页logo
			indexLogo:function(){
				// 跳转至首页
				//window.location.href = basePath;
			}
			
		}
	});
	
	// 调用查询提帖子的方法
	vm.query();
	
});


