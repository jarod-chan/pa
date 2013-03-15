<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

<script type="text/javascript">
	$(function(){
		
		$("#btnCommit").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/commit").submit();
		});
		
		$("#btnBack").click(function(){
			window.open("/${ctx}/atten/${person.id}/preatten/list","_self");
		});
	})
</script>
</head>

<c:set target="${pagefunc}" property="name" value="预约打卡" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/atten/${person.id}/preatten/list" />  

<c:set target="${pagetitle}" property="name" value="预约打卡新建" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/atten/${person.id}/preatten/new" /> 

<c:set var="pagesize" value="990" scope="request"/>  
<body>


	
		

<%@ include file="../../common/message.jsp"%>

<form action="/${ctx}/atten/${person.id}/preatten" method="post">

<input type="hidden" name="year" value="${preatten.year}">
<input type="hidden" name="month" value="${preatten.month}">

<table>
<tr>
	<td style="width: 300px;">编号：系统自动生成</td> <td style="width: 300px;">状态：${preatten.state.name}</td>
</tr>
<tr>
	<td colspan="2">	
		日期：${preatten.year}年${preatten.month}月
		<select name="dayitem.date">
			<c:forEach var="day" items="${dayList}">
				<option value="${day}" <c:if test="${day==preatten.dayitem.date}">selected="true"</c:if> >${day}</option>
			</c:forEach>
		</select>日
		<select name="dayitem.ampm">
			<c:forEach var="ampm" items="${ampms}">
				<option value="${ampm}" <c:if test="${ampm==preatten.dayitem.ampm}">selected="true"</c:if> >${ampm.name}</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<td colspan="2">原因：<br>
	<textarea name="reason" style="height: 180px;width: 600px;">${preatten.reason}</textarea>
	</td>
</tr>
<tr>
	<td colspan="2">申请人：${preatten.person.name}</td>
</tr>
</table>
<br>
	<input type="button" id="btnCommit" value="提交">
	<input type="button" id="btnBack" value="取消">
</form>
</body>
</html>
