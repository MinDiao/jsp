<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>员工列表</title>
    <link rel="stylesheet" href="css/empList.css">
    <style type="text/css">
    	table {
		  margin: auto;
		  border-collapse: collapse;
		  text-align: center;
		}
		table tr + tr {
		  border-top: 1px solid #ccc;
		}
		table th,td {
		  width: 4.6875rem;
		  height: 2.34375rem;
		}
    </style>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery-3.1.1.js"></script>
    <script type="text/javascript">
    	window.onload = function(){
    		// 进入页面后先刷新一次员工列表
    		empListJQ();
    	}
    	function empListJQ(){
    		$(function(){
    			// 先清除tbody中的内容
    			$("tbody").html("");
    			// get请求方式
       			$.get(
       					// 请求地址
           				"${pageContext.request.contextPath}/emp?action=empServletAll",
           				// 响应数据
           				function(data){
           					// 将请求到的数据遍历
           					$.each(data.result, function(index, items){
           						// 创建tr标签
           						let $tr = $("<tr></tr>");
           						// 将请求到的属性填充到td标签中
           						let $empName = $("<td>"+ items.empName +"</td>");
           						let $empBirthday = $("<td>"+ items.empBirthday +"</td>");
           						let $empphone = $("<td>"+ items.empphone +"</td>");
           						let $deptName = $("<td>"+ items.deptName +"</td>");
           						// 创建修改,删除的按钮
           						let $button = $('<td>'+
           								 '<button type="button" onclick="empUpdate('+ items.empID +')">修改</button>'+
           				                 '<button type="button" onclick="empDelete('+ items.empID +')">删除</button>'+
           								 '</td>');
           						// 将td填充到tr标签中
           						$tr.append($empName);
           						$tr.append($empBirthday);
           						$tr.append($empphone);
           						$tr.append($deptName);
           						$tr.append($button);
           						// 将填充好的tr标签填充到tbody中
           						$("tbody").append($tr);
           					})
        				},
        				// 响应类型
        				"json"
           		);
    		});
    	};
    	
    	
    	function empDelete(empID){
    		// 让用户进行再次确认选择
			if (confirm("您确定要删除此员工吗?")) {
				$(function(){
					// post请求方式
					$.post(
							// 请求地址
	    					"${pageContext.request.contextPath}/emp?action=empDelete&empID="+empID,
	    					// 请求数据
   							{
	    						"empID":empID
	    					},
	    					// 响应数据
	    					function(data){
	    						// 判断是否删除成功
	    						if (data.result > 0) {
	    							// 删除成功
									alert("删除成功");
	    							// 刷新员工列表方法
									empListJQ();
									windwo.location.href="emp?action=empServletAll";
								} else {
									alert("删除失败")
								}
	    					}
	    			);
				})
			}
		};
    </script>
</head>
<body>
   	<table>
    	<thead>
	        <tr>
	            <th>员工姓名</th>
	            <th>员工生日</th>
	            <th>员工电话</th>
	            <th>所属部门</th>
	            <th>操作</th>
	        </tr>
        </thead>
        <tbody></tbody>
   	</table>

    <script>
        // 修改员工信息,先获取员工的id,为了将原来的信息打印到文本框中
        function empUpdate(empID){
        	window.location.href = "emp?action=empServletById&empID="+empID;
        }
    </script>
</body>
</html>