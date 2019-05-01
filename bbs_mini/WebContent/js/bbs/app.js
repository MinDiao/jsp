/**
 * 这是公共的js
 */

// 项目路径
var basePath = "http://127.0.0.1:8080/bbs_mini/";


//初始化判断是否登录的全局变量
//是否登录? true:登录, false:为登录
var login_flag = false;


// 登录用户的缓存
var loginUser;



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


/**
 * 判断用户是否登录的方法
 */
function isLogin(){
	
	// 获取url传参的id
	// var msgid2 = getUrlParam("msgid");
	
	// 获取用户是否登录
	// 从sessionStorage中获取loginUser
	loginUser = sessionStorage.getItem('loginUser');
	// // JSON.parse() 将json字符串转成json对象
	loginUser = JSON.parse(loginUser)
	
	// 判断是否登录成功
	if (loginUser != undefined && loginUser != null && loginUser != '') {
		// 改变为登录状态
		login_flag = true;
	} else {
		// 改变为未登录状态
		login_flag = false;
	}
}


/**
 * 退出登录
 */
function loginOut(){
	// 清除缓存
	sessionStorage.removeItem("loginUser");
	// 跳转至首页
	window.location.href = 'detail.html';
}


/**
 * 调用用户是否登录的方法
 */
$(function(){
	// 判断是否登录
	isLogin();
})


