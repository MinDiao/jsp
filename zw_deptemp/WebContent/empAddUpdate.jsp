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
    <link rel="stylesheet" href="css/empAdd.css">
    <title>员工添加</title>
    <style>
        .error{
            color: transparent;
        }
    </style>
</head>
<body>
    <form action="emp?action=empAddUpdate&empID=${empDept.empID }" method="post" onsubmit="return runSubmit()">
        <ul>
            <li>
                <span>员工名字：</span>
                <input type="text" class="empAddUpdateInput" name="empName" value="${empDept.empName }">
                <i class="error">输入提示</i>
            </li>
            <li>
                <span>员工生日：</span>
                <input type="date" class="empAddUpdateInput" name="empBirthday" value="${empDept.empBirthday }">
                <i class="error">输入提示</i>
            </li>
            <li>
                <span>员工电话：</span>
                <input type="text" class="empAddUpdateInput" name="empphone" value="${empDept.empphone }">
                <i class="error">输入提示</i>
            </li>
            <li>
                <span>所属部门：</span>
                <select name="deptId" id="deptId">
                	<c:forEach items="${deptList }" var="dept">
                    	<option value="${dept.deptId }">${dept.deptName }</option>
                	</c:forEach>
                </select>
            </li>
            <!--隐藏域-->
            <li>
                <input type="hidden" name="empID" value="${empDept.empID }">
            </li>
            <li>
                <!--提交-->
                <input type="submit" value="保存">
                <!--返回-->
                <input type="button" onclick="history.back()" value="返回">
            </li>
        </ul>
    </form>
    
    <!--判断输入的合法性-->
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script src="./js/empAddUpdate.js"></script>
    <script type="text/javascript">
    	// 作用于修改时对应员工的部门ID的下拉选项
    	$(function(){
    		$("#deptId option[value='${empDept.deptId}']").prop("selected",true);
    	});
    </script>
</body>
</html>