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
</head>
<body>
    <form action="">
    	<table>
        <tr>
            <th>员工姓名</th>
            <th>员工生日</th>
            <th>员工电话</th>
            <th>所属部门</th>
            <th>操作</th>
        </tr>
	        <c:forEach items="${empDeptList }" var="empDept">
	         <tr>
	             <td>${empDept.empName }</td>
	             <td>${empDept.empBirthday }</td>
	             <td>${empDept.empphone }</td>
	             <td>${empDept.deptName }</td>
	             <!--隐藏域-->
	             <input type="hidden" name="EmpID" value="${empDept.empID }">
	             <input type="hidden" name="deptId" value="${empDept.deptId }">
	             <td>
	                 <!-- <button type="button" onclick="empUpdate(${empDept.empID},${empDept.empName },${empDept.empBirthday },${empDept.empphone },${empDept.deptName },${empDept.deptId })">修改</button> -->
	                 <button type="button" onclick="empUpdate(${empDept.empID})">修改</button>
	                 <button type="button" onclick="empDelete(${empDept.empID})">删除</button>
	             </td>
	         </tr>
	        </c:forEach>
    	</table>
    </form>

    <script>
        // 修改员工信息
        /* function empUpdate(empID, empName, empBirthday, empphone, deptName, deptId){
        	window.location.href = "emp?action=empServletById&empID="+empID+"&empName="+empName+"&empBirthday="+empBirthday+"&empphone="+empphone+"&deptName="+deptName+"&deptId="+deptId;
        } */
        function empUpdate(empID){
        	window.location.href = "emp?action=empServletById&empID="+empID;
        }
        // 删除员工信息
        function empDelete(empID){
        	if (confirm("您确定要删除此员工吗?")) {
        		window.location.href = "emp?action=empDelete&empID="+empID;
			}
        }
    </script>
    ${succe }
</body>
</html>