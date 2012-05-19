<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%> 
<!doctype html>
<html>
<head>
<title><decorator:title/></title>
<decorator:head />
<style type="text/css">

/* 定义下拉菜单样式 */

.nav,.nav li ul{margin:0;padding:0;}
.nav {list-style-type:none;}
.nav li {position:relative;float:left;}
.nav li ul {display:none;position:absolute;top:15px;padding-top:3px;list-style-type:none;}
.nav li ul li {padding-top:5px;padding-left:0px;background-color: #FFFFFF;clear: both;}
.nav li ul li a{text-decoration: none;color:#000000;white-space:nowrap;}
.nav li ul li a:HOVER{background-color: #5BADFF;}
.nav li ul li a:VISITED{color: #000000;}

/*-----------------------------------------------------------------------------------------*/


/* 定义页面头部显示信息 */

#mainbar {
	border:1px solid;
	border-style: none none solid none;
	border-color: #000000; /*5BADFF*/
	width: 1010px;
	margin-top: 20px;
	padding-bottom:5px;
	margin-bottom: 20px;
	font-weight:bold;
}

/*取消浮动样式 ，通用  */
#mainbar .clear_div{
	clear: both;
}

/*上左  */
#mainbar .top_left{
	float: left;
	height:65px;
}

#mainbar .top_left .main_head{
	font-style:normal;
	font-weight:bold;
	font-size:36px;
	float:none;
	font-family:"黑体";
}

/*上右  */
#mainbar .top{
	margin-bottom: 5px;
}

#mainbar .top_right{
	float: right;
	height:65px;
}

#mainbar .top_right .main_blank{
	height:50px;
	float:none;
}

#mainbar .top_right .main_info{
	height:15px;
	text-align:right;
}


/* 下左 */
#mainbar .second_left{
	float: left;
}

/* 下左链接 */
#mainbar .second_left a{
	text-decoration: none;
	color:#000000;
	white-space:nowrap;
}
#mainbar .second_left a:HOVER{background-color: #5BADFF;}
#mainbar .second_left a:VISITED{color: #000000;}


/* 下右 */
#mainbar .second_right{
	float: right;
}

/*-----------------------------------------------------------------------------------------*/

/* 定义页面内部的头部信息 */

.headdiv{
	width:${pagesize}px;/* pagesize 从页面传入可否修改 */
}

.headdiv .headleft{
	width:50%;
	float: left;
}
.headdiv .headright{
	width:48%;
	float: right;
	text-align: right;
}
.headdiv .headnone{
   clear: both;
}
/*-----------------------------------------------------------------------------------------*/

</style>
<script type="text/javascript">
$(function(){
	$('.nav').children("li:has(ul)").hover(
		function(){
			$(this).children("ul").slideDown();
		},
		function(){
			$(this).children("ul").hide();
		}
	);
});
</script>
</head>
<%@ page import="java.util.*" %>
<%
	String loginInfo=(String)session.getAttribute("loginInfo");
	request.setAttribute("loginInfo", loginInfo);
	List menuList=(List)session.getAttribute("menuList");
	request.setAttribute("menuList", menuList);
%>

<body>

<div id="mainbar">
	<div class="top">
		<div class="top_left">
			<div class="main_head">方远房产卓越绩效管理平台</div>
		</div>
		<div class="top_right">
			<div class="main_blank"></div>
			<div class="main_info">${loginInfo}&nbsp;&nbsp;</div>
		</div>
		<div class="clear_div"></div>
	</div>
	
	<div class="second">
		<div class="second_left" >
			<a href="${pagefunc.url}">${pagefunc.name}</a>&gt;<a href="${pagetitle.url}">${pagetitle.name}</a>
		</div>
		<div class="second_right" >
			<!-- 功能菜单  -->
			<ul class="nav" style="float:left">
				<li>
					<div>&nabla;绩效系统功能&nbsp;&nbsp;</div>
					<ul>
					<c:forEach var="menu" items="${menuList}">
						<li><a href="/${ctx}/${menu.url}"><strong>&nbsp;&nbsp;${menu.name}&nbsp;&nbsp;</strong></a></li>
					</c:forEach>
					</ul>
				</li>
				<li>
					<div>&nabla;系统功能&nbsp;&nbsp;</div>
					<ul>
					
						<li><a href="/${ctx}/common/settings/person/${loginRet.personid}/password">&nbsp;&nbsp;修改密码&nbsp;&nbsp;</a></li>
					
						<li><a href="/${ctx}/login">&nbsp;&nbsp;退出系统&nbsp;&nbsp;</a></li>
					
					</ul>
				</li>
			</ul>
		</div>
		<div class="clear_div"></div>
	</div>

</div>





<decorator:body />  
</body>
</html>
