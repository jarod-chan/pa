<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>
<script type="text/javascript">
	function saveAllChecks(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		actionFrom.attr("action",oldAction+"/saveAllChecks").submit();
	}
</script>
</head>
<c:set target="${pagefunc}" property="name" value="年度横向评价" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/personsc" />  


<c:set var="pagesize" value="825" scope="request"/> 
<body>

<%@ include file="../../common/message.jsp"%>
</body>
</html>
