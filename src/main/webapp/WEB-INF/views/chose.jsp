<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
<link type="text/css" rel="stylesheet" href="/${ctx}/resources/css/mainbar.css" /> 
<style type="text/css">

#goques {
	display: block;
	border: 2px solid #6666FF;
	top: 100px;
	background-color:  #FFFFFF;
	left: 300px; 
	width: 400px;
	height:200px;
	position: absolute;
}

</style>
<script type="text/javascript">

$(function(){
	$("#btn_qs").bind("click",function(){
		window.open('/pa/qs/login','_self');
	});
	$("#btn_pa").bind("click",function(){
		window.open('/pa/first?pass=true','_self');
	});
});
</script>
</head>
<body >


<div id="goques">
	<div style="margin:5px;">
		<div style="text-align: center;border-bottom:  2px solid #6666FF;">
			<h1>通知</h1>
		</div>
		<div style="margin: 5px 0px 5px 0px;">
			&nbsp;&nbsp;&nbsp;&nbsp;如果你想参与满意度调查，请点击【满意度调查】按钮。如果你已完成满意度调查，你可以点击【绩效考核系统】按钮直接进入绩效考核页面。
		</div>
	</div>
	<div style="margin:10px 5px 0px 5px; text-align: center;">
	<input id="btn_qs" style="height: 50px;width: 200px;font-size: 25px;" type="button" value="满意度调查" />&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="btn_pa" type="button" value="绩效考核系统" />
	</div>
</div>

</body>
</html>
