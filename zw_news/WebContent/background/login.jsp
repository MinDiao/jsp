<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//web应用路径
	String path = request.getContextPath();
	//请求协议://服务名称:端口号/web应用路径
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<!-- basePath默认的超链接都是它 -->
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="background/resources/css/pintuer.css">
<link rel="stylesheet" href="background/resources/css/admin.css">
<script type="text/javascript" src="background/resources/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="background/resources/scripts/pintuer.js"></script>
<title>新闻发布系统后台</title>
<script type="text/javascript">
	
	// 实现表单验证
	function checkForm(){
		// 非空验证
		var userName = $("input[name = 'userName']").val(); // 获取input中的值的val()方法
		var password = $("input[name = 'password']").val();
		if (userName == "" || password == "") {
			return false;
		} else {
			return true; // 不为空时提交表单
		}
	}
</script>
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <!-- 旧方法 -->
            <!-- <form action="${pageContext.request.contextPath }/user" method="post"> -->
            
            <!-- 新方法 -->
            <form action="user?action=login" method="post" onsubmit="return checkForm();">
            
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>闪电新闻系统登录</h1></div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big" name="userName" placeholder="登录账号"  data-validate="required:请输入账号" />
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" class="input input-big" name="password" placeholder="登录密码" data-validate="required:请填写密码" />
                            <span class="icon icon-key margin-small"></span>
                           	 
                           	 ${error }
                        </div>
                    </div>
                </div>
                <div style="padding:0px 30px 30px 30px;"><input type="submit" class="button button-block bg-main text-big input-big" value="登录"></div>
            </div>
            </form>          
        </div>
    </div>
</div>
</body>
</html>