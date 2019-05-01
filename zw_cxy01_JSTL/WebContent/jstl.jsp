<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- c:out 输出信息到页面 -->
	<%
		request.setAttribute("name", "zhangsan");
	%>
	<c:out value="${name }" default="aaa"></c:out>
	
	<!-- c:set设置值
		 scope:可选四大域对象 page、request、session、application 默认为page 此属性用来限制作用域
	 -->
	<c:set var="sex" value="男" scope="request"></c:set>
	
	<!-- 取值 -->
	<c:out value="${requestScope.sex }"></c:out>
	
	<!-- 判断 -->
	<c:if test="${param.age < 18 }">
		<span style="color: red;">未成年</span>
	</c:if>
	<c:if test="${param.age >= 18 and param.age < 40 }"><!-- and功能与&&相同 -->
		<span style="color: blue;">青年</span>
	</c:if>
	
	<hr>
	<!-- c:if elseif else  cwo -->
	<c:choose>
		<c:when test="${param.age < 18 }">
			<span>少年</span>
		</c:when>
		<c:when test="${param.age <40 }">
			<span>青年</span>
		</c:when>
		<c:otherwise>
			<span>中老年</span>
		</c:otherwise>
	</c:choose>
	<hr>
	
	<!-- c:foreach items:遍历的对象 var:每一次要遍历的元素 -->
	<c:forEach items="${array }" var="str">
		${str }
	</c:forEach><br>
	
	<c:forEach items="${list }" var="str">
		${str }
	</c:forEach><br>
	
	<c:forEach items="${users }" var="user">
		${user.name }:${user.age }
	</c:forEach><br>
	
	<c:forEach items="${map }" var="entry">
		${entry.key }:${entry.value.name }
	</c:forEach><br>
	
	<!-- 遍历0-10之内的数字 -->
	<c:forEach var="i" begin="0" end="10" step="2"> <!-- 默认step步长为1 -->
		${i }
	</c:forEach><br>
	
	<c:forEach items="${users }" var="user" varStatus="status">
		<!-- count代表第几个,index代表下标 -->
		${user.name }:${status.index }:${status.count }
	</c:forEach>

</body>
</html>