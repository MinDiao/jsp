<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>    
<!DOCTYPE html>
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
</script>
</head>
<body>
<div class="data_list backMain">

	<div class="data_content">
		<form action="friendLink?action=friendLinkAddUpdate&onoff=update" method="post" onsubmit="return checkForm()">
			<table style="padding: 5px">
				<tr>
					<td>
						<label>链接名称：</label>
					</td>
					<td>
						<input type="text" id="linkName" name="linkName" value="${friendLinkSave.linkName }"/>
						<span id="errorName" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td>
						<label>链接地址：</label>
					</td>
					<td>
						<input type="text" id="linkUrl" name="linkUrl" value="${friendLinkSave.linkUrl }"/>
						<span id="errorUrl" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td>
						<label>联系人邮件：</label>
					</td>
					<td>
						<input type="text" id="linkEmail" name="linkEmail" value="${friendLinkSave.linkEmail }"/>
						<span id="errorEmail" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="linkId" name="linkId" value="${friendLinkSave.linkId }"/>
					</td>
					<td>
						<input type="submit" id="btnSubmit" class="btn btn-primary" value="保存友情链接"/>&nbsp;&nbsp;
						<input type="button" class="btn btn-primary" value="返回" onclick="javascript:history.back()"/>&nbsp;&nbsp;
				</tr>
			</table>
			
		</form>
	</div>
</div>
</body>
</html>