<%@ page language="java" import="java.util.*,model.*,dao.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<base href="<%=basePath%>">
		<title>小米商城</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">	
		
		 <style>
        * {
            margin: 0;
            padding: 0
        }
      
        .inner{
            width: 1226px;
            height: 460px;
            position: relative;
            overflow: hidden;
        }
        .inner img{
          	width: 1226px;
            height: 460px;
            vertical-align: top
        }
        #ul {
            width: 1000%;
            position: absolute;
            list-style: none;
            left:0;
            top: 0;
        }
        .inner li{
            float: left;
 
        }
 
        ol {
            position: absolute;
            height: 20px;
            right: 20px;
            bottom: 20px;
            text-align: center;
            padding: 5px;
        }
        ol li{
            display: inline-block;
            width: 20px;
            height: 20px;
            line-height: 20px;
            background-color: #fff;
            margin: 5px;
            cursor: pointer;
 
        }
        ol .current{
            background-color: red;
        }
        #arr{
            display: none;
        }
        #arr span{
            width: 40px;
            height: 40px;
            position: absolute;
            left: 5px;
            top: 50%;
            margin-top: -20px;
            background: #fff;
            cursor: pointer;
            line-height: 40px;
            text-align: center;
            font-weight: bold;
            font-family: '黑体';
            font-size: 30px;
            color: #000;
            opacity: 0.5;
            border: 1px solid #fff;
        }
        #arr #right {
            right: 5px;
            left: auto;
        }
    </style>
			
	</head>
	<body>
	<!-- start header -->
		<header>
			<div class="top center">
				<div class="left fl">
					<ul>
						<li><a href="">小米商城</a></li>
						<li>|</li>
						<li><a href="">MIUI</a></li>
						<li>|</li>
						<li><a href="">米聊</a></li>
						<li>|</li>
						<li><a href="">游戏</a></li>
						<li>|</li>
						<li><a href="">多看阅读</a></li>
						<li>|</li>
						<li><a href="">云服务</a></li>
						<li>|</li>
						<li><a href="">金融</a></li>
						<li>|</li>
						<li><a href="">小米商城移动版</a></li>
						<li>|</li>
						<li><a href="">问题反馈</a></li>
						<li>|</li>
						<li><a href="">Select Region</a></li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="right fr">
					<div class="gouwuche fr"><a href="z.protect/gouwuche.jsp">购物车</a></div>
					<div class="fr">
					
						  	
						<c:choose>
							<c:when test="${empty sessionScope.user}">
								<ul>
									<li><a href="./login.jsp" target="_blank">登录</a></li>
									<li>|</li>
									<li><a href="./register.jsp" target="_blank" >注册</a></li>													
								</ul>
							</c:when>

							<c:otherwise>
							    <ul>
									<li style="color:#b0b0b0;font-size:13px;">欢迎&nbsp;&nbsp;${sessionScope.user.uname}</li>
									<li>&nbsp;&nbsp;|</li>
									<li><a style="color:#b0b0b0;font-size:13px;" href="servlet/LogoutActionServelt">退出登录</a></li>
								</ul>                    
							</c:otherwise>
						</c:choose>
						
				<!-- 
					<%
						User user = (User)session.getAttribute("user");
						
						if(user!=null){														
						%>
							<ul>
								<li style="color:#b0b0b0;font-size:13px;">欢迎&nbsp;&nbsp;<%=user.getUname() %></li>
								<li>&nbsp;&nbsp;|</li>
								<li><a style="color:#b0b0b0;font-size:13px;" href="servlet/LogoutActionServelt">退出登录</a></li>
							</ul>
							
							
					<%}else{%>
						<ul>
							<li><a href="./login.jsp" target="_blank">登录</a></li>
							<li>|</li>
							<li><a href="./register.jsp" target="_blank" >注册</a></li>
													
						</ul>
							
				
					<%}%>
				-->
						
					</div>
					
				</div>
			
			</div>
		</header>
	<!--end header -->
	
	<!-- start banner_x -->
	<div class="banner_x center">
		<a href="./shouye.jsp" target="_blank"><div class="logo fl"></div></a>
		<a href=""><div class="ad_top fl"></div></a>
		<div class="nav fl">
			<ul>
				<%
					TypeDAO typeDAO = new TypeDAO();
					request.setAttribute("plist", typeDAO.queryByTypeID(-1));			
				%>
				<c:forEach var="product" items="${plist }">
					<li><a href="servlet/TypeServlet?typeID=${product.typeID }">${product.typename }</a></li>
				</c:forEach>

				<li><a href="">服务</a></li>
				<li><a href="">社区</a></li>
			</ul>
		</div>
		<div class="search fr">
			<form action="servlet/SearchServlet" method="post" >
				<div class="text fl">
					<input type="text" class="shuru" placeholder="小米6&nbsp;小米MIX现货" name="searchname">
				</div>
				<div class="submit fl">
					<input type="submit" class="sousuo" value="搜索" />
				</div>
				<div class="clear"></div>
			</form>
			<div class="clear"></div>
		</div>
	</div>
	<!-- end banner_x -->

	<!-- start banner_y -->
		<div class="banner_y center">
			
			<div class="box" id="box">
    <div class="inner">
        <!--轮播图-->
        <ul  id="ul">
            <li><a href="#"><img src="image/a1.jpg" alt=""></a></li>
            <li><a href="#"><img src="image/a2.jpg" alt=""></a></li>
            <li><a href="#"><img src="image/a3.jpg" alt=""></a></li>
            <li><a href="#"><img src="image/a4.jpg" alt=""></a></li>
            <li><a href="#"><img src="image/a5.jpg" alt=""></a></li>
        </ul>
 
        <ol class="bar">
 
        </ol>
        <!--左右焦点-->
        <div id="arr">
                    <span id="left">
                        <
                    </span>
            <span id="right">
                        >
                    </span>
        </div>
 
    </div>
