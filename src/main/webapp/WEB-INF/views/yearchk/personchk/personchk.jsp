<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>
<style type="text/css">
.mainul{list-style-type:none; margin:0;padding:0;width:100%; }
.mainul li{ width:84px; float:left;background-color:#FFFFFF;margin: 10px;border: 1px solid #000000; }
.mainul li select{width:80px;}
.mainul li div {text-align: center; padding: 2px;}
</style>
<script type="text/javascript">
	function save(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		$(".mainul").formatName();
		actionFrom.attr("action",oldAction+"/save").submit();
	}
</script>
</head>
<c:set var="pagefunc" value="年终员工考核" scope="request"/> 
<c:set var="pagetitle" value="年终员工个人考核" scope="request"/> 
<c:set var="pagesize" value="820" scope="request"/> 
<body>

<div class="headdiv" >
<div class="headleft"  >
考核年份:${pageBean.year}&nbsp;&nbsp;员工:${pageBean.personChkBean.name}&nbsp;&nbsp;
状态:胜${pageBean.personChkBean.win}&nbsp;负${pageBean.personChkBean.lose}&nbsp;平${pageBean.personChkBean.draw}&nbsp;</td>
</div>
<div class="headright" >
<input type="button" value="<<返回" onclick="javascript:window.open('/${ctx}/person/${person.id}/yearchk','_self')"/>
</div>
<div  class="headnone"></div>
</div>
<%@ include file="../../common/message.jsp"%>
<form action="/${ctx}/person/${person.id}/yearchk/personchk/${pageBean.personChkBean.id}" method="post">
<table border=1 style="table-layout:fixed;width:800px;" >
<thead>
	<tr>
		<th style="width:100px;">员工</th>
		<th style="width:700px;">对比考核结果</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td>${pageBean.personChkBean.name}</td>
		<td>
		<ul class="mainul">
			<c:forEach var="item" items="${pageBean.cellBeans}" varStatus="status">
				<li>
					<div>
						${item.rowPerson.name}
						<input type="hidden" name="fychecks_id" value="${item.fycheck.id}"/>
						<input type="hidden" name="fychecks_year" value="${pageBean.year}"/>
						<input type="hidden" name="fychecks_colId" value="${item.colPerson.id}"/>
						<input type="hidden" name="fychecks_rowId" value="${item.rowPerson.id}"/>
						<input type="hidden" name="fychecks_chkId" value="${person.id}"/>
					</div>
					<div>
						<select name="fychecks_val">
							<option value="1"    <c:if test="${item.fycheck.val=='1'}">selected="true"</c:if> >${item.colPerson.name}胜</option>
							<option value="0"    <c:if test="${item.fycheck.val=='0'}">selected="true"</c:if> >平</option>
							<option value="-1"   <c:if test="${item.fycheck.val=='-1'}">selected="true"</c:if> >${item.rowPerson.name}胜</option>
						</select>
					</div>
				</li>
			</c:forEach>
		</ul>
		</td>
	</tr>
</tbody>
</table>
<br/>
<input type="button" value="保存" onclick="save()"/>
</form>
</body>
</html>
