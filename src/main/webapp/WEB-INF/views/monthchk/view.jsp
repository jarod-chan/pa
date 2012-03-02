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
<h2>员工月度工作任务提报</h2>
员工:${monthChk.person.name}&nbsp;&nbsp;部门:${monthChk.person.department}&nbsp;&nbsp;上级主管:${mange.name}<br>
考核周期:${monthChk.year}年${monthChk.month}月&nbsp;&nbsp;考核状态:${monthChk.state.name}
<input type="button" value="历史考核>>" onclick="javascript:window.open('/${ctx}/person/${monthChk.person.id}/monthchk/histroy','_self')"/>
<br>
<c:if test="${msg!=null}">
	<font id="msg" style="color:red;" >${msg}</font>
</c:if>
<br>

<table border=1 style="table-layout:fixed;width:950px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:100px;">工作性质</th>
		<th style="width:600px;">工作内容</th>
		<td style="width:50px;">用时</td>
		<th style="width:150px;">评价</th>
	</tr>
</thead>
<tbody id="tbl">
	<c:forEach var="item" items="${monthChk.monthChkItems}">
		<tr>
			<td>
				${item.sn}
			</td>
			<td>
				${item.workType.worktype}
			</td>
			<td>
				${item.task}
			</td>
			<td>
				${item.workhour}
			</td>
			<td>
			<c:if test="${monthChk.state=='FINISHED'}">
			<c:choose>
     			<c:when test="${item.point=='5'}">优秀</c:when>
     			<c:when test="${item.point=='4'}">良好</c:when>
     			<c:when test="${item.point=='3'}">尽职</c:when>
     			<c:when test="${item.point=='2'}">需改进</c:when>
     			<c:when test="${item.point=='1'}">差</c:when>
			</c:choose>
			</c:if>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
 
<input type="button" value="退出"  onclick="javascript:window.open('/${ctx}/login','_self')"/>

</body>
</html>
