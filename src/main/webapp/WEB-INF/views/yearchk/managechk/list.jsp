<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>
</head> 

<c:set target="${pagefunc}" property="name" value="员工年度考核" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/mange/${person.id}/yearchk" />  

<c:set target="${pagetitle}" property="name" value="部门员工年度工作评价列表" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/mange/${person.id}/yearchk" /> 


<c:set var="pagesize" value="600" scope="request"/>
<body>

<div class="headdiv" >
<div class="headleft"  >
考核年份:${year}&nbsp;&nbsp;部门:${person.department}
</div>
<div class="headright" >
</div>
<div  class="headnone"></div>
</div>
<%@ include file="../../common/message.jsp"%>

<table border=1 style="table-layout:fixed;width:600px;">
<tr>
	<td>序号</td><td>员工</td><td>得分</td><td>操作</td>
</tr>
<c:forEach  var="item"  items="${personPointList}" varStatus="stauts">
	<tr>
	<td>${stauts.count} </td>
	<td>${item.person.name} </td>
	<td>${item.getPoint}</td>
	<td><input type="button" value="评价" " onclick="javascript:window.open('/${ctx}/mange/${person.id}/yearchk/person/${item.person.id}','_self')"/></td>
	</tr>
</c:forEach>
</table>

</body>
</html>