<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

<script type="text/javascript">
	$(function(){
		
		//ie6不支持maxlength属性
		$("input[name=place],input[name=leader],input[name=reason]").iemaxlength();

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

<c:set target="${pagefunc}" property="name" value="短期公出" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/atten/${person.id}/busiout/list" />  

<c:set target="${pagetitle}" property="name" value="短期公出新建" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/atten/${person.id}/busiout/new" /> 

<c:set var="pagesize" value="990" scope="request"/>  
<body>

<%@ include file="../../common/message.jsp"%>

<form action="/${ctx}/atten/${person.id}/busiout" method="post">

<input type="hidden" name="year" value="${busiout.year}">
<input type="hidden" name="month" value="${busiout.month}">

<table>
<tr>
	<td style="width: 300px;">编号：系统自动生成</td> <td style="width: 500px;">状态：${busiout.busiState.name}</td>
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
	<td colspan="2">地点：<input type="text"  style="width: 190px;" name="place"  maxlength="10" value="${busiout.place}" /><font style="color:red">[10字以内，简略写明外出地点]</font></td>
</tr>
<tr>
	<td colspan="2">原因：<input type="text"  style="width: 500px;" name="reason" maxlength="50" value="${busiout.reason}" /><font style="color:red">[50字以内，简略写明外出原因]</font>
	</td>
</tr>
<tr>
	<td >上级：<input type="text"  style="width: 100px;" name="leader" maxlength="10" value="${busiout.leader}" />已同意本次外出。</td>
	<td >申请人：${busiout.person.name}</td>
</tr>
</table>
<br>
	<input type="button" id="btnCommit" value="提交">
	<input type="button" id="btnBack" value="取消">
</form>
</body>
</html>
