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
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style/news.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="bootstrap/js/jQuery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	// 删除
	function friendLinkDelete(linkId){
		if (confirm("您确定要删除此友情链接吗?")) {
			window.location.href="friendLink?action=friendLinkDelete&linkId="+linkId;
		}
	}
	
	// 修改
	function friendLinkUpdate(linkId){
		window.location.href="friendLink?action=friendLinkShowById&linkId="+linkId;
	}
	
</script>
</head>
<body>
	<div class="data_list backMain">
		<div class="data_content">
			<table class="table table-hover table-bordered">
				<tr>
					<th>链接编号</th>
					<th>链接名称</th>
					<th>链接地址</th>
					<th>联系人邮件</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${friendLinkAll }" var="friendLink">
					<tr>
						<td>${friendLink.linkId }</td>
						<td>${friendLink.linkName }</td>
						<td>${friendLink.linkUrl }</td>
						<td>${friendLink.linkEmail }</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="friendLinkUpdate(${friendLink.linkId})">修改</button>&nbsp;&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="friendLinkDelete(${friendLink.linkId})">删除</button>
						</td>
					</tr>
				</c:forEach>
				
			</table>
			${successAddUpdate }
			${successdelete }
		</div>
	</div>
</body>
</html>