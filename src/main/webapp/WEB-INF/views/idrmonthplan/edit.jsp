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
	var contextWidth={"width":"580px"};
	var contextLen={"maxlength":"50"};
	
	var rowClick=function (){
		$(".tbldef tbody tr td:last-child :button").hide();
		$(this).find("td:last").find(":button").show();
		$(".tbldef tbody tr").removeClass("currRow");
		$(this).addClass("currRow");
	};
	
	var addLastRow=function(){
		var newtr=tr.clone();
		newtr.click(rowClick);
		$(".tbldef tbody").append(newtr); 
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
		.append($("<input type='text' name='idrTasks_context' />").css(contextWidth).attr(contextLen));
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
		$(":input[name='idrTasks_context']").css(contextWidth).attr(contextLen);
		$(".tbldef tbody tr").click(rowClick);
		$(".addLast").bind('click',addLastRow);
	});
	
	function add(obj) {
		var newtr=tr.clone();
		newtr.click(rowClick);
		$(obj).parent().parent().after(newtr);
		reIndexTable();
	}

	function remove(obj) {
		$(obj).parent().parent().remove();
		reIndexTable();
	}

	function up(obj) {
		var tr = $(obj).parent().parent();
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
	
	function save(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		$(".tbldef tbody").formatName();
		actionFrom.attr("action",oldAction+"/save").submit();
	}
	
	function sort(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		$(".tbldef tbody").formatName();
		actionFrom.attr("action",oldAction+"/sort").submit();
	}
	
	function commit(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		var msg="确定提交？";
		if(confirm(msg)){
			$(".tbldef tbody").formatName();
			actionFrom.attr("action",oldAction+"/commit").submit();
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
<form  action="/${ctx}/mange/${person.id}/idrmonthplan" method="post">

<input name="id" type="hidden" value="${idrMonthPlanBill.id}" /> 
<input name="year" type="hidden" value="${idrMonthPlanBill.year}" /> 
<input name="month" type="hidden" value="${idrMonthPlanBill.month}" /> 
<input name="department.id" type="hidden" value="${idrMonthPlanBill.department.id}" /> 

<table border=1 style="table-layout:fixed;width:800px;" class="tbldef">
<thead>
	<tr>
		<td style="width:50px;">序号</td>
		<td style="width:600px;">工作计划<font style="color:red">[字数限制：50个]</font></td>
		<td style="width:150px;">操作<input type="button" class="addLast" value="+" /></td>
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
				<input type="text" name="idrTasks_context" value="${item.context}" />
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
<input type="button" value="保存" onclick="save()"/>
<input type="button" value="提交分管副总" onclick="commit()"/>
</form>

</body>
</html>
