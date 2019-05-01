<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.js"></script>

<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		// 失去焦点向服务器发送请求
		$("#username").blur(function() {
			// 向服务器发送ajax请求
			$.ajax({
				// 请求url
				url:"${pageContext.request.contextPath}/one",
				// 请求的方式
				type:"post",
				// 发送的数据
				data:{
					"username":$("#username").val().trim()
				},
				// 服务器返回的数据类型
				dataType:"json",
				// 响应
				success:function (data) {
					if (data.flag == 1) {
						$("#errInfo").text("存在此用户");
					}
				}
			});
		});
	});
</script>
</head>
<body>
	<form id="form1" action="" method="post">
		用户名<input id="username" type="text" name="username"/>
			<span style="color: red" id="errInfo"></span><br/> 
		<input type="button" id="btn" value="注册" />
	</form>

</body>
</html>