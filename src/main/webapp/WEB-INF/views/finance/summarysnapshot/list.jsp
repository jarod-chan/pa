<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

<script type="text/javascript">
	var selChange=function(){
		$('<form/>',{action:'/${ctx}/finance/${person.id}/summarysnapshot',method:'post'})
		.append($('<input/>',{type:'hidden',name:'year',value:$("select[name=year]").val()}))
	 	.appendTo($("body"))
	 	.submit();
	}
	
	function remove(summarysnapshotId){
		$('<form/>',{action:'/${ctx}/finance/${person.id}/summarysnapshot/'+summarysnapshotId+'/remove',method:'post'})
	 	.appendTo($("body"))
	 	.submit();
	}
	
	$(document).ready(function(){
		$("select[name=year]").bind("change",selChange);
	})
	
</script>
</head>

<c:set target="${pagefunc}" property="name" value="员工考核结果" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/finance/${person.id}/summarysnapshot" />  

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
	<input type="button" value="接收考核结果" onclick="javascript:window.open('/${ctx}/finance/${person.id}/summarysnapshot/receive','_self')"/>
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
		<th style="width:80px;">状态</th>
		<th style="width:120px;">操作</th>
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
		${item.state.name}
	</td>
	<td>	
		<input type="button" value="查看" onclick="javascript:window.open('/${ctx}/finance/${person.id}/summarysnapshot/${item.id}','_self')"/>
		&nbsp;&nbsp;
		<c:if test="${item.state=='RECEIVED'}">
			<input type="button" value="删除" onclick="remove('${item.id}')"/>
		</c:if>
	</td>
				
   </tr>
</c:forEach>
</tbody>
</table>
</body>
</html>
