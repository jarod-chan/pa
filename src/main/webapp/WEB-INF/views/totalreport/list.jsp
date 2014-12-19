<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>

<c:set target="${pagefunc}" property="name" value="公司考核情况查询" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/totalreport" />  


<c:set var="pagesize" value="825" scope="request"/> 
<body>

<div class="headdiv" >
<div class="headleft" >
</div>
<div class="headright"  >
</div>
<div  class="headnone"></div>
</div>

<%@ include file="../common/message.jsp"%>
<table border=1 style="table-layout:fixed;width:800px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:200px;">报表名称</th>
		<th style="width:500px;">报表说明</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td>1</td>
		<td>
			<input type="button" value="公司部门月度工作查询" onclick="javascript:window.open('/${ctx}/report/query/idrmonthplan','_self')"/>
		</td>
		<td>按月显示所有部门的工作计划及总结情况。</td>		
   </tr>
   	<tr>
		<td>2</td>
		<td>
			<input type="button" value="公司员工月度工作查询" onclick="javascript:window.open('/${ctx}/report/query/monthchk','_self')"/>
		</td>
		<td>按月显示部门所有员工的工作总结及经理评价。</td>		
   </tr>
   	<tr>
		<td>3</td>
		<td>
			<input type="button" value="公司部门月度工作概况" onclick="javascript:window.open('/${ctx}/report/analysis/idrmonthplan','_self')"/>
		</td>
		<td>按月显示所有部门计划的状态。</td>		
   </tr>
   	<tr>
		<td>4</td>
		<td>
			<input type="button" value="公司员工月度工作概况" onclick="javascript:window.open('/${ctx}/report/analysis/monthchk','_self')"/>
		</td>
		<td>按月显示所有员工工作总结的状态。</td>		
   </tr>
</tbody>
</table>


</body>
</html>
