<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

</head>
<c:set target="${pagefunc}" property="name" value="年度横向评价" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/person/${person.id}/yearchk" />  


<c:set var="pagesize" value="960" scope="request"/> 
<body>

<div class="headdiv" >
<div class="headleft"  >
考核年份:${year}&nbsp;&nbsp;状态:已提交
</div>
<div class="headright" >
</div>
<div  class="headnone"></div>
</div>
<%@ include file="../../common/message.jsp"%>


<div style="color: red;font-size: 50px; width: 800px;" >
	你已经提交${year}年年终员工评价，无法进行其它操作!
</div>
</body>
</html>
