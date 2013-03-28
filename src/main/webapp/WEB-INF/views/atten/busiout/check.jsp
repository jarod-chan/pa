<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>
<style type="text/css">
	.chk_div{
		border: 1px dashed   #1E8EFF;
		padding: 10px;
		width: 500px;
		margin-top: 10px;
	}
</style>

<script type="text/javascript">
	$(function(){
		$("#btnCommit").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/commit").submit();
		});
		$("#btnBack").click(function(){
			window.open("/${ctx}/atten/${person.id}/task/list","_self");
		});
	})
</script>
</head>

<c:set target="${pagefunc}" property="name" value="任务中心" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/atten/${person.id}/task/list" />  

<c:set target="${pagetitle}" property="name" value="任务执行" /> 
<c:set target="${pagetitle}" property="url" value="#" /> 

<c:set var="pagesize" value="990" scope="request"/>  
<body>


	
		

<%@ include file="../../common/message.jsp"%>


<table>
<tr>
	<td style="width: 300px;">编号：${busiout.no}</td> <td style="width: 300px;">状态：${busiout.busiState.name}</td>
</tr>
<tr>
	<td colspan="2">	
		日期：${busiout.begDayitem.year}年${busiout.begDayitem.month}月${busiout.begDayitem.day}日${busiout.begDayitem.ampm.name}
		&nbsp;<span>-&gt;</span>&nbsp;
		${busiout.endDayitem.year}年${busiout.endDayitem.month}月${busiout.endDayitem.day}日${busiout.endDayitem.ampm.name}
	</td>
</tr>
<tr>
	<td colspan="2">地点：${busiout.place}</td>
</tr>
<tr>
	<td colspan="2">原因：${busiout.reason}</td>
</tr>
<tr>
	<td>上级：${busiout.leader}已同意本次外出。</td>
	<td>申请人：${busiout.person.name}</td>
</tr>
</table>


<c:forEach var="opinion" items="${opinions}">
	<div class="chk_div">
		<div class="chk_result">
			${opinion.userName}：${opinion.result.name}
		</div>
		<c:if test="${not empty opinion.content}">
			<div class="chk_content">
					审批意见：${opinion.content}
			</div>	
		</c:if>	
	</div>
</c:forEach>


<form action="/${ctx}/atten/${person.id}/busiout/check" method="post">
	<input type="hidden" name="taskId" value="${task.id}"/>
	<input type="hidden" name="businessId" value="${busiout.id}"/>
	<div class="chk_div" >
		<div class="chk_result">
			审批结果：<select name="result">
				<c:forEach var="result" items="${resultList}">
					<option value="${result}">${result.name}</option>
				</c:forEach>
			</select>
		</div>	
		<div class="chk_content">
			审批意见：<textarea name="content" style="vertical-align: top;height:180px;width: 400px; "></textarea>
		</div>
	</div>
</form>

<br>
	<input type="button" id="btnCommit" value="提交">
	<input type="button" id="btnBack" value="&lt;&lt;返回">
</body>
</html>
