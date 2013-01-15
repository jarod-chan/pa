<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>

<style type="text/css">
 #tbl{
 	border-collapse: collapse;
 }
 
 .tablmax{
 	width: 1500px;
 }
 
 #tbl tbody td{
 	border: 1px solid #000000;
 	padding: 5px;
 }
 

</style>

<script type="text/javascript">
	$(document).ready(function() {
		$("select[name=year]").bind("change",selChange);
	 });
	
	var ifopen=true;
	
	function showHideItem(obj){
		if(ifopen){
			$(obj).val('>>');
			$('#tbl tr').find('td:gt(3)').hide();
			
			ifopen=false;
		}else{
			$(obj).val('<<');
			$('#tbl tr').find('td:gt(3)').show();
			ifopen=true;
		}
		$("#tbl").toggleClass("tablmax");
	}
	
	var selChange=function(){
		window.open('/${ctx}/admin/rpt/point/'+$(this).val()+'/asc','_self');
	}
	
</script>
 
</head>
<body>
<h1>考核最终得分</h1>

<select name="year">
		<c:forEach var="item" items="${yearSel}">
			<option value="${item}" <c:if test="${item==year}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	</select>年



&nbsp;&nbsp;
<input type="button" value="&lt;&lt;返回"  onclick="javascript:window.open('/pa/admin/all','_self')"/>
<br>
<br>
<table id="tbl" class="tablmax">
<thead>
	<tr>
		<th ></th>
		<th ></th>
		<th></th>
		<th style="text-align: right;padding: 0px;">
			<input type="button" value="&lt;&lt;"  onclick="showHideItem(this)"/>
		</th>
	</tr>
</thead>
<tbody>
<tr>
	<td>
		<div style="width: 30px;">编号</div>
	</td>
	<td>
		<div style="width: 50px;">用户名</div>
	</td>
	<td>
		<div style="width: 60px;">部门</div>
	</td>
	<td>
	<div style="width: 120px;">
		排名
		<input type="button" value="&uarr;"  onclick="javascript:window.open('/${ctx}/admin/rpt/point/${year}/asc','_self')"/>
		<input type="button" value="&darr;"  onclick="javascript:window.open('/${ctx}/admin/rpt/point/${year}/desc','_self')" />
	</div>
	</td>
	<td>Scheck</td>
	<td>mamp</td>
	<td>stotal</td>
	<td>alpha</td>
	<td>maxs</td>
	<td>mins</td>
	<td>samp</td>
	<td>upsilon</td>
	<td>val</td>
	<td>result=upsilon+val</td>
</tr>
<c:forEach var="item" items="${points}" >
	<tr>
	<td>${item.personId}</td> 
	<td>${item.personName}</td>
	<td>${item.personDept}</td>
	<td>${item.ranking}</td>
	<td>${item.scheck}</td>
	<td>${item.mamp}</td>
	<td>${item.stotal}</td>
	<td>${item.alpha}</td>
	<td>${item.maxs}</td>
	<td>${item.mins}</td>
	<td>${item.samp}</td>
	<td>${item.upsilon}</td>
	<td>${item.val}</td>
	<td>${item.result}</td>
	</tr>
</c:forEach>
</tbody>
</table>
<div style="color:red;">
${error_info}
</div>
<br>


</body>
</html>
