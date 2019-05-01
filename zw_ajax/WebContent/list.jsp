<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(function(){
		$.get(
				"${pageContext.request.contextPath}/three",
				function(data){
					/* $(data.result).each(function(){
						
					}); */
					// 与上面作用相同
					$.each(data.result,function(index,element){
						var tr = $("<tr></tr>");
						// 编号
						var id = $("<td>"+element.id+"</td>");
						// 姓名
						var name = $("<td>"+element.name+"</td>");
						// 年龄
						var age = $("<td>"+element.age+"</td>");
						// 地址
						var address = $("<td>"+element.address+"</td>");
						
						// 添加节点
						tr.append(id);
						tr.append(name);
						tr.append(age);
						tr.append(address);
						
						$("tbody").append(tr);
					})
				},
				"json"
		);
	})
</script>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>地址</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</body>
</html>