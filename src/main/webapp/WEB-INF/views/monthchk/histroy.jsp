<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
$(function() {
	$("#queryform select").bind("change",function(){
		$("#queryform").submit();
	});
});
</script>
</head> 

<c:set target="${pagefunc}" property="name" value="月度工作历史" /> 
<c:set target="${pagefunc}" property="url" value="/${ctx}/monthsmy/histroy" /> 
<c:set var="pagesize" value="800" scope="request"/> 

<body>

<div class="headdiv" >
<div class="headleft" >
	<form id="queryform" action="/${ctx}/monthsmy/histroy" method="post">
	年份：
	<select name="year">
		<c:forEach var="item" items="${dateTool.allYears}">
			<option value="${item}" <c:if test="${item==monthsmy_histroy.year}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	</select>
	</form>
</div>
<div class="headright">
</div>
<div  class="headnone"></div>
</div>


<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/monthchktable.jsp"%>
</body>
</html>
