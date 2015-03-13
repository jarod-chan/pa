<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

<script type="text/javascript">
	var selChange=function(){
		$('<form/>',{action:'/${ctx}/monthsmy/manage',method:'post'})
		.append($('<input/>',{type:'hidden',name:'year',value:$("select[name=year]").val()}))
		.append($('<input/>',{type:'hidden',name:'month',value:$("select[name=month]").val()}))
	 	.appendTo($("body"))
	 	.submit();
	}
	
	$(document).ready(function(){
		$("select[name=year],select[name=month]").bind("change",selChange);
	})
	
</script>
</head>

<c:set target="${pagefunc}" property="name" value="月度小结评价" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/monthsmy/manage" />  

<c:set var="pagesize" value="825" scope="request"/> 
<body>

<div class="headdiv" >
<div class="headleft"  >
	部门:${mange.department}&nbsp;&nbsp;
	考核周期:
	<select name="year">
		<c:forEach var="item" items="${dateTool.allYears}">
			<option value="${item}" <c:if test="${item==monthplan_manage.year}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	</select>年
	<select name="month">
		<c:forEach var="item" items="${dateTool.allMonths}">
			<option value="${item}" <c:if test="${item==monthplan_manage.month}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	</select>月
</div>
<div class="headright" >
</div>
<div  class="headnone"></div>
</div>

<%@ include file="../../common/message.jsp"%>
<table border=1 style="table-layout:fixed;width:800px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:450px;">工作完成情况表</th>
		<th style="width:100px;">员工</th>
		<th style="width:100px;">状态</th>
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
		${item.year}年${item.month}月份${item.person.name}工作完成情况
	</td>
	<td>
		${item.person.name}
	</td>
	<td>
		【${item.state.name}】
	</td>
	<td>
		<c:choose>
			<c:when test="${item.state=='NEW'||item.state=='SAVED'}"></c:when>
			<c:when test="${item.state=='SUBMITTED'}">
				<input type="button"  value="评价" onclick="javascript:window.open('/${ctx}/monthsmy/manage/${item.id}','_self')"/>
			</c:when>
			<c:when test="${item.state=='FINISHED'}">
			</c:when>
		</c:choose>
		
	</td>
				
   </tr>
</c:forEach>
</tbody>
</table>
</body>
</html>
