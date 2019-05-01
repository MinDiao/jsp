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
<script src="js/ckeditor/ckeditor.js"></script>
<title>新闻管理</title>
<script type="text/javascript">
	// 相当于window.onload
	$(document).ready(function(){
		// $代表jQuery对象
		$("#btnSubmit").click(function(){
			// 获取新闻标题值
			var title = $("#title").val();
			// 得到作者
			var author = $("#author").val();
			// 获取新闻类别值
			var typeId = $("#typeId").val();
			// 获取富文本编辑器中内容-> instances实例
			var content = CKEDITOR.instances.content.getData();
			// 声明开关变量
			var state = true;
			// 判空
			if (title==null || title=="") {
				$("#errorTitle").html("新闻标题不能为空!");
				state=false;
			}
			if (author==null || author=="") {
				$("#errorAuthor").html("作者不能为空!")
				state=false;
			}
			if (typeId==null || typeId=="") {
				$("#errorType").html("请选择新闻类别!");
				state=false;
			}
			if (content==null || content=="") {
				$("#errorContent").html("新闻内容不能为空!");
				state=false;
			}
			// 使用ajax封装一个数据
			if (state) {
				$.ajax({
					url:"news?action=newsSave",
					type:"post",
					data:{
						newsId:$("#newsId").val(),
						title:title,
						author:author,
						typeId:typeId,
						content:content,
						isHead:$("#isHead").val(),
						isHot:$("#isHot").val()
					},
					// 服务器响应回来的数据大致有3种类型:text \ json(轻量级数据格式->其实就是键值对,可以包含数据,可以包含集合) \ xml
					// {"result":1,"type":"Add"}
					dataType:"json",
					// 接收成功是个回调函数
					success:function(data){
						if (data.result==1) {
							alert(data.type=="update" ? "修改成功":"添加成功");
							// 保存成功跳转页面
							window.location.href="news?action=backList";
						} else {
							alert("保存失败");
						}
					}
					
				});
			}
		});
	});
</script>
</head>
<body>
<div class="data_list backMain">
	<div class="data_content">
		<form id="formNews"  method="post">
			<table style="padding: 5;width: 100%">
				<tr>
					<td width="80px">
						<label>新闻标题：</label>
					</td>
					<td>
						<input type="text" id="title" name="title" class="input-xxlarge" value="${news.title }"/>
						<font id="errorTitle" color="red"></font>
					</td>
				</tr>
				<tr>
					<td>
						<label>新闻作者：</label>
					</td>
					<td>
						<input type="text" id="author" name="author" value="${news.author }"/>
						<font id="errorAuthor" color="red"></font>
					</td>
				</tr>
				<tr>
					<td>
						<label>新闻类别：</label>
					</td>
					<td>
						<select id="typeId" name="typeId">
							<option value="">请选择新闻类别</option>
								<c:forEach items="${newsTypeList }" var="newsType">
									<!-- 将newsType的id与news的id进行比较,如果有相同的id则进行绑定显示然后进行修改,如果为空则添加 -->
									<option value="${newsType.newsTypeId }" ${newsType.newsTypeId==news.typeId ? 'selected':'' }>
										${newsType.typeName }
									</option>
								</c:forEach>
						</select>
						<font id="errorType" color="red"></font>
					</td>
				</tr>
				<tr>
					<td>
						<label>新闻属性：</label>
					</td>
					<td>
						<label class="checkbox inline">
						  <input type="checkbox" id="isHead" name="isHead" value="1" ${news.isHead==1? 'checked':'' }>头条
						</label>
						<label class="checkbox inline">
						  <input type="checkbox" id="isHot" name="isHot" value="1" ${news.isHot==1? 'checked':'' }>热点
						</label>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<label>新闻内容：</label>
					</td>
					<td>
						<textarea class="ckeditor" id="content" name="content">
							${news.content }
						</textarea>
						<font id="errorContent" color="red"></font>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="newsId" name="newsId" value="${news.newsId }"/>&nbsp;
					</td>
					<td>
						<input type="button" id="btnSubmit" class="btn btn-primary" value="保存新闻"/>&nbsp;&nbsp;
						<input type="button" class="btn btn-primary" value="返回" onclick="javascript:history.back()"/>&nbsp;&nbsp;<font id="errorContent" color="red"></font>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>	
</body>
</html>