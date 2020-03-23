<%@ page language="java" import="java.util.* ,model.*"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<base href="<%=basePath%>">      
		<title>会员登录</title>
		<link rel="stylesheet" type="text/css" href="css/login.css" />
	</head>
	<body>
		<!-- login -->
		<div class="top center">
			<div class="logo center">
				<a href="./shouye.jsp" target="_blank"><img src="image/mistore_logo.png" alt=""></a>
			</div>
		</div>
		
		<form  action="servlet/LoginActionServlet" method="get" class="form center">
		
		<div class="login">
			<div class="login_center">
				<div class="login_top">
					<div class="left fl">会员登录</div>
					<div class="right fr">您还不是我们的会员？<a href="${pageContext.request.contextPath }/register.jsp" target="_self">立即注册</a></div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div style="margin-top:-30px;" class="login_main center">
				
					<div class="username">用户名:&nbsp;<input class="shurukuang" type="text" name="uname" placeholder="请输入你的用户名" 
						value="${requestScope.user.uname}"
					/></div>
					<div class="username">密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;<input class="shurukuang" type="password" name="upwd" 
					placeholder="请输入你的密码" value="${requestScope.user.upwd}"/></div>
					<div class="username">
							
						<div class="left fl">验证码:&nbsp;<input class="yanzhengma" type="text" name="code" placeholder="请输入验证码"/></div>
						
						<div class="right fl">
							<img style="" id="validationCode" src="servlet/ValidationCodeServlet">
						</div>
						
						
					</div>
					<div class="username">
						<input style="vertical-align: middle;" type="checkbox" name="remember" value="remember">记住密码<br>
						<span style="color:red;padding-left:130px;"><b>${msg }</b></span>
					</div>
				</div>
			
				<div  style="margin-top:-20px; class="login_submit">
					<input class="submit" type="submit" name="submit" value="立即登录" >
				</div>
				
			</div>
		</div>
		</form>
		<footer>
			<div class="copyright">简体 | 繁体 | English | 常见问题</div>
			<div class="copyright">小米公司版权所有-京ICP备10046444-<img src="image/ghs.png" alt="">京公网安备11010802020134号-京ICP证110507号</div>

		</footer>
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
