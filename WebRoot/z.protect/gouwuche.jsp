<%@ page language="java" import="java.util.*,model.*,dao.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<base href="<%=basePath%>">
		<title>我的购物车-小米商城</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	
		<style>
			.gw_num{border: 1px solid #dbdbdb;width: 110px;line-height: 26px;overflow: hidden; margin-top:45px; margin-left:-22px;}
			.gw_num em{display: block;height: 26px;width: 26px;float: left;color: #7A7979;border-right: 1px solid #dbdbdb;text-align: center;cursor: pointer;}
			.gw_num .num{display: block;float: left;text-align: center;width: 52px;font-style: normal;font-size: 14px;line-height: 24px;border: 0;}
			.gw_num em.add{float: right;border-right: 0;border-left: 1px solid #dbdbdb;}
			.quanxuan{vertical-align: middle;}
		</style>	
	</head>
	<body>

<!-- start banner_x -->
		<div class="banner_x center" >
			<a href="./index.jsp" target="_blank"><div class="logo fl"></div></a>
			
			<div class="wdgwc fl ml40">我的购物车</div>
			<div class="wxts fl ml20">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</div>
			<div class="dlzc fr">

				<c:if test="${not empty sessionScope.user}">
					<ul>
						<li style="color:#b0b0b0;font-size:13px;">欢迎&nbsp;&nbsp;${sessionScope.user.uname}</li>
						<li>&nbsp;&nbsp;|</li>
						<li><a style="color:#b0b0b0;font-size:13px;"
							href="servlet/LogoutActionServelt">退出登录</a></li>
					</ul>
				</c:if>

			</div>
			
		</div>
	<hr>
		
<form action="servlet/BuyServlet?uname=${sessionScope.user.uname}" method="post" >		
		<div class="gwcxqbj" style="overflow:auto; height:389px">

			<div class="gwcxd center">
				<div class="top2 center">
					<div class="sub_top fl">
						<input type="checkbox" value="quanxuan" class="quanxuan"  id="chk_all"/>全选
					</div>
					<div class="sub_top fl">商品名称</div>
					<div class="sub_top fl">单价</div>
					<div class="sub_top fl">数量</div>
					<div class="sub_top fl">商品编号</div>
					<div class="sub_top fr">操作</div>
					<div class="clear"></div>
				</div>
		  	<c:forEach items="${c}" var="c" varStatus="status">
				<div class="content2 center" >
					<div class="sub_content fl ">
						<input type="checkbox" value="${c.cartID }" class="quanxuan" name="chk_list"/>
					</div>
					<div class="sub_content fl"> </div>
					
					<div class="sub_content fl ft20">${c.cartname } </div>
					
					<div class="sub_content fl price" style="padding-left:5px;">￥${c.cartprice }</div>
					
					<div class="sub_content fl">
					
						<div class="gw_num">
	   						<em class="jian">-</em>
	   							<input type="text" value="1" class="num"/>
	   						<em class="add">+</em>
						</div>
						
					</div>
					
					<div class="sub_content fl" style="text-align:right;">${c.cartID }</div>
					
					
					<div class="sub_content fl"><a href="servlet/CartDelServlet?cartID=${c.cartID }&uname=${sessionScope.user.uname}">×</a></div>
					
					
					<div class="clear"></div>
				</div>
    		</c:forEach>
	
			</div>
			
			<div class="jiesuandan mt20 center">
				<div class="tishi fl ml20">
					<ul>
						<li><a href="./index.jsp">继续购物</a></li>
						<li>|</li>
						<li>
							<%
								CartDAO cdao = new CartDAO();
								User user = (User)session.getAttribute("user");
								String uname = user.getUname();;
								int z=cdao.shumu(uname);
							%>	
							共<span><%=z%></span>件商品，已选择<span id="num">0</span>件
						
						</li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="jiesuan fr">
					<div class="jiesuanjiage fl">合计（不含运费）：<span>￥</span><span id="totalPrice">0</span></div>
					<div class="jsanniu fr"><input class="jsan" type="submit" name="jiesuan"  value="去结算"/></div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
			
		</div>
</form>
  

	
	<!-- footer -->
	<footer class="center" style="padding-top:70px;">
			
			<div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select Region</div>
			<div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2014]0059-0009号</div> 
			<div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
		</footer>

    <script src="js/jquery.min.js"></script>
		
<script type="text/javascript">
$(function () {

		//全选
	$("#chk_all").click(function(){
		$("input[name='chk_list']").prop("checked", $(this).prop("checked"));
	 	 }); 
           
		//计算商品总价格
		 $(":checkbox").change(function () {
		
           calTotalPrice();
 
        });
	
	
		//显示选中数量　
		$(":checkbox").change(function () {		
			var $num = $("#num");
			var i=0;	
		 $($("input[name='chk_list']:checked")).each(function(){
		 	i++;
		 });
		 $num.text(i);
		});
	
		//加的效果
		$(".add").click(function(){
	   		var n=$(this).prev().val();
	   		var num=parseInt(n)+1;
	   		if(num==0){ return;}
	     	 $(this).prev().val(num);
	     	 
	 		calTotalPrice();
	 		}
 		);
 		//减的效果
 		$(".jian").click(function(){
  		var n=$(this).next().val();
   		var num=parseInt(n)-1;
   		if(num==0){ return}
   		$(this).next().val(num);
   		
	 		calTotalPrice();
  		});
        
 });
 
 function calTotalPrice(){
 	var $checkedCheckbox = $(".content2").find(":checkbox:checked");
 	var $totalPrice = $("#totalPrice");
 	var totalPrice = 0;
 	$checkedCheckbox.each(function (){
 		 var price = parseFloat($(this).parent().parent().find(".price").text().substring(1));
         var count = parseInt($(this).parent().parent().find(".num").val());
         totalPrice += price*count;
 	});
 	$totalPrice.text(totalPrice);
 }
</script>



	</body>
</html>