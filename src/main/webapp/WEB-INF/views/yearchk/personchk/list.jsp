<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>
<script type="text/javascript">
	function saveAllChecks(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		actionFrom.attr("action",oldAction+"/saveAllChecks").submit();
	}
	
	function commitAllChecks(){
		if(confirm("提交以后，你将无法再对评价结果进行修改，确定提交？")){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/commitAllChecks").submit();
		}
	}
</script>
</head>
<c:set target="${pagefunc}" property="name" value="年终员工考核" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/person/${person.id}/yearchk" />  


<c:set var="pagesize" value="825" scope="request"/> 
<body>

<div class="headdiv" >
<div class="headleft"  >
考核年份:${pageBean.year}&nbsp;&nbsp;状态:<c:choose><c:when test="${pageBean.commit==true}">已提交</c:when><c:when test="${pageBean.finish==true}">已完成</c:when><c:otherwise>未完成</c:otherwise></c:choose>
</div>
<div class="headright" >
	<c:if test="${not pageBean.commit}">
		<input type="button" value="对所有未评价人员平分评价" onclick="saveAllChecks()"/>
		<input type="button" value="提交评价结果" onclick="commitAllChecks()"/>
	</c:if>
</div>
<div  class="headnone"></div>
</div>
<form action="/${ctx}/person/${person.id}/yearchk" method="post">
<input name="year" type="hidden" value="${pageBean.year}"/>
</form>
<%@ include file="../../common/message.jsp"%>
<table border=1 style="table-layout:fixed;width:800px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:150px;">员工</th>
		<th style="width:500px;">评价结果</th>
		<th style="width:100px;">操作</th>
	</tr>
</thead>
<tbody>
<% int num=0;%>
<c:forEach var="item" items="${pageBean.departmentChkBeans}"  varStatus="status">
	<tr>
		<td colspan="4">
			部门:${item.department}
		</td>
	</tr>
	<c:forEach var="personResult" items="${item.personChkBeans}">
	<% num++;%>
	<tr>
		<td><% out.print(num); %></td>
		<td>${personResult.name}</td>
		<td>胜${personResult.win}&nbsp;负${personResult.lose}&nbsp;平${personResult.draw}&nbsp;</td>
		<td>
		<c:if test="${not pageBean.commit}">
			<input type="button" value="评价" onclick="javascript:window.open('/${ctx}/person/${person.id}/yearchk/personchk/${personResult.id}','_self')"/>
		</c:if>
		</td>
	</tr>
	</c:forEach>

</c:forEach>
</tbody>
</table>
</body>
</html>
