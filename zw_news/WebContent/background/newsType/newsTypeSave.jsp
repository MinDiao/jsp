<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>新闻网站后台管理</title>
<script type="text/javascript">
	
</script>
</head>
<body>
<div class="data_list backMain">
	<div class="data_content">
		<form action="newsType?action=save" method="post">
			<table style="padding: 5px">
				<tr>
					<td>
						<label>新闻类别名称：</label>
					</td>
					<td>
						<input type="text" id="typeName" name="typeName" value="${newsType.typeName }"/>
					</td>
				</tr>
				<tr>
					<td>
						<!-- 隐藏域 -->
						<input type="hidden" id="newsTypeId" name="newsTypeId" value="${newsType.newsTypeId }"/>
					</td>
					<td>
						<input type="submit" id="btnSubmit" class="btn btn-primary"  value="保存新闻类别"/>&nbsp;&nbsp;
						<input type="button" class="btn btn-primary" value="返回" onclick="javascript:history.back()"/>&nbsp;&nbsp;<font id="error" color="red"></font>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>
</body>
</html>