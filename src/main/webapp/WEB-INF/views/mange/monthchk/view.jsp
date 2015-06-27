<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>
<style type="text/css">
	.colspan{
		border: 1px solid #000; 
		display: inline-block;
		margin-left: 5px;
		padding: 2px 5px;
	}
	.color_5{
		background-color: #FF8080;
	}
	.color_4{
		background-color: #FECF78;
	}
	.color_3{
		background-color: #B9EA48;
	}
	.color_2{
		background-color: #00C462;
	}
	.color_1{
		background-color: #1E8EFF;
	}
</style>

</head>

<c:set target="${pagefunc}" property="name" value="月度小结评价" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/monthsmy/manage" />  

<c:set target="${pagetitle}" property="name" value="员工月度小结查看" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/monthsmy/manage/${monthChk.id}/view" /> 

<c:set var="pagesize" value="990" scope="request"/>  

<body>

<div class="headdiv" >
<div class="headleft" >
部门:${mange.department}&nbsp;&nbsp;考核员工:${monthChk.person.name}&nbsp;&nbsp;考核周期:${monthChk.year}年${monthChk.month}月&nbsp;&nbsp;考核状态:${monthChk.state.name}
</div>
<div class="headright">
</div>
<div  class="headnone"></div>
</div>


<%@ include file="../../common/message.jsp"%>

<form id="monthChk" action="/${ctx}/monthsmy/manage/${monthChk.id}" method="post">

<table class="tbldef" border=1 style="table-layout:fixed;width:950px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:100px;">工作性质</th>
		<th style="width:600px;">工作内容</th>
		<th style="width:50px;">用时</th>
		<th style="width:150px;">评价</th>
	</tr>
</thead>
<tbody>
	<c:forEach var="item" items="${monthChk.monthChkItems}">
		<tr>
			<td style="display:none">
				<input type="text" name="monthChkItems_id"   value="${item.id}" />
			</td>
			<td>
				${item.sn}
			</td>
			<td>
				${item.workType.worktype}
			</td>
			<td>
				${item.task}
			</td>
			<td>
				${item.workhour}
			</td>
			<td>
				<c:if test="${not empty item.point}">
				<span class="colspan color_${item.point}">
				<c:choose>
	     			<c:when test="${item.point=='5'}">优秀</c:when>
	     			<c:when test="${item.point=='4'}">良好</c:when>
	     			<c:when test="${item.point=='3'}">尽职</c:when>
	     			<c:when test="${item.point=='2'}">需改进</c:when>
	     			<c:when test="${item.point=='1'}">差</c:when>
				</c:choose>
				</span>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
<br/>

<input type="button" value="<<返回"  onclick="javascript:window.open('/${ctx}/monthsmy/manage','_self')"/>
</form>

</body>
</html>
