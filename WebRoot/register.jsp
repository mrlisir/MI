<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<base href="<%=basePath%>">
		<title>用户注册</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css">
		<style>
			.error{
				color:red;
				font-size:14px;
				padding-left:88px;
			}
		</style>
	</head>
	<body>
		<form action="servlet/RegisterActionServlet" method="post" enctype="multipart/form-data">
		<div class="regist">
			<div class="regist_center">
				<div class="regist_top">
					<div class="left fl">会员注册</div>
					<div class="right fr"><a href="shouye.jsp" target="_self">小米商城</a></div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div class="regist_main center">
					<div class="username">用&nbsp;&nbsp;户&nbsp;&nbsp;名:&nbsp;&nbsp;<input class="shurukuang" type="text" name="uname" placeholder="请输入你的登录名"/><span>请不要输入汉字</span></div>
					<span class="error">${uname}</span><br>
					<div class="username">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;<input class="shurukuang" type="password" name="upwd" placeholder="请输入你的密码"/><span>请输入英文字母或数字</span></div>
					<span class="error">${upwd}</span><br>
					<div class="username">确认密码:&nbsp;&nbsp;<input class="shurukuang" type="password" name="upwds" placeholder="请确认你的密码"/><span>两次密码要输入一致哦</span></div>
					<span class="error">${upwds}</span><br>
					
					<div class="username">
						验&nbsp;&nbsp;证&nbsp;&nbsp;码:&nbsp;
						<input class="shurukuang" type="text" name="code" placeholder="请输入验证码"/>
						<span><img id="validationCode" src="servlet/ValidationCodeServlet"></span>
					</div>
					<span class="error">${kong}</span><br>
					
				</div>
				<div class="regist_submit">
					<input class="submit" type="submit" name="submit" value="立即注册" >
				</div>
				
			</div>
		</div>
		</form>
		<script src="js/jquery.min.js"></script>
		<script>
			$(function(){
				$("#validationCode").click(function(){
					var code = document.getElementById("validationCode");
					code.src = "servlet/ValidationCodeServlet?timestamp="+new Date().getTime();	
				});
			});
		</script>
	</body>
</html>
