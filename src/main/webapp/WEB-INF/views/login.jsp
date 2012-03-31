<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
<style type="text/css">
.center{
margin-left:auto;
margin-right:auto;
text-align:center;
} 
</style>
<script type="text/javascript">



	//特殊人员事件
	function blurUsername(){
		var username=$(this).val().trim();
		$(this).val(username);
		if(username=='牟一琦'||username=='陆兆贤'){
			$("#specSel").show();
		}else{
			$("#specSel").hide();
		}
	}

	$(document).ready(function() {
		$(":input[name='username']").bind("blur",blurUsername).triggerHandler("blur");
	});

</script>
</head>
<body>

<div class="center"><h1>方远房产卓越绩效管理平台</h1></div>
<div class="center">
<form action="login" method="post">
<img src="/pa/resources/img/logo.jpg">
<br/>
用户：<input type="text" name="username"  value="${loginPage.username}"  style="width:150px;"/> 
<br/>

密码：<input type="password" name="password" value=""   style="width:150px;"/>
 <br/>
<div id="specSel">
角色：<select name="specialPerson" style="width:150px;">
		<option value="G">分管副总</option>
		<option value="Y">部门经理</option>
	</select>
</div>
 <c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:200px;margin-left:auto;margin-right:auto;">${msg}</div>
 </c:if>
 <br/>
 <input type="submit" value="登录"/>
 <input type="button" value="忘记密码"  onclick="javascript:window.open('/pa/fetchcsr','_self')"/>
</form>
</div> 
</body>
</html>
