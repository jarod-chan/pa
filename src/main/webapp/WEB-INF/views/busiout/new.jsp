<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>

<script type="text/javascript">
	$(function(){
		$("#btn_singel").click(function(){
			$("#span_end").toggle();
			var singel=$("input[name=singel]");
			if($("#span_end").is(":visible")){
				singel.val("false");
			}else{
				singel.val("true");
			}
		});
		
		$("#btnCommit").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/commit").submit();
		});
		
		$("#btnBack").click(function(){
			window.open("/${ctx}/atten/${person.id}/busiout/list","_self");
		});
	})
</script>
</head>

<c:set target="${pagefunc}" property="name" value="公出申请" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/atten/${person.id}/busiout/list" />  

<c:set target="${pagetitle}" property="name" value="公出申请新建" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/atten/${person.id}/busiout/new" /> 

<c:set var="pagesize" value="990" scope="request"/>  
<body>


	
		

<%@ include file="../common/message.jsp"%>

<form action="/${ctx}/atten/${person.id}/busiout" method="post">

<input type="hidden" name="year" value="${busiout.year}">
<input type="hidden" name="month" value="${busiout.month}">

<table>
<tr>
	<td style="width: 300px;">编号：系统自动生成</td> <td style="width: 300px;">状态：${busiout.busiState.name}</td>
</tr>
<tr>
	<td colspan="2">	
		日期：${busiout.year}年${busiout.month}月
		<select name="begDayitem.date">
			<c:forEach var="day" items="${dayList}">
				<option value="${day}" <c:if test="${day==busiout.begDayitem.date}">selected="true"</c:if> >${day}</option>
			</c:forEach>
		</select>日
		<select name="begDayitem.ampm">
			<c:forEach var="ampm" items="${ampms}">
				<option value="${ampm}" <c:if test="${ampm==busiout.begDayitem.ampm}">selected="true"</c:if> >${ampm.name}</option>
			</c:forEach>
		</select>
		&nbsp;<span>-&gt;</span>&nbsp;
		<select name="endDayitem.date">
			<c:forEach var="day" items="${dayList}">
				<option value="${day}" <c:if test="${day==busiout.endDayitem.date}">selected="true"</c:if> >${day}</option>
			</c:forEach>
		</select>日
	    <select name="endDayitem.ampm">
			<c:forEach var="ampm" items="${ampms}">
				<option value="${ampm}" <c:if test="${ampm==busiout.endDayitem.ampm}">selected="true"</c:if> >${ampm.name}</option>
			</c:forEach>
		</select>

	</td>
</tr>
<tr>
	<td colspan="2">原因：<br>
	<textarea name="reason" style="height: 180px;width: 600px;">${busiout.reason}</textarea>
	</td>
</tr>
<tr>
	<td colspan="2">申请人：${busiout.person.name}</td>
</tr>
</table>
<br>
	<input type="button" id="btnCommit" value="提交">
	<input type="button" id="btnBack" value="取消">
</form>
</body>
</html>
