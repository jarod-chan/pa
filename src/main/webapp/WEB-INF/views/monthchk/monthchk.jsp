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

	
	var leftcss={"float":"left","width":"22px"};
	var leftAndMargin={"float":"left","margin-left":"10px","width":"22px"};
	var taskSize={"width":"580px"};
	var taskMaxlength={"maxlength":"40"};
	
	var rowClick=function (){
		$("#tbl tr td:last-child :button").hide();
		$(this).find("td:last").find(":button").show();
		$("#tbl tr").removeClass("currRow");
		$(this).addClass("currRow");
	};

	var tr = $("<tr>")
			.append("<td>")
			.find("td:last")
			.append("<input type='text' name='monthChkItems_id' />")
			.append("<input type='text' name='monthChkItems_sn' />")
			.css("display","none")
			.end()
			.append("<td>")
			.append("<td>")
			.find("td:last")
			.append($("<input type='text' name='monthChkItems_task'/>").css(taskSize).attr(taskMaxlength))
			.end()
			.append("<td>")
			.find("td:last")
			.append(
					$("<input type='button' class='add' onclick='add(this)' value='+'   />").css(leftcss).hide())
			.append(
					$("<input type='button' class='remove' onclick='remove(this)' value='-'   />").css(leftAndMargin).hide())
			.append(
					$("<input type='button' class='up' onclick='up(this)' value='/\\'  />").css(leftAndMargin).hide())
			.append(
					$("<input type='button' class='down' onclick='down(this)' value='\\/'  />").css(leftAndMargin).hide())
			.append("&nbsp;")
			.end();
	
	$(document).ready(function() {
		setTimeout(function() {
			$("#msg").slideToggle(1000);
		}, 3000);
		$(".add").css(leftcss).hide();
		$(".remove").add(".up").add(".down").css(leftAndMargin).hide();
		$("#tbl tr").click(rowClick);
		$(":input[name='monthChkItems_task']").css(taskSize).attr(taskMaxlength);
		$(".addLast").click(function(){
 			var newtr=tr.clone();
			newtr.click(rowClick);
			$("#tbl").append(newtr); 
			reIndexTable();
		})
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
		$("#tbl").find("tr").each(function(){
			$(this).find("td").eq(0).find("[name='monthChkItems_sn']").val(index);
			$(this).find("td").eq(1).html(index);
			index++;
		});
	}
	
	function save(){
		var oldAction=$("#monthChk").attr("action");
		$("#monthChk").attr("action",oldAction+"/save").submit();
	}
	
	function commit(){
		var oldAction=$("#monthChk").attr("action");
		var msg="";
		if(confirm())
	}
	
</script>  
<body>
员工${monthChk.person.name}${currMonth}月份工完成情况

<form id="monthChk" action="/${ctx}/person/${monthChk.person.id}/monthchk" method="post">

<input name="year" type="hidden" value="${monthChk.year}" /> 
<input name="month" type="hidden" value="${monthChk.month}" /> 

<table border=1 style="table-layout:fixed;width:800px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:600px;">工作内容<font style="color:red">[字数限制：40个]</font></th>
		<th style="width:150px;">操作<input type="button" class="addLast" value="+"  /></th>
	</tr>
</thead>
<tbody id="tbl">
	<c:forEach var="item" items="${monthChk.monthChkItems}">
		<tr>
			<td style="display:none">
				<input type="text" name="monthChkItems_id"   value="${item.id}" />
				<input type="text" name="monthChkItems_sn"   value="${item.sn}" />
			</td>
			<td>
				${item.sn}
			</td>
			<td>
				<input type="text" name="monthChkItems_task" value="${item.task}" />
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


<input type="button" value="保存" onclick="save()"/>
<input type="button" value="提交" onclick="commit()"/>
</form>

</body>
</html>
