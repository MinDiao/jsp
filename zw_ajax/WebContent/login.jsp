<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
        .login {
            margin: 10px auto;
            width: 400px;
            padding-top:60px;
        }

        fieldset {
            height: 150px;
        }

        .items {
            width: 100%;
            height: 30px;
            margin-left: 30px;
        }

        label {
            display: inline-block;
            width: 60px;
        }
    </style>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#btnLogin").click(function(){
    			$.post(
    					"${pageContext.request.contextPath}/two",
    					{
    						"username":$("input[name='username']").val(),
    						"userpwd":$("input[name='userpwd']").val()
    					},
    					function(data) {
    						if (data.result == 1) {
								alert("登录成功");
							} else {
								alert("登录失败");
							}
    					}/* ,"json" */
    			);
    		});
    	});
    </script>
</head>
<body>
	<div class="login">
        <fieldset>
            <legend>用户登录</legend>
            <form id="form1">
                <div class="items">
                    <label for="username">用户名:</label>
                    <input type="text" name="username" id="username" value="" placeholder="输入用户名" />
                    <span id="msg1"></span>
                </div>
                <div class="items">
                    <label for="userpwd">密码:</label>
                    <input type="password" name="userpwd" id="userpwd" value="" placeholder="输入密码" />
                    <span id="msg2"></span>
                </div>
                <div class="items">
                    <input type="button" name="name" id="btnLogin" value="登录" />
                </div>
            </form>
        </fieldset>
    </div>
</body>
</html>