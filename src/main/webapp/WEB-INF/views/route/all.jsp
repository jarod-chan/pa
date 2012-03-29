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
<h1>${title}</h1>
<c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:300px">${msg}</div>
</c:if>

<c:forEach var="item" items="${urls}">
<a href="${item.url}">${item.name}</a><br/><br/>
</c:forEach>

<br/>
<input type="button" value="退出"  onclick="javascript:window.open('/${ctx}/login','_self')"/><br>
</body>
</html>
