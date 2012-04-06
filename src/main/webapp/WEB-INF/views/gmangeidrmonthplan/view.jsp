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

	var summaryCss={"width":"530","vertical-align":"top"};
	var summaryLength={"maxlength":"500"};
	var summaryOption={minHeight:60,extraSpace:3};
	
	var rowClick=function (){
		$(".tbldef tbody tr").removeClass("currRow");
		$(this).addClass("currRow");
	};

	
	$(document).ready(function() { 
		$(".tbldef tbody tr").click(rowClick);
		$("textarea[name='idrTasks_summary']").css(summaryCss).attr(summaryLength).autoResize(summaryOption);
		//解决ie下默认高度错误
		if($.browser.msie) { 
			$("textarea[name='idrTasks_summary']").each(function(){
				$(this).append(" ").triggerHandler("change");
			})
		}
	});
	
</script>  
<body>
<h2>部门月度工作计划【查看】</h2>
部门经理:${mange.name}&nbsp;&nbsp;部门:${idrMonthPlanBill.department.name}&nbsp;&nbsp;<br>
计划周期:${idrMonthPlanBill.year}年${idrMonthPlanBill.month}月&nbsp;&nbsp;状态:${idrMonthPlanBill.state.name}
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
			<td>
				${item.sn}
			</td>
			<td>
				<div>
					<c:if test="${item.context!=null}">
						<div>计划：${item.context}</div>
					</c:if>
					<div>总结：<textarea style="border-style:none;background:transparent;"  readonly="readonly" name="idrTasks_summary">${item.summary}</textarea></div>  
				</div>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>

<input type="button" value="返回"  onclick="javascript:window.open('/${ctx}/gmange/${person.id}/idrmonthplan','_self')"/>

</body>
</html>
