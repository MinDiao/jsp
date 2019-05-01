<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html >
<html>
<head>
<base href="<%= basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style/news.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="bootstrap/js/jQuery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	function newsTypeDelete(newsTypeId){
		if (confirm("您确定要删除此新闻类别吗?")) {
			window.location.href="newsType?action=delete&newsTypeId="+newsTypeId;
		}
	}
</script>
</head>
<body>
<div class="data_list backMain">
	<div class="data_content">
		<table class="table table-hover table-bordered">
			<tr>
				<th>序号</th>
				<th>新闻类别编号</th>
				<th>新闻类别名称</th>
				<th>操作</th>
			</tr>
			
			<c:forEach items="${newsTypeBackList }" var="newsTypeBack" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>${newsTypeBack.newsTypeId }</td>
					<td>${newsTypeBack.typeName }</td>
					<td>
						<!-- 伪协议+重定向,通过&符号可以传多个参数 -->
						<button class="btn btn-info" type="button"
						onclick="javascript:window.location.href='newsType?action=newsTypeShow&newsTypeId=${newsTypeBack.newsTypeId }'">修改</button>
						<button class="btn btn-danger" type="button"
						onclick="newsTypeDelete(${newsTypeBack.newsTypeId });">删除</button>
					</td>
				</tr>
			</c:forEach>
				
		</table>
		${msg }
	</div>
</div>
</body>
</html>