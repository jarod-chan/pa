<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
	 });
</script>  
<body>
<h1>管理员页面</h1>
<c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:300px">${msg}</div>
</c:if>

<a href="/${ctx}/admin/person">用户管理</a><br>
<a href="/${ctx}/admin/rpt/point/asc">考核报表</a><br>
<a href="/${ctx}/admin/idrtype">指标类型</a><br>
<a href="/${ctx}/admin/idrtypeweight/edit/2010">年度指标类型权重</a><br>

<input type="button" value="退出"  onclick="javascript:window.open('/${ctx}/login','_self')"/><br>
</body>
</html>
