<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

<script type="text/javascript">
	$(function(){
		
		$("#btnBack").click(function(){
			window.open("/${ctx}/atten/${person.id}/busiout/list?year=${queryBean.year}&month=${queryBean.month}","_self");
		});
	})
</script>
</head>

<c:set target="${pagefunc}" property="name" value="短期公出" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/atten/${person.id}/busiout/list" />  

<c:set target="${pagetitle}" property="name" value="短期公出查看" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/atten/${person.id}/busiout/new" /> 

<c:set var="pagesize" value="990" scope="request"/>  
<body>


	
		

<%@ include file="../../common/message.jsp"%>


<table>
<tr>
	<td style="width: 300px;">编号：${busiout.no}</td> <td style="width: 300px;">状态：${busiout.busiState.name}</td>
</tr>
<tr>
	<td colspan="2">	
		日期：${busiout.year}年${busiout.month}月${busiout.begDayitem.date}日${busiout.begDayitem.ampm.name}
		&nbsp;<span>-&gt;</span>&nbsp;
		${busiout.endDayitem.date}日${busiout.endDayitem.ampm.name}
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
<br>
	<input type="button" id="btnBack" value="&lt;&lt;返回">
</body>
</html>
