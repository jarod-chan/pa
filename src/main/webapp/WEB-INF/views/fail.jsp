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
Sorry,您没有权限访问该资源!
</div>
</body>
</html>
