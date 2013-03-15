<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>


<script type="text/javascript">	
	$(function(){
		var selChange=function(){
			$('<form/>',{action:'/${ctx}/atten/${person.id}/busiout/list',method:'get'})
			.append($('<input/>',{type:'hidden',name:'year',value:$("select[name=year]").val()}))
			.append($('<input/>',{type:'hidden',name:'month',value:$("select[name=month]").val()}))
		 	.appendTo($("body"))
		 	.submit();
		}
		
		$("select[name=year],select[name=month]").bind("change",selChange);
		
	})
	
</script>

</head>

<c:set target="${pagefunc}" property="name" value="公出申请" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/atten/${person.id}/busiout/list" />  


<c:set var="pagesize" value="825" scope="request"/> 
<body>

<div class="headdiv" >
<div class="headleft" >
	<select name="year">
		<c:forEach var="item" items="${dateTool.allYears}">
			<option value="${item}" <c:if test="${item==queryBean.year}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	</select>年
	<select name="month">
		<c:forEach var="item" items="${dateTool.allMonths}">
			<option value="${item}" <c:if test="${item==queryBean.month}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	</select>月
</div>
<div class="headright"  >
	<input type="button" value="新建" onclick="javascript:window.open('/${ctx}/atten/${person.id}/busiout/new','_self')"/> 
</div>
<div  class="headnone"></div>
</div>

<%@ include file="../common/message.jsp"%>


<table border=1 style="table-layout:fixed;width:800px;">
<thead>
	<tr>
		<th style="width:500px;">编号</th>
		<th style="width:150px;">请假期间</th>
		<th style="width:100px;">状态</th>
		<th style="width:100px;">详细</th>
	</tr>
</thead>
<tbody>
<c:forEach var="busiout" items="${busioutList}"  varStatus="status">
	<tr>
	<td>
		${busiout.no}
	</td>
	<td>
		${busiout.begDayitem.date}日${busiout.begDayitem.ampm.name}-&gt;
		${busiout.endDayitem.date}日${busiout.endDayitem.ampm.name}
	</td>
	<td>
		${busiout.busiState.name}
	</td>
	<td>
		<input type="button" value="查看" " onclick="javascript:window.open('/${ctx}/atten/${person.id}/busiout/view/${busiout.id}?year=${queryBean.year}&month=${queryBean.month}','_self')"/>
	</td>		
   </tr>
</c:forEach>
</tbody>
</table>

</body>
</html>
