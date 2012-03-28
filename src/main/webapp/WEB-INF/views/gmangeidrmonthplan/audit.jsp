<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
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
	
	function back(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		$(".tbldef tbody").formatName();
		actionFrom.attr("action",oldAction+"/back").submit();
	}
	
	function next(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		var msg="确定通过该工作计划？";
		if(confirm(msg)){
			$(".tbldef tbody").formatName();
			actionFrom.attr("action",oldAction+"/next").submit();
		}
	}


	
</script>  
<body>
<h2>部门月度工作计划【审核】</h2>
部门经理:${mange.name}&nbsp;&nbsp;部门:${idrMonthPlanBill.department.name}&nbsp;&nbsp;<br>
计划周期:${idrMonthPlanBill.year}年${idrMonthPlanBill.month}月&nbsp;&nbsp;状态:${idrMonthPlanBill.state.name}
<br/>
<c:if test="${message!=null}">
	<font id="msg" style="color:red;" >${message}</font>
</c:if>
<br/>
<form  action="/${ctx}/gmange/${person.id}/idrmonthplan/${idrMonthPlanBill.id}" method="post">

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


<input type="button" value="打回" onclick="back()"/>
<input type="button" value="通过" onclick="next()"/>
<input type="button" value="返回"  onclick="javascript:window.open('/${ctx}/gmange/${person.id}/idrmonthplan','_self')"/>
</form>

</body>
</html>
