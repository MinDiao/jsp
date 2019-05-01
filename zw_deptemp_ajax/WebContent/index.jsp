<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html lang="zh">
<head>
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>员工管理系统</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<script>
</script>
<body>
    <header>
        <h1>员工管理系统</h1>
    </header>
    <div class="admin">
        <iframe src="./default.jsp" frameborder="0" name="top" width="100%" height="400"></iframe>
    </div>
    <div class="btn-bottom">
    	<a class="btn btn-left" href="empList.jsp" target="top">员工列表</a>
        <a class="btn btn-right" href="emp?action=empServletById" target="top">添加员工</a>
    </div>
    <footer>
        <p>&copy;</p>
    </footer>
</body>
</html>