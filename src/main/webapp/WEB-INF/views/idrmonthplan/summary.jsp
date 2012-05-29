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
	
	var leftcss={"float":"left","width":"22px"};
	var leftAndMargin={"float":"left","margin-left":"10px","width":"22px"};
	
	//原始计划条数
	var contextSize=${idrMonthPlanBill.contextSize};
	
	var summaryCss={"width":"530","vertical-align":"top"};
	var summaryLength={"maxlength":"500"};
	var summaryOption={minHeight:60,extraSpace:3};
	
	var rowClick=function (){
		$(".tbldef tbody tr td:last-child :button:not(.addLast)").hide();
		$(this).find("td:last").find(":button").show();
		$(".tbldef tbody tr").removeClass("currRow");
		$(this).addClass("currRow");
	};
	
	var addLastRow=function(){
		var newtr=tr.clone();
		newtr.click(rowClick);
		$(".tbldef tbody").append(newtr); 
		$(newtr).find("textarea[name='idrTasks_summary']").css(summaryCss).attr(summaryLength).autoResize(summaryOption).iemaxlength();
		reIndexTable();
	}
	
	
	var tr = $("<tr>");
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='idrTasks_id' />"))
		.append($("<input type='text' name='idrTasks_sn' />"))
		.css("display","none");
	tr.append("<td>");
	tr.append("<td>")
		.find("td:last")
		.append(
			$("<div>")
				.append($("<div>")).find("div:last").append("总结：").append($("<textarea name='idrTasks_summary'></textarea>"))
			.end()
		);
	tr.append("<td>")
		.find("td:last")
		.append(
				$("<input type='button' class='add' onclick='add(this)' value='+'   />").css(leftcss).hide())
		.append(
				$("<input type='button' class='remove' onclick='remove(this)' value='-'   />").css(leftAndMargin).hide())
		.append(
				$("<input type='button' class='up' onclick='up(this)' value='/\\'  />").css(leftAndMargin).hide())
		.append(
				$("<input type='button' class='down' onclick='down(this)' value='\\/'  />").css(leftAndMargin).hide())
		.append("&nbsp;"); 
	
	$(document).ready(function() { 
		$(".add").css(leftcss).hide();
		$(".remove").add(".up").add(".down").css(leftAndMargin).hide();
		$(".tbldef tbody tr").click(rowClick);
		$("textarea[name='idrTasks_summary']").css(summaryCss).attr(summaryLength).autoResize(summaryOption).iemaxlength();
		$(".tbldef tbody tr").find("td:last").slice(0,contextSize).each(function(index){
			if(index+1==contextSize){
				$(this).html("<input type='button' class='addLast' value='+' />");
			}
			else
				$(this).html("&nbsp;");
		})
		$(".addLast").bind('click',addLastRow);
	});
	
	function add(obj) {
		var newtr=tr.clone();
		newtr.click(rowClick);
		$(obj).parent().parent().after(newtr);
		$(newtr).find("textarea[name='idrTasks_summary']").css(summaryCss).attr(summaryLength).autoResize(summaryOption).iemaxlength();
		reIndexTable();
	}

	function remove(obj) {
		$(obj).parent().parent().remove();
		reIndexTable();
	}

	function up(obj) {
		var tr = $(obj).parent().parent();
		//不能让新增的总结行移动到计划总结之前
		if(tr[0].rowIndex-1==contextSize) return;
		tr.prev().before(tr);
		reIndexTable();
	}

	function down(obj) {
		var tr = $(obj).parent().parent();
		tr.next().after(tr);
		reIndexTable();
	}
	
	function reIndexTable(){
		var index=1;
		$(".tbldef tbody").find("tr").each(function(){
			$(this).find("td").eq(0).find("[name='idrTasks_sn']").val(index);
			$(this).find("td").eq(1).html(index);
			index++;
		});
	}
	
	function summary(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		$(".tbldef tbody").formatName();
		actionFrom.attr("action",oldAction+"/summary").submit();
	}
	
	
	function finish(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		var msg="工作计划执行完成，流程将进入下个月，确定完成？";
		if(confirm(msg)){
			$(".tbldef tbody").formatName();
			actionFrom.attr("action",oldAction+"/finish").submit();
		}
	}


	
</script>  
</head>
<c:set target="${pagefunc}" property="name" value="部门工作执行" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/mange/${person.id}/idrmonthplan" />  

<c:set var="pagesize" value="820" scope="request"/>
<body>
<%@ include file="personinfo.jsp"%>
<%@ include file="../common/message.jsp"%>
<form  action="/${ctx}/mange/${person.id}/idrmonthplan/${idrMonthPlanBill.id}" method="post">

<input name="id" type="hidden" value="${idrMonthPlanBill.id}" /> 
<input name="year" type="hidden" value="${idrMonthPlanBill.year}" /> 
<input name="month" type="hidden" value="${idrMonthPlanBill.month}" /> 
<input name="department.id" type="hidden" value="${idrMonthPlanBill.department.id}" /> 

<table border=1 style="table-layout:fixed;width:800px;" class="tbldef">
<thead>
	<tr>
		<td style="width:50px;">序号</td>
		<td style="width:600px;">工作总结<font style="color:red">[字数限制：500个]</font></td>
		<td style="width:150px;">操作</td>
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
				<div>
					<c:if test="${item.context!=null}">
						<div>计划：${item.context}</div>
					</c:if>
					<div>总结：<textarea name="idrTasks_summary" >${item.summary}</textarea></div>  
				</div>
			</td>
			<td>
				<input type="button" class="add" onclick='add(this)' value="+"  />
				<input type="button" class="remove" onclick='remove(this)' value="-"  />
				<input type="button" class="up" onclick='up(this)' value="/\" />
				<input type="button" class="down" onclick='down(this)' value="\/" />
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>

<br/>
<input type="button" value="保存" onclick="summary()"/>
<input type="button" value="完成工作总结" onclick="finish()"/>

</form>

</body>
</html>
