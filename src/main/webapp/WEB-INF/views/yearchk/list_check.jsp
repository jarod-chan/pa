<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>

<link rel="stylesheet" type="text/css" href="/${ctx}/resources/css/check.css" />

<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
	 });
</script>  
</head>
<c:set var="pagefunc" value="年终员工考核" scope="request"/> 
<c:set var="pagetitle" value="年终员工考核列表" scope="request"/> 
<c:set var="pagesize" value="1010" scope="request"/> 
<body>
	<h3>员工${currPerson.name}对其它人的考核</h3>
<c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:300px">${msg}</div>
</c:if>

<form action="save?personId=${currPerson.id}" method="post">
	<div>
	  <input type="submit" value="保存"/>
	  <input type="button" value="退出" " onclick="javascript:window.open('../','_self')"/>
	</div>
	
	<table id="tab" border=1>
		<c:forEach items="${tableData}" var="row">
			<tr>
				<c:forEach items="${row}" var="cell">
					<td>
					<div  class="cell">
						<c:choose>
							<c:when test="${cell.type=='empty'}">
								<nbsp />
							</c:when>
							<c:when test="${cell.type=='person'}">
							${cell.fyperson.name}
							</c:when>
							<c:when test="${cell.type=='data'}">
								<input type="hidden" name="id" value="${cell.dataCell.id}" />
								<input type="hidden" name="colId" value="${cell.dataCell.colId}" />
								<input type="hidden" name="rowId" value="${cell.dataCell.rowId}" />
								<select name="val">
									<option value="1"    <c:if test="${cell.dataCell.val=='1'}">selected="true"</c:if> >${cell.fyperson.name}优</option>
									<option value="0"    <c:if test="${cell.dataCell.val=='0'}">selected="true"</c:if> >平</option>
									<option value="-1"   <c:if test="${cell.dataCell.val=='-1'}">selected="true"</c:if>>${cell.scperson.name}优</option>
								</select>
							</c:when>
						</c:choose>
					</div>
					</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</form>

</body>
</html>
