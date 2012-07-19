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
	
	var rowClick=function (){
		$(".tbldef tbody tr td:last-child :button").hide();
		$(this).find("td:last").find(":button").show();
		$(".tbldef tbody tr").removeClass("currRow");
		$(this).addClass("currRow");
	};
	
	var numberBlur=function(){
		if(!IsFloat($(this).val()))	{
			$(this).val("");
		} 
	};

	var sel=$("<select>").attr({name:"idrTypeWeight_idrType.id"});
	<c:forEach var="idrType" items="${idrTypes}">
		sel.append("<option value='${idrType.id}'>${idrType.name}</option>");
	</c:forEach>

	var tr = $("<tr>")
	.append("<td>")
		.find("td:last")
		.append("<input type='text' name='idrTypeWeight_id' />")
		.append("<input type='text' name='idrTypeWeight_sn' />")
		.css("display","none")
		.end()
	.append("<td>")
	.append("<td>")
		.find("td:last")
		.append(sel)
		.end()
	.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='IdrTypeWeight_weight'/>")).bind("blur",numberBlur)
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
		$(".tbldef tbody tr").click(rowClick);

		$(":input[name='IdrTypeWeight_weight']").bind("blur",numberBlur);
		
		$(".addLast").click(function(){
 			var newtr=tr.clone();
			newtr.click(rowClick);
			newtr.find(":input[name='IdrTypeWeight_weight']").bind("blur",numberBlur);
			$(".tbldef tbody").append(newtr); 
			reIndexTable();
		})
	});
	
	function add(obj) {
		var newtr=tr.clone();
		newtr.click(rowClick);
		newtr.find(":input[name='IdrTypeWeight_weight']").bind("blur",numberBlur);
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
			$(this).find("td").eq(0).find("[name='idrTypeWeight_sn']").val(index);
			$(this).find("td").eq(1).html(index);
			index++;
		});
	}
	
	function save(){
		var actionFrom=$("#typeweight");
		var oldAction=actionFrom.attr("action");
		$(".tbldef tbody").formatName();
		actionFrom.attr("action",oldAction+"/save").submit();
	}
	
	function commit(){
		var oldAction=$("#typeweight").attr("action");
		var msg="确定提交？";
		if(confirm(msg)){
			$(".tbldef tbody").formatName();
			$("#typeweight").attr("action",oldAction+"/commit").submit();
		}
	}
</script>
</head>
<body>
<h2>类别权重</h2>
年度：${idrYearTypeWeight.year}
<%@ include file="../common/message.jsp"%>
<form id="typeweight" action="/${ctx}/admin/idrtypeweight" method="post">
<input type="hidden" name="year"  value="${idrYearTypeWeight.year}" />
<table border="1" class="tbldef">
		<thead>
			<tr>
				<th style="display:none">none</th>
				<th style="width:100px;">序号</th>
				<th style="width:100px;">类别</th>
				<th >权重<font style="color:red">[一位小数]</font></th>
				<th style="width:150px;">操作<input type="button" class="addLast" value="+"  /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${idrYearTypeWeight.idrTypeWeight}">
			<tr>
				<td  style="display:none">
					<input type="text" name="idrTypeWeight_id"   value="${item.id}" />
					<input type="text" name="idrTypeWeight_sn"   value="${item.sn}" />
				</td>
				<td>
					${item.sn}
				</td>
				<td>
					<select name="idrTypeWeight_idrType.id">
					<c:forEach var="idrType" items="${idrTypes}">
						<option value="${idrType.id}" <c:if test="${idrType.id==item.idrType.id}">selected="true"</c:if> >${idrType.name}</option>
					</c:forEach>
					</select>
				</td>
				<td>
					<input type="text" name="IdrTypeWeight_weight"   value="${item.weight}" />
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
<br>
<input type="button" value="保存" onclick="save()"/>
<input type="button" value="提交" onclick="commit()"/>
<input type="button" onclick="window.open('/pa/admin/all','_self');" value="返回管理员页面<<">
</form>
</body>
</html>
