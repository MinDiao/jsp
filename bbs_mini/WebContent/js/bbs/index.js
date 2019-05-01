/**
 * 论坛首页js
 */

var vm;

//页码,并赋值初始值为第一页
var pageNum = 1;
// 页容量
var pageSize = 5;
// 一共多少页
var pages;
// 一共多少条记录
var total;

$(function(){
	console.log(typeof(loginUser)); // 缓存是Object类型,需要转换成字符串
	vm = new Vue({
		el:'#msg_data',
		data:{
			// 登录标识:定义在app.js中
			isLogin:login_flag,
			
			// 登录后显示用户名:定义在app.js中
			loginUser:loginUser,
			
			// 首页内容
			items:[],
	
			// 首页模糊查询输入框
			msgtopic:'',
			
			// 用户新增的帖子信息
			msg:{
				msgtopic:'',
				msgcontent:''
			}
		},
		
		methods:{
			// 用户退出登录
			//closeLogin:function(){
				// 清除浏览器缓存,页面改为未登录状态
				/*sessionStorage.removeItem("loginUser");
				// 方法一:局部刷新页面
				//this.isLogin = false,
				
				// 方法二:或进行再次跳转到本页面
				window.location.href = 'index.html';*/
				// 弹框
				//alert('退出成功');
			//},
			// 隔行变色方法二
			/*cal:function(index){
				return 'row'+(index%2+1);
			}*/
			
			// 发表帖子
			add:function(){
				
				$.ajax({
					// 请求地址
					url:basePath + 'index?action=add',
					// 请求类型
					type:'post',
					data:{
						// 登陆者id
						userid:this.loginUser.userid, // 已经在app.js转换成json对象
						// 主题
						msgtopic:this.msg.msgtopic,
						// 内容
						msgcontent:this.msg.msgcontent
					},
					// 响应类型
					dataType:'json',
					// 响应
					success:function(data){
						if (data.code == 1) {
							alert('发表成功');
							// 刷新首页
							window.location.href='index.html';
						} else {
							alert('发表失败');
						}
					}
				})
			},
			
			// 点击帖子列表跳转到帖子详情页
			toDetail:function(msgid){
				
				// 1.把帖子id存到sessionStorage
				sessionStorage.setItem('msgid', msgid);
				
				/*//URL传参公用方法
				function getUrlParam(name) {
					var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
					var r = window.location.search.substr(1).match(reg);
					if(r != null) {
						return unescape(r[2]);
					} else {
						return null;
					}
				}*/
				
				// 2.跳转页面
				window.location.href='detail.html';
			},
			
			
			// 按访问量排序
			visitOrder:function(){
				// ajax查询
				$.ajax({
					// 请求地址
					url:basePath + 'index?action=visitOrder',
					// 请求方式
					type:'get',
					data:{
						
					},
					// 响应类型
					dataType:'json',
					// 响应数据
					success:function(data){
						vm.items = data.code;
					}
				});
			},
			
			// 按回复量排序
			replyOrder:function(){
				// ajax查询
				$.ajax({
					// 请求地址
					url:basePath + 'index?action=replyOrder',
					// 请求方式
					type:'get',
					// 响应类型
					dataType:'json',
					// 响应数据
					success:function(data){
						vm.items = data.code;
					}
				});
			},
			
			// 按id排序(比发表日期效率高)
			idOrder:function(){
				// ajax查询
				$.ajax({
					// 请求地址
					url:basePath + 'index?action=idOrder',
					// 请求方式
					type:'get',
					// 响应类型
					dataType:'json',
					// 响应数据
					success:function(data){
						vm.items = data.code;
					}
				});
			}
			
		}
	});
	
	// 调用ajax获取首页内容
	indexCount();
	
	// 分页信息方法
	//createPage();
});


// ajax获取首页内容
function indexCount(){
	$.ajax({
		// 请求地址
		url:basePath + 'index',
		
		// 请求类型
		type:'get',
		
		data:{
			// 模糊查询主题名
			'msgtopic':vm.msgtopic,
			
			// 页码
			pageNum:pageNum,
			// 页容量
			pageSize:pageSize
		},
		
		// 响应类型
		dataType:'json',
		
		//async:false, // 是否异步,默认是true
		
		// 响应数据
		success:function(data){
			// 将装有首页内容对象的json数据赋值到Vue对象中
			vm.items = data.page.data;
			
			// 一共多少页
			pages = data.page.totalPage;
			// 记录数
			total = data.page.rows;
			// 再重新创建/刷新分页按钮
			createPage();
		}
	})
};


//创建分页信息方法
function createPage(){
	new Page({
	    id: 'pagination',
	    curPage: pageNum, //初始页码,pageNum在每次查询后都赋值
	    pageTotal: pages, //总页数
	    pageAmount: pageSize, //每页多少条
	    dataTotal: total, //总共多少条数据
	    pageSize: 5, //可选,分页个数
	    showPageTotalFlag: true, //是否显示数据统计
	    showSkipInputFlag: true, //是否支持跳转
	    getPage: function(page) {
	        //获取当前页数
	        //console.log(page);
	    	
	        // 回调
	        // 给请求的页面赋值
	    	pageNum = page;
	        // 调用查询用户的方法
	    	indexCount();
	    }
	})
}








