<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>


<script type="text/javascript">

	function drop(){
		$('<form/>',{action:'/${ctx}/monthlog/${summarySnapshot.id}/remove',method:'post'})
	 	.appendTo($("body"))
	 	.submit();
	}
</script> 
</head>

<c:set target="${pagefunc}" property="name" value="考核结果历史" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/monthlog/history" />    

<c:set target="${pagetitle}" property="name" value="考核结果查看" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/monthlog/history/${summarySnapshot.id}" /> 

<c:set var="pagesize" value="800" scope="request"/>  

<body>


<div class="headdiv" >
<div class="headleft" style="width: 70%">
	考核周期:${summarySnapshot.year}年${summarySnapshot.month}月&nbsp;&nbsp;接收日期:<fmt:formatDate value="${summarySnapshot.logDate}" pattern="yyyy-MM-dd HH:mm:ss" />
	&nbsp;&nbsp;状态:${summarySnapshot.state.name}
</div>
<div class="headright" style="width: 30%">
	<c:if test="${isSummarySnapshotCanRemove}">
		<input type="button" value="删除"  onclick="drop()"/>
	</c:if>
</div>
<div  class="headnone"></div>
</div>


<%@ include file="../../common/message.jsp"%>
<%@ include file="../../fragment/snapshotitemview.jsp"%>



</body>
</html>
