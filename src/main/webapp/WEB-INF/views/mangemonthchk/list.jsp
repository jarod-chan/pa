<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>
<c:set var="pagefunc" value="员工工作评价" scope="request"/> 
<c:set var="pagetitle" value="员工月度工作任务" scope="request"/> 
<c:set var="pagesize" value="825" scope="request"/> 
<body>

<div class="headdiv" >
<div class="headleft"  >
	部门:${mange.department}&nbsp;&nbsp;
</div>
<div class="headright" >
	<input type="button" value="历史考核>>" onclick="javascript:window.open('/${ctx}/mange/${mange.id}/monthchk/histroy','_self')"/>
	<input type="button" value="修改密码>>" onclick="javascript:window.open('/${ctx}/common/settings/person/${mange.id}/password?backurl=/pa/mange/${mange.id}/monthchk','_self')"/>
</div>
<div  class="headnone"></div>
</div>

<%@ include file="../common/message.jsp"%>
<table border=1 style="table-layout:fixed;width:800px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:500px;">工作完成情况表</th>
		<th style="width:150px;">员工</th>
		<th style="width:100px;">操作</th>
	</tr>
</thead>
<tbody>
<c:forEach var="item" items="${monthChks}"  varStatus="status">
	<tr>
	<td>
		${status.count}
	</td>
	<td>
		${item.year}年${item.month}月份${item.person.name}工完成情况【${item.state.name}】
	</td>
	<td>
		${item.person.name}
	</td>
	<td>
		<input type="button" value="评价" onclick="javascript:window.open('/${ctx}/mange/${mange.id}/monthchk/${item.id}','_self')"/>
	</td>
				
   </tr>
</c:forEach>
</tbody>
</table>
</body>
</html>
