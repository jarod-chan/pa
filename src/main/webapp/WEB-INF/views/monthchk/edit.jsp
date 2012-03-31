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
	var taskMaxlength={"maxlength":"50"};
	var hourCss={"width":"40px"};
	
	var rowClick=function (){
		$("#tbl tr td:last-child :button").hide();
		$(this).find("td:last").find(":button").show();
		$("#tbl tr").removeClass("currRow");
		$(this).addClass("currRow");
	};
	
	var sel=$("<select>").attr({name:"monthChkItems_worktype"});
	<c:forEach var="workType" items="${workTypes}">
		sel.append("<option value='${workType.id}'>${workType.worktype}</option>");
	</c:forEach>

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
				.append(sel)
				.end()
			.append("<td>")
				.find("td:last")
				.append($("<input type='text' name='monthChkItems_task'/>").css(taskSize).attr(taskMaxlength))
				.end()
			.append("<td>")
				.find("td:last")
				.append($("<input type='text' name='monthChkItems_workhour'/>").css(hourCss))
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
		$(".add").css(leftcss).hide();
		$(".remove").add(".up").add(".down").css(leftAndMargin).hide();
		$("#tbl tr").click(rowClick);
		$(":input[name='monthChkItems_task']").css(taskSize).attr(taskMaxlength);
		$(":input[name='monthChkItems_workhour']").css(hourCss);
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
		var msg="提交以后，单据将交由经理审核，无法再次修改，确定提交？";
		if(confirm(msg)){
			$("#monthChk").attr("action",oldAction+"/commit").submit();
		}
	}
	
</script>  
<body>
<h2>员工月度工作任务提报</h2>
员工:${monthChk.person.name}&nbsp;&nbsp;部门:${monthChk.person.department}&nbsp;&nbsp;上级主管:${mange.name}<br>
考核周期:${monthChk.year}年${monthChk.month}月&nbsp;&nbsp;考核状态:${monthChk.state.name}
<input type="button" value="历史考核>>" onclick="javascript:window.open('/${ctx}/person/${monthChk.person.id}/monthchk/histroy','_self')"/>
<input type="button" value="修改密码>>" onclick="javascript:window.open('/${ctx}/common/settings/person/${monthChk.person.id}/password?backurl=/${ctx}/person/${monthChk.person.id}/monthchk','_self')"/>
<br>
<c:if test="${msg!=null}">
	<font id="msg" style="color:red;" >${msg}</font>
</c:if>
<br>
<form id="monthChk" action="/${ctx}/person/${monthChk.person.id}/monthchk" method="post">

<input name="year" type="hidden" value="${monthChk.year}" /> 
<input name="month" type="hidden" value="${monthChk.month}" /> 

<table border=1 style="table-layout:fixed;width:980px;">
<thead>
	<tr>
		<td style="width:50px;">序号</td>
		<td style="width:100px;">工作性质</td>
		<td style="width:600px;">工作内容<font style="color:red">[字数限制：50个]</font></td>
		<td style="width:80px;">用时<font style="color:red">[小时]</font></td>
		<td style="width:150px;">操作<input type="button" class="addLast" value="+"  /></td>
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
				<select name="monthChkItems_worktype">
				<c:forEach var="workType" items="${workTypes}">
					<option value="${workType.id}" <c:if test="${item.workType.id==workType.id}">selected="true"</c:if> >${workType.worktype}</option>
				</c:forEach>
				</select>
			</td>
			<td>
				<input type="text" name="monthChkItems_task" value="${item.task}" />
			</td>
			<td>
				<input type="text" name="monthChkItems_workhour" value="${item.workhour}" />
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
<input type="button" value="退出"  onclick="javascript:window.open('/${ctx}/login','_self')"/>
</form>

</body>
</html>
