<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
		
		//绑定增删改查
	//	alert($(":button[value='+']").size());
		
		var tr=$("<tr>")
			.append("<td>").find("td:last")
				.append("<input type='text' name='monthChkItems_id' />")
				.append("<input type='text' name='monthChkItems_sn' />")
				.end()
			.append("<td>")
	 		.append("<td>").find("td:last")
	 			.append("<input type='text' name='monthChkItems_task'/>")
	 			.end()
	 		.append("<td>").find("td:last")
	 			.append("<input type='button' value='+'   />")
				.append("<input type='button' value='-'   />")
				.append("<input type='button' value='/\\'  />")
				.append("<input type='button' value='\\/'  />")
				.end();
	 		
		
		$(":button").filter("[value='+']").each(function(){
			$(this).bind('click',function(){
				$(this).parent().parent().after(tr.clone());
			});
		});
 		$(":button").filter("[value='+']").each(function(){
			$(this).bind('click',function(){
				var tr=$(this).parent().parent().remove();
				$("#tbl").remove(tr);
			});
		}); 
		
	 });
	
	
	
</script>  
<body>
员工${monthChk.person.name}${currMonth}月份工完成情况

<form id="monthChk" action="/${ctx}/person/${monthChk.person.id}/monthchk" method="post">

<input name="year" type="hidden" value="${monthChk.year}" /> 
<input name="month" type="hidden" value="${monthChk.month}" /> 

<table border=1 >
<thead>
	<tr>
		<th style="width:20px;">id</th>
		<th style="width:20px;">序号</th>
		<th style="width:600px;">工作内容</th>
		<th style="width:180px;">操作</th>
	</tr>
</thead>
<tbody id="tbl">
	<c:forEach var="item" items="${monthChk.monthChkItems}">
		<tr>
			<td>
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
				<input type="button" value="+"  /> 
				<input type="button" value="-"  /> 
				<input type="button" value="/\" />
				<input type="button" value="\/" />
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>


<input type="submit" value="保存"/>
<input type="button" value="提交" onclick="javascript:window.open('/pa/person','_self')"/>
</form>

</body>
</html>
