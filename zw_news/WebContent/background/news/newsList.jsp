<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<!-- 格式化的功能,可以用来格式化日期 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script src="js/ckeditor/ckeditor.js"></script>
<script src="js/My97DatePicker/WdatePicker.js"></script>
<title>新闻维护</title>
<script type="text/javascript">
	function newsDelete(newsId){
		// 删除
		if (confirm("确定要删除这条新闻吗?")) {
			// 使用Ajax方法
			$.ajax({
				// 请求地址
				url:"news?action=newsDelete",
				// 请求方式 post可将请求数据填充到请求体中,不像get数据在请求头上(不安全)
				type:"post",
				// 请求的数据{请求的参数(与Servlet对应):请求的数据}
				data:{newsId:newsId},
				// 请求数据的类型(文本类型),如果不定义text类型则默认为HTML页面
				dataType:"text",
				// 返回请求(data是文本数据)
				success:function(data){
					// 判断是否请求成功
					if (data=="true") {
						// 删除失败
						alert("删除失败");
					} else {
						
						// 删除成功
						alert("删除成功");
						// 重新绑定数据源来查询,来设置跳转的链接
						window.location.href="news?action=backList";
					}
				}
			});
		}
	}
</script>
</head>
<body>
	<div class="data_list backMain">
		<div class="search_content" style="vertical-align: middle;">
			<form action="news?action=backList" method="post">
				新闻标题: <input type="text" name="title" value="${search.title }">&nbsp;&nbsp;
				发布日期： <input type="text" name="startDate" class="Wdate"
					onclick="WdatePicker()" style="width: 120px;" value="${search.startDate }" />&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" name="endDate" class="Wdate"
					onclick="WdatePicker()" style="width: 120px;" value="${search.endDate }" />&nbsp;&nbsp;
				<button class="btn  btn-primary" type="submit"
					style="margin-top: -8px;">查询</button>
			</form>
		</div>
		<div class="data_content">
			<table class="table table-hover table-bordered">
				<tr>
					<th><input type="checkbox" id="checkedAll" /></th>
					<th>序号</th>
					<th>新闻标题</th>
					<th>新闻类别</th>
					<th>发布时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${newsBackList }" var="newsBack" varStatus="status">
					<tr>
						<td><input type="checkbox" name="newsIds" value="" /></td>
						<td>${status.index+1 }</td>
						<td>${newsBack.title }</td>
						<td>${newsBack.typeName }</td>
						<td><fmt:formatDate value="${newsBack.publishDate }" type="date" pattern="yyyy-MM-dd"/></td>
						<td>
							<button class="btn  btn-info" type="button" 
							onclick="javascript:window.location.href='news?action=newsShow&newsId=${newsBack.newsId}'">修改</button>&nbsp;
							<button class="btn btn-danger" type="button" 
							onclick="newsDelete(${newsBack.newsId})">删除</button>
						</td>
					</tr>
				</c:forEach>
				
			</table>
		</div>

		<div class="pagination pagination-centered">
			${pageCode }
		</div>
	</div>
</body>
</html>