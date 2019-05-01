<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	welcome
	<%
		pageContext.setAttribute("name", "pageContext"); // 作用于当前文件上下文
		request.setAttribute("name", "request"); 
		session.setAttribute("name", "session"); 
		application.setAttribute("name", "application"); 
	%>
	
	<!-- 方式1 out -->
	<%out.println(request.getAttribute("name")); %>
	
	<!-- 方式1 表达式,脚本片段 -->
	<%=request.getAttribute("name") %><br>
	
	<!-- 方式3 el表达式 -->
	${name }<br>
	
	<!-- 获取session相应的值 -->
	${sessionScope.name }
	
	<!-- 获取request相应值 -->
	${requestScope.name }
	
	<!-- el中一个页面的上下文对象(与jsp中的不一样) 获取绝对路径(获取绝对路径会解决页面\样式无法加载的情况,绝对不会出错 -->
	<a href="${pageContext.request.contextPath }/one">oneServlet</a>
	
	<hr>
	
	<!-- 数组 -->
	${array[0] }<br>
	
	<!-- 集合 -->
	${list[1] }<br>
	
	<!-- 对象属性 -->
	${user.name }<br>
	
	<!-- 泛型List集合 -->
	${users[0].name }<br>
	
	<!-- 泛型Map集合 -->
	${map.one.name }<br>
	
	<!-- 运算符 -->
	${1 == 1 }<br>
	${1 eq 1 }<br>
	${empty " " }<br> <!-- 返回false --> 	<!-- js中有trim方法 -->
	${empty user } <!-- 虽然没有数据,但有实例,也会为false -->
	
</body>
</html>