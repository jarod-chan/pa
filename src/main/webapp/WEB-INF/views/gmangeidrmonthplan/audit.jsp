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
</head>
<c:set var="pagefunc" value="部门月度工作任务执行" scope="request"/> 
<c:set var="pagetitle" value="部门月度工作计划审核" scope="request"/> 
<c:set var="pagesize" value="670" scope="request"/>
<body>

<div class="headdiv" >
<div class="headleft" >
	部门:${idrMonthPlanBill.department.name}&nbsp;&nbsp;计划周期:${idrMonthPlanBill.year}年${idrMonthPlanBill.month}月&nbsp;&nbsp;状态:${idrMonthPlanBill.state.name}
</div>
<div class="headright"  >
</div>
<div  class="headnone"></div>
</div>

<%@ include file="../common/message.jsp"%>
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

<br/>
<input type="button" value="打回" onclick="back()"/>
<input type="button" value="通过" onclick="next()"/>
<input type="button" value="返回"  onclick="javascript:window.open('/${ctx}/gmange/${person.id}/idrmonthplan','_self')"/>
</form>

</body>
</html>
