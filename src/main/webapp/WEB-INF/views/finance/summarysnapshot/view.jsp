<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>


<script type="text/javascript">
	var selChange=function(){
		$('<form/>',{action:'/${ctx}/finance/${person.id}/summarysnapshot/receive',method:'post'})
		.append($('<input/>',{type:'hidden',name:'year',value:$("select[name=year]").val()}))
		.append($('<input/>',{type:'hidden',name:'month',value:$("select[name=month]").val()}))
	 	.appendTo($("body"))
	 	.submit();
	}
	
	$(document).ready(function(){
		$("select[name=year],select[name=month]").bind("change",selChange);
	})
	
	function remove(){
		$('<form/>',{action:'/${ctx}/finance/${person.id}/summarysnapshot/${summarySnapshot.id}/remove',method:'post'})
	 	.appendTo($("body"))
	 	.submit();
	}
</script> 
</head>

<c:set target="${pagefunc}" property="name" value="员工考核结果" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/finance/${person.id}/summarysnapshot" />  

<c:set target="${pagetitle}" property="name" value="员工考核结果查看" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/finance/${person.id}/summarysnapshot/${summarySnapshot.id}" /> 

<c:set var="pagesize" value="990" scope="request"/>  

<body>
<form action="/${ctx}/finance/${person.id}/summarysnapshot/receive/save" method="post">

<div class="headdiv" >
<div class="headleft" >
	考核周期:${summarySnapshot.year}年${summarySnapshot.month}月&nbsp;&nbsp;接收日期:<fmt:formatDate value="${summarySnapshot.logDate}" pattern="yyyy-MM-dd HH:mm:ss" />
	&nbsp;&nbsp;状态:${summarySnapshot.state.name}
</div>
<div class="headright">
	<c:if test="${isSummarySnapshotCanRemove}">
		<input type="button" value="删除"  onclick="remove()"/>
	</c:if>
</div>
<div  class="headnone"></div>
</div>


<%@ include file="../../common/message.jsp"%>
<%@ include file="../../fragment/snapshotitemview.jsp"%>


</form>

</body>
</html>
