<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>小米商城</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
        <p>注册成功</p>
    	<p>还有<span></span>秒跳转到登录页面</p>
    	<p>如果您的浏览器不支持自动跳转，<a href="login.jsp">请点击登录链接！</a></p>
    <script src="js/jquery.min.js"></script>
    <script>
    	let $span = null;
    	let num = 0;
    
    	function decrease(){
    		num--;
    		$span.text(num);
    		
    		if(num>0){    			
    			setTimeout(decrease, 1000);
    		}else{
    			location.href = "login.jsp";
    		}
    	}
    	
    	$(function(){
    		$span = $("span");
    		num = 5;
    		$span.text(num);
    		
    		setTimeout(decrease, 1000);
    	});
    </script>
  </body>
</html>
