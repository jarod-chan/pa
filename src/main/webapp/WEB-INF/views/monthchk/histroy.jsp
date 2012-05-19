<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>

<c:set target="${pagefunc}" property="name" value="月度工作任务" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/person/${person.id}/monthchk" />  

<c:set target="${pagetitle}" property="name" value="员工月度工作任务完成历史" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/person/${person.id}/monthchk/histroy" /> 
<c:set var="pagesize" value="800" scope="request"/> 
<body>
<form action="" method="get">
<div class="headdiv" >
<div class="headleft" >
年份：
<select name="year">
	<c:forEach var="item" items="${dateTool.allYears}">
		<option value="${item}" <c:if test="${item==queryBean.year}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
&nbsp;&nbsp;
<input type="submit" value="查询" />
</div>
<div class="headright">
<input type="button" value="<<返回" onclick="javascript:window.open('/${ctx}/person/${person.id}/monthchk','_self')"/>
</div>
<div  class="headnone"></div>
</div>
</form>

<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/monthchktable.jsp"%>
</body>
</html>
