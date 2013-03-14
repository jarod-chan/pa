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
		
		$("#btnSave").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/save").submit();
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

<table>
<tr>
	<td style="width: 300px;">编号：${busiout.no}</td> <td style="width: 300px;">状态：${busiout.busiState.name}</td>
</tr>
<tr>
	<td colspan="2">	
		日期：
		<input type="text" id="begdate"  name="begDayitem.date" value="${busiout.begDayitem.date}" style="width: 150px;"/>&nbsp;<select name="begDayitem.ampm">
			<c:forEach var="ampm" items="${ampms}">
				<option value="${ampm}"  <c:if test="${ampm==busiout.begDayitem.ampm}">selected="true"</c:if> >${ampm.name}</option>
			</c:forEach>
		</select>
		&nbsp;<input id="btn_singel" type="button" value="-&gt;">&nbsp;<input type="hidden" name="singel" value="${busiout.singel}">
		<span id="span_end" <c:if test="${busiout.singel==true}">style="display:none"</c:if> >
	    <input type="text" id="enddate"  name="endDayitem.date" value="${busiout.endDayitem.date}"/>&nbsp;<select name="endDayitem.ampm">
			<c:forEach var="ampm" items="${ampms}">
				<option value="${ampm}"  <c:if test="${ampm==busiout.endDayitem.ampm}">selected="true"</c:if> >${ampm.name}</option>
			</c:forEach>
		</select>
		</span>
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
	<input type="button" id="btnSave" value="保存">
	<input type="button" id="btnCommit" value="提交">
</form>
<script type="text/javascript">ESONCalendar.bind("begdate");ESONCalendar.bind("enddate");</script>
</body>
</html>