</div>


		</div>
	<!-- end banner_y -->

	<div class="danpin center">

		<div class="biaoti center">商品列表</div>
		
		<div class="main center">

			<c:forEach items="${p}" var="product" varStatus="status">

				<div class="mingxing fl">
					<div class="sub_mingxing">
						<a href="servlet/ProductServlet?productID=${product.productID }"><img
							src="${product.image }" alt=""></a>
					</div>
					<div class="pinpai">
						<a href="servlet/ProductServlet?productID=${product.productID }">${product.productname }</a>
					</div>
					<div class="youhui">5月9日-21日享花呗12期分期免息</div>
					<div class="jiage">${product.price }元起</div>
				</div>
			</c:forEach>
		</div>
		<br>
		<div style="text-align:center;">
			<a href="servlet/PageServlet?op=p">上一页</a>
			<c:forEach begin="1" end="${page.pageSum}" step="1" var="i">

				<a href="servlet/PageServlet?op=${i }">${i }</a>

			</c:forEach>

			第 ${page.pageNum } 页 <a href="servlet/PageServlet?op=n">下一页</a>
		</div>
		
	</div>

	<footer class="mt20 center">			
			<div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select Region</div>
			<div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2014]0059-0009号</div> 
			<div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
		</footer>

<script>
    /**
     *
     * @param id  传入元素的id
     * @returns {HTMLElement | null}  返回标签对象，方便获取元素
     */
    function my$(id) {
        return document.getElementById(id);
    }
 
    //获取各元素，方便操作
    var box=my$("box");
    var inner=box.children[0];
    var ulObj=inner.children[0];
    var list=ulObj.children;
    var olObj=inner.children[1];
    var arr=my$("arr");
    var imgWidth=inner.offsetWidth;
    var right=my$("right");
    var pic=0;
    //根据li个数，创建小按钮
    for(var i=0;i<list.length;i++){
        var liObj=document.createElement("li");
 
        olObj.appendChild(liObj);
        liObj.innerText=(i+1);
        liObj.setAttribute("index",i);
 
        //为按钮注册mouseover事件
        liObj.onmouseover=function () {
            //先清除所有按钮的样式
 
            for (var j=0;j<olObj.children.length;j++){
                olObj.children[j].removeAttribute("class");
            }
            this.className="current";
            pic=this.getAttribute("index");
            animate(ulObj,-pic*imgWidth);
        }
 
    }
 
 
    //设置ol中第一个li有背景颜色
    olObj.children[0].className = "current";
    //克隆一个ul中第一个li,加入到ul中的最后=====克隆
    ulObj.appendChild(ulObj.children[0].cloneNode(true));
 
    var timeId=setInterval(onmouseclickHandle,5000);
    //左右焦点实现点击切换图片功能
    box.onmouseover=function () {
        arr.style.display="block";
        clearInterval(timeId);
    };
    box.onmouseout=function () {
        arr.style.display="none";
        timeId=setInterval(onmouseclickHandle,5000);
    };
 
    right.onclick=onmouseclickHandle;
    function onmouseclickHandle() {
        //如果pic的值是5,恰巧是ul中li的个数-1的值,此时页面显示第六个图片,而用户会认为这是第一个图,
        //所以,如果用户再次点击按钮,用户应该看到第二个图片
        if (pic == list.length - 1) {
            //如何从第6个图,跳转到第一个图
            pic = 0;//先设置pic=0
            ulObj.style.left = 0 + "px";//把ul的位置还原成开始的默认位置
        }
        pic++;//立刻设置pic加1,那么此时用户就会看到第二个图片了
        animate(ulObj, -pic * imgWidth);//pic从0的值加1之后,pic的值是1,然后ul移动出去一个图片
        //如果pic==5说明,此时显示第6个图(内容是第一张图片),第一个小按钮有颜色,
        if (pic == list.length - 1) {
            //第五个按钮颜色干掉
            olObj.children[olObj.children.length - 1].className = "";
            //第一个按钮颜色设置上
            olObj.children[0].className = "current";
        } else {
            //干掉所有的小按钮的背景颜色
            for (var i = 0; i < olObj.children.length; i++) {
                olObj.children[i].removeAttribute("class");
            }
            olObj.children[pic].className = "current";
        }
    }
    left.onclick=function () {
        if (pic==0){
            pic=list.length-1;
            ulObj.style.left=-pic*imgWidth+"px";
        }
        pic--;
        animate(ulObj,-pic*imgWidth);
        for (var i = 0; i < olObj.children.length; i++) {
            olObj.children[i].removeAttribute("class");
        }
        //当前的pic索引对应的按钮设置颜色
        olObj.children[pic].className = "current";
    };
 
    //设置任意的一个元素,移动到指定的目标位置
    function animate(element, target) {
        clearInterval(element.timeId);
        //定时器的id值存储到对象的一个属性中
        element.timeId = setInterval(function () {
            //获取元素的当前的位置,数字类型
            var current = element.offsetLeft;
            //每次移动的距离
            var step = 10;
            step = current < target ? step : -step;
            //当前移动到位置
            current += step;
            if (Math.abs(current - target) > Math.abs(step)) {
                element.style.left = current + "px";
            } else {
                //清理定时器
                clearInterval(element.timeId);
                //直接到达目标
                element.style.left = target + "px";
            }
        }, 10);
    }
</script>


	</body>
</html>
