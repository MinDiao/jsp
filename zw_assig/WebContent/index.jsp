<%@page import="java.util.UUID" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String uuid = UUID.randomUUID().toString().replace("-","");
		request.getSession().setAttribute("token",uuid);
	%>
	<form action="login" method="post">
		<input type="hidden" value="<%=uuid %>" name="token">
		用户:<input type="text" name="username" id="username"><br>
		密码:<input type="password" name="password" id="password"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>