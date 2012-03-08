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
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
	 });
</script>  

</head>
<body>
<div style="margin: 100 auto; text-align:center;font: bold 18px; color: #0066CC;vertical-align: middle"> 
访问失败，请确认你已经登录系统并且你访问的地址是正确的。<br />
<input type="button" value="点此重新登录系统" onclick="javascript:window.open('/${ctx}/login','_self')"/>
</div>
</body>
</html>
