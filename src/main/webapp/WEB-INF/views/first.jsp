<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
<link type="text/css" rel="stylesheet" href="/${ctx}/resources/css/mainbar.css" /> 

</head>
<body>
<div id="mainbar">
	<div class="top">
		<div class="top_left">
			<div class="main_head">方远房产卓越绩效管理平台</div>
		</div>
		<div class="top_right">
			<div class="main_blank"><input type="button" value="登录" onclick="back()"/>&nbsp;&nbsp;</div>
			<div class="main_info"></div>
		</div>
		<div class="clear_div"></div>
	</div>
	
	<div class="second">
		<div class="second_left" >
			<a href="/${ctx}">首页</a>
		</div>
		<div class="second_right" >
			<!-- 功能菜单  -->
			<ul class="nav" style="float:left">
				<li>
					<div>&nabla;绩效系统功能&nbsp;&nbsp;</div>
				</li>
				<li>
					<div>&nabla;系统功能&nbsp;&nbsp;</div>
				</li>
			</ul>
		</div>
		<div class="clear_div"></div>
	</div>
</div>

<%@ include file="notification.jsp"%>

<style type="text/css">
body {
	border: 0;
	height: 100%; /* 必须 */
	overflow-y: auto; /* 必须 */
}

#fixdiv {
	display: block;
	top: 40px;
	left: ${pagewidth-291}px;
	width: 300px;
	position: fixed;
} /* IE并不认识fixed，而FF认识 */
* html #fixdiv {
	position: absolute;
} /* 这个只有IE认识 */
#fixdiv {
	background-color:#FFFFFF;            /*log 图片颜色 #ADD779  */
	border:solid;
	border-width:1px;
	border-color: #000000 ;                 /* #5FBA63 */
	text-align: center;
}
#fixdiv  div{
	height: 25px;
}
</style>
<!--[if IE 6]>
   <style type="text/css">
   /*<![CDATA[*/
	html {overflow-x:auto; overflow-y:hidden;}
   /*]]>*/
   </style>
<![endif]-->

<script type="text/javascript">
	
	$(document).ready(function() {
		$("#loginform :input[name='username']").bind("blur",blurUsername).triggerHandler("blur");
	});

	//特殊人员事件
	var blurUsername=function(){
		var username=$(this).val().trim();
		$(this).val(username);
		if(username=='牟一琦'||username=='陆兆贤'){
			$("#specSel").show();
		}else{
			$("#specSel").hide();
		}
	}



</script>

<form id="loginform" action="login" method="post">
	<div id="fixdiv" >
		<div></div>
		<div>
			用户：<input type="text" name="username"  value="${loginPage.username}"  style="width:150px;"/> 
		</div>
		<div>
			密码：<input type="password" name="password" value=""   style="width:150px;"/>
		</div>
		<div id="specSel">
			角色：<select name="specialPerson" style="width:155px;">
					<option value="G">分管副总</option>
					<option value="Y">部门经理</option>
				</select>
		</div>
		<div>
			<c:if test="${message!=null}">
				<font id="msg" style="color:red;" >${message}</font>
			</c:if>
		</div>
		<div>
			 <input type="submit" value="登录系统"/>
	 		 <input type="button" value="忘记密码"  onclick="javascript:window.open('/pa/fetchcsr','_self')"/>
		</div>
	</div>
</form>	
</body>
</html>
