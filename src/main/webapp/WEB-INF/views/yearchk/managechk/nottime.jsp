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
<c:set var="pagefunc" value="员工年度考核" scope="request"/> 
<c:set var="pagetitle" value="部门员工年度工作评价列表" scope="request"/> 
<c:set var="pagesize" value="825" scope="request"/> 
<body>

<div class="headdiv" >
<div class="headleft"  >
&nbsp;
</div>
<div class="headright" >
&nbsp;
</div>
<div  class="headnone"></div>
</div>
<%@ include file="../../common/message.jsp"%>
</body>
</html>
