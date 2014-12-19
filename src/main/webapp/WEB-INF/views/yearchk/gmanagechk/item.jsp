<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

<style type="text/css">
	.chkdiv{
		border: 1px solid #808080;
		text-align: center;
		width:60px;
		margin-left: 10px;
		margin-right: 10px;
	}
	/* 
	颜色设置
	var optColorArr=["#FF8080","#FECF78","#B9EA48","#00C462","#1E8EFF"];
	 */	
	.type5{
		background-color: #FF8080;
	}
	.type4{
		background-color:#FECF78;
	}
	.type3{
		background-color:#B9EA48;
	}
	.type2{
	    background-color:#00C462;
	}
	.type1{
		background-color:#1E8EFF;
	}
</style>


</head>  
<c:set target="${pagefunc}" property="name" value="年度考核评分查询" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/yearpk/gm/department/${selDepartment.id}" />  

<c:set target="${pagetitle}" property="name" value="年度考核评分详细" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/yearpk/gm/person/${checkPerson.id}/year/${year}" />

<c:set var="pagesize" value="768" scope="request"/>
<body>


<div class="headdiv" >
<div class="headleft"  >
考核年份:${year}&nbsp;&nbsp;员工:${checkPerson.name}
</div>
<div class="headright" >
<input type="button" value="<<返回"  onclick="javascript:window.open('/${ctx}/yearpk/gm/department/${selDepartment.id}','_self')"/>
</div>
<div  class="headnone"></div>
</div>
<%@ include file="../../common/message.jsp"%>


<form action="/${ctx}/mange/${person.id}/yearchk/person/${checkPerson.id}" method="post">
<input name="year" type="hidden" value="${year}"/>

<table border=1>
<tr>
	<td>考核要素</td><td>合计：${sumall}</td><td>得分</td><td>考核内容</td><td>必选</td><td>评价</td>
</tr>
<c:forEach items="${chkmangeTabs}" var="item">
	<tr style="height:30px;">
	<c:if test="${item.special=='Y'}">
		<td rowspan="${item.rownum}">${item.type}</td>
		<td rowspan="${item.rownum}">${item.typesum}</td>
	</c:if>
	<td>${item.chkitem.point}</td>
	<td>${item.chkitem.content}</td>
	<td>
		<c:if test="${item.chkitem.must}">
			<input type="checkbox" name="flagchk"  <c:if test="${item.ischeck}">checked</c:if> disabled/>
		</c:if> 
	</td>
	<td>
		<c:if test="${item.ischeck && not empty item.chkmange.id}">
			<c:choose>
				<c:when test="${item.chkmange.val=='5'}">
					<div class="chkdiv type5">优秀</div>
				</c:when>
				<c:when test="${item.chkmange.val=='4'}">
					<div class="chkdiv type4">良好</div>
				</c:when>
				<c:when test="${item.chkmange.val=='3'}">
					<div class="chkdiv type3">尽职</div>
				</c:when>
				<c:when test="${item.chkmange.val=='2'}">
					<div class="chkdiv type2">需改进</div>
				</c:when>
				<c:when test="${item.chkmange.val=='1'}">
					<div class="chkdiv type1">差</div>
				</c:when>
			</c:choose>
			
		</c:if>
	</td>
	</tr>
</c:forEach>
</table>
<br>

</form>
</body>
</html>