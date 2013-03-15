<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

<script type="text/javascript">
	$(function(){
		
		$("#btnBack").click(function(){
			window.open("/${ctx}/atten/${person.id}/preatten/list?year=${queryBean.year}&month=${queryBean.month}","_self");
		});
	})
</script>
</head>

<c:set target="${pagefunc}" property="name" value="预约打卡" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/atten/${person.id}/preatten/list" />  

<c:set target="${pagetitle}" property="name" value="预约打卡查看" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/atten/${person.id}/preatten/new" /> 

<c:set var="pagesize" value="990" scope="request"/>  
<body>


	
		

<%@ include file="../../common/message.jsp"%>


<table>
<tr>
	<td style="width: 300px;">编号：${preatten.no}</td> <td style="width: 300px;">状态：${preatten.state.name}</td>
</tr>
<tr>
	<td colspan="2">	
		日期：${preatten.year}年${preatten.month}月${preatten.dayitem.date}日${preatten.dayitem.ampm.name}
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
	<input type="button" id="btnBack" value="&lt;&lt;返回">
</body>
</html>
