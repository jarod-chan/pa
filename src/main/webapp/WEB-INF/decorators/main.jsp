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
/*for mainbar*/
.nav,.nav li ul{margin:0;padding:0;}
.nav {list-style-type:none;}
.nav li {position:relative;float:left;}
.nav li ul {display:none;position:absolute;top:15px;padding-top:3px;list-style-type:none;}
.nav li ul li {padding-top:5px;padding-left:0px;background-color: #FFFFFF;clear: both;}
.nav li ul li a{text-decoration: none;color:#000000;white-space:nowrap;}
.nav li ul li a:HOVER{background-color: #5BADFF;}
.nav li ul li a:VISITED{color: #000000;}

#mainbar {
	border:1px solid;
	border-style: none none solid none;
	border-color: #000000; /*5BADFF*/
	width:${pagesize}px;
	margin-top: 20px;
	padding-bottom:5px;
	margin-bottom: 5px;
}

.headdiv{
	width:${pagesize}px;
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

/*定义退出按钮*/
#mainbar .mainact{
	text-decoration: none;color: #000000;
}
#mainbar .mainact:HOVER{
	background-color: #000000; color: #FFFFFF;
}

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
<div style="float:left">
	<ul class="nav" style="float:left">
	<li>
		<div>&nabla;<strong>${pagefunc}&nbsp;&gt;&nbsp;${pagetitle}</strong></div>
		<ul>
		<c:forEach var="menu" items="${menuList}">
			<li><a href="/${ctx}/${menu.url}"><strong>&nbsp;&nbsp;${menu.name}&nbsp;&nbsp;</strong></a></li>
		</c:forEach>
		</ul>
	</li>
	</ul>
</div>
<div style="float:right">
	${loginInfo}
	<a class="mainact" title="退出系统"  href="/${ctx}/login">X</a>
</div>
<div style="clear:both"></div>
</div>
<decorator:body />  
</body>
</html>
