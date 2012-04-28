<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>
<c:set var="pagefunc" value="年终员工考核" scope="request"/> 
<c:set var="pagetitle" value="年终员工考核列表" scope="request"/> 
<c:set var="pagesize" value="825" scope="request"/> 
<body>

<div class="headdiv" >
<div class="headleft"  >
</div>
<div class="headright" >
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
