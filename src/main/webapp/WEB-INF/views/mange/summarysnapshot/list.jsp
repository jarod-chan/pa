<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

<script type="text/javascript">
	var selChange=function(){
		$('<form/>',{action:'/${ctx}/monthlog/cf',method:'post'})
		.append($('<input/>',{type:'hidden',name:'year',value:$("select[name=year]").val()}))
	 	.appendTo($("body"))
	 	.submit();
	}
	
	$(document).ready(function(){
		$("select[name=year]").bind("change",selChange);
	})
	
</script>

<style type="text/css">
	div.state{
		background-color:#FFFFFF;
		border: 1px solid #97CBFF;
	}
	
	div.confirmed{
		background-color:#A0A0A0;
	}
</style>
</head>

<c:set target="${pagefunc}" property="name" value="考核结果确认" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/monthlog/cf" />  

<c:set var="pagesize" value="825" scope="request"/> 
<body>

<div class="headdiv" >
<div class="headleft"  >
	年度:
	<select name="year">
		<c:forEach var="item" items="${dateTool.allYears}">
			<option value="${item}" <c:if test="${item==queryBean.year}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	</select>年
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
		<th style="width:350px;">工作完成情况表</th>
		<th style="width:200px;">接收日期</th>
		<th style="width:100px;">状态</th>
		<th style="width:100px;">操作</th>
	</tr>
</thead>
<tbody>
<c:forEach var="item" items="${summarySnapshots}"  varStatus="status">
	<tr>
	<td>
		${status.count}
	</td>
	<td>
		${item.year}年${item.month}月份员工考核结果
	</td>
	<td>
		<fmt:formatDate value="${item.logDate}" pattern="yyyy-MM-dd HH:mm:ss" />
	</td>
	<td>
		<div class="state ${fn:toLowerCase(item.state)}">${item.state.name}</div> 
	</td>
	
	<td>
		<input type="button" value="查看" onclick="javascript:window.open('/${ctx}/monthlog/cf/${item.id}','_self')"/>
	</td>
				
   </tr>
</c:forEach>
</tbody>
</table>
</body>
</html>
