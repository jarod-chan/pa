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
		$("#tbl tr").removeClass("currRow");
		$(this).addClass("currRow");
	};
	
	$(document).ready(function() {
		setTimeout(function() {
			$("#msg").slideToggle(1000);
		}, 3000);
		$("#tbl tr").click(rowClick);
	});
	
</script>  
<body>
员工${monthChk.person.name}${monthChk.year}年${monthChk.month}月份工完成情况【${monthChk.state.name}】
<c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:300px">${msg}</div>
</c:if>

<table border=1 style="table-layout:fixed;width:800px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:600px;">工作内容</th>
		<th style="width:150px;"></th>
	</tr>
</thead>
<tbody id="tbl">
	<c:forEach var="item" items="${monthChk.monthChkItems}">
		<tr>
			<td>
				${item.sn}
			</td>
			<td>
				${item.task}
			</td>
			<td>
				
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
 
 <input type="button" value="退出" " onclick="javascript:window.open('../','_self')"/>

</body>
</html>
