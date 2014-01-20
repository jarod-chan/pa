<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<style type="text/css">
	.currRow{
		background-color:#77BBFF;
	}
</style>


<script type="text/javascript">
var rowClick=function (){
	$(".tbldef tbody tr").removeClass("currRow");
	$(this).addClass("currRow");
};
$(document).ready(function() { 
	$(".tbldef tbody tr").click(rowClick);
});
</script>  
</head>
<c:set target="${pagefunc}" property="name" value="部门月度计划" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/mange/${person.id}/idrmonthplan" />  

<c:set var="pagesize" value="670" scope="request"/> 
<body>

<%@ include file="personinfo.jsp"%>
<%@ include file="../common/message.jsp"%>
<table border=1 style="table-layout:fixed;width:650px;" class="tbldef">
<thead>
	<tr>
		<td style="width:50px;">序号</td>
		<td style="width:600px;">工作计划</td>
	</tr>
</thead>
<tbody id="tbl">
	<c:forEach var="item" items="${idrMonthPlanBill.idrTasks}">
		<tr>
			<td style="display:none">
				<input type="text" name="idrTasks_id"   value="${item.id}" />
				<input type="text" name="idrTasks_sn"   value="${item.sn}" />
			</td>
			<td>
				${item.sn}
			</td>
			<td>
				${item.context}
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>

<br/>


</body>
</html>
