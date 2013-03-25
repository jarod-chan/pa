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
			window.open("/${ctx}/atten/${person.id}/preatten/list","_self");
		});
	})
</script>
</head>

<c:set target="${pagefunc}" property="name" value="临时公出" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/atten/${person.id}/preatten/list" />  

<c:set target="${pagetitle}" property="name" value="临时公出新建" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/atten/${person.id}/preatten/new" /> 

<c:set var="pagesize" value="990" scope="request"/>  
<body>


	
		

<%@ include file="../../common/message.jsp"%>

<form action="/${ctx}/atten/${person.id}/preatten" method="post">

<input type="hidden" name="dayitem.year" value="${preatten.dayitem.year}">
<input type="hidden" name="dayitem.month" value="${preatten.dayitem.month}">

<table>
<tr>
	<td style="width: 300px;">编号：系统自动生成</td> <td style="width: 500px;">状态：${preatten.state.name}</td>
</tr>
<tr>
	<td colspan="2">	
		日期：${preatten.dayitem.year}年${preatten.dayitem.month}月
		<select name="dayitem.day">
			<c:forEach var="day" items="${dayList}">
				<option value="${day}" <c:if test="${day==preatten.dayitem.day}">selected="true"</c:if> >${day}</option>
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
	<td colspan="2">地点：<input type="text"  style="width: 190px;" name="place"  maxlength="10" value="${preatten.place}" /><font style="color:red">[10字以内，简略写明外出地点]</font></td>
</tr>
<tr>
	<td colspan="2">原因：<input type="text"  style="width: 500px;" name="reason" maxlength="50" value="${preatten.reason}" /><font style="color:red">[50字以内，简略写明外出原因]</font>
	</td>
</tr>
<tr>
	<td>上级：<input type="text"  style="width: 100px;" name="leader" maxlength="10" value="${preatten.leader}" />已同意本次外出。</td>
	<td>申请人：${preatten.person.name}</td>
</tr>
</table>
<br>
	<input type="button" id="btnCommit" value="提交">
	<input type="button" id="btnBack" value="取消">
</form>
</body>
</html>
