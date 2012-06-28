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
	var contextSize={"width":"500px"};
	var contextlength={"maxlength":"50"};
	
	var tr = $("<tr>");
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='deptKpiItems_id'   value='' />"))
		.append($("<input type='text' name='deptKpiItems_sn'   value='' />"))
		.css("display","none");
	
	tr.append("<td>");
	
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='deptKpiItems_context' />").css(contextSize).attr(contextlength));
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='deptKpiItems_point'   value='${item.point}' size='10' />"));
		
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
		$(".tbldef tbody tr").bind("click",rowClick);
		$(":input[name='deptKpiItems_context']").css(contextSize).attr(contextlength);
		$(".addLast").bind("click",addLastRow);
		$(":input[name='deptKpiItems_point']").bind("blur",numberBlur);
	});
	
	var rowClick=function (){
		$(".tbldef tbody tr td:last-child :button").hide();
		$(this).find("td:last").find(":button").show();
		$(".tbldef tbody tr").removeClass("currRow");
		$(this).addClass("currRow");
	};
	
	var addLastRow=function(){
		var tbody=$(".tbldef tbody");
		tbody.append(cloneTR()); 
		reIndexTable();
	};
	
	function cloneTR(){
		var newtr=tr.clone();
		newtr.click(rowClick);
		newtr.find(":input[name='deptKpiItems_point']").bind("blur",numberBlur);
		return newtr;
	}
	
	function add(obj) {
		var tr = $(obj).parents("tr");
		tr.after(cloneTR()); 
		reIndexTable();
	}

	function remove(obj) {
		$(obj).parents("tr").remove();
		reIndexTable();
	}

	function up(obj) {
		var tr = $(obj).parents("tr");
		tr.prev().before(tr);
		reIndexTable();
	}

	function down(obj) {
		var tr =  $(obj).parents("tr");
		tr.next().after(tr);
		reIndexTable();
	}
	
	function reIndexTable(){
		var index=1;
		$(".tbldef tbody").find("tr").each(function(){
			$(this).find("td").eq(0).find("[name='deptKpiItems_sn']").val(index);			
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
	
	var numberBlur=function(){
		if(IsFloat($(this).val(),"+"))	{
			var point=hold($(this).val(),0);
			$(this).val(point);
		}else{
			$(this).val("");
		}
	};
	
</script>
</head>

<body>
<h2>部门公司KPI项目</h2>
<br/>
<a href="/${ctx}/admin/deptkpi/${year}">部门KPI列表</a>
&gt;<a href="/${ctx}/admin/deptkpi/${year}/department/${department.id}">${department.name}KPI分解列表</a>
&gt;<a href="/${ctx}/admin/deptkpi/${year}/department/${department.id}/idrcompany/${idrCompany.id}">${department.name}KPI分解</a>
<br/>
<br/>
年度：${year}&nbsp;&nbsp;部门:${department.name}<br/>
公司KPI指标:${idrCompany.context}
<%@ include file="../common/message.jsp"%>

<form action="/${ctx}/admin/deptkpi/${year}/department/${department.id}/idrcompany/${idrCompany.id}" method="post">

<table border="1" class="tbldef" >
		<thead>
			<tr>
				<th style="display:none">none</th>
				<th style="width:100px;">序号</th>
				<th style="width:550px;" >部门指标内容</th>
				<th style="width:100px;" >分值<font style="color:red">[整数]</font></th>
				<th style="width:150px;white-space:nowrap;overflow:hidden;">操作<input type="button" class="addLast" value="+"  /></th>
			</tr>
		</thead>
		<tbody>
	  		 <c:forEach var="item" items="${deptKpiItems}">
			<tr>
				<td style="display:none">
					<input type="text" name="deptKpiItems_id"   value="${item.id}" />
					<input type="text" name="deptKpiItems_sn"   value="${item.sn}" />
					<input type="text" name="deptKpiItemsKey"   value="${item.id}" />
				</td>
				<td>
					${item.sn}
				</td>
				<td>
					<input type="text" name="deptKpiItems_context"   value="${item.context}" />
				</td>
				<td>
					<input type="text" name="deptKpiItems_point"   value="${item.point}" size="10"/>
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
</form>

<br/>
<input type="button" value="保存" onclick="save()"/>
</form>
</body>
</html>
