<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
		$("select[name=year]").bind("change",selChange);
	 });
	
	function showHideItem(obj){
		if($(obj).val()=='显示明细'){
			$(obj).val('隐藏明细');
			$('#tbl tr').find('td:gt(2):lt(13)').show();
		}else{
			$(obj).val('显示明细');
			$('#tbl tr').find('td:gt(2):lt(13)').hide();
		}
	}
	
	var selChange=function(){
		$('<form/>',{action:'/${ctx}/admin/rpt/point/'+$(this).val()+'/asc',method:'post'})
		.append($('<input/>',{type:'hidden',name:'year',value:$("select[name=year]").val()}))
	 	.appendTo($("body"))
	 	.submit();
	}
	
</script>
 
</head>
<body>
<h1>考核最终得分</h1>
<c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:300px">${msg}</div>
</c:if>

<select name="year">
		<c:forEach var="item" items="${yearSel}">
			<option value="${item}" <c:if test="${item==year}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	</select>年

<input type="button" value="从高到低排名&darr;"  onclick="javascript:window.open('/${ctx}/admin/rpt/point/${year}/desc','_self')"/>
<input type="button" value="从低到高排名&uarr;"  onclick="javascript:window.open('/${ctx}/admin/rpt/point/${year}/asc','_self')"/>

&nbsp;&nbsp;

<input type="button" value="隐藏明细"  onclick="showHideItem(this)"/>
<br>
<br>
<table border=1 id="tbl">
<tr>
	<td>编号</td><td>用户名</td><td>部门</td><td>Scheck</td><td>mdep</td><td>mall</td><td>damp</td>
	<td>mamp</td><td>stotal</td><td>s</td><td>alpha</td><td>maxs</td><td>mins</td><td>samp</td>
	<td>upsilon</td><td>val</td><td>result=upsilon+val</td><td>ranking</td>
</tr>
<c:forEach var="item" items="${points}" >
	<tr>
	<td>${item.personId}</td> 
	<td>${item.personName}</td>
	<td>${item.personDept}</td>
	<td>${item.scheck}</td>
	<td>${item.mdep}</td>
	<td>${item.mall}</td>
	<td>${item.damp}</td>
	<td>${item.mamp}</td>
	<td>${item.stotal}</td>
	<td>${item.s}</td>
	<td>${item.alpha}</td>
	<td>${item.maxs}</td>
	<td>${item.mins}</td>
	<td>${item.samp}</td>
	<td>${item.upsilon}</td>
	<td>${item.val}</td>
	<td>${item.result}</td>
	<td>${item.ranking}</td>
	</tr>
</c:forEach>
</table>
<div style="color:red;">
${error_info}
</div>
<br>
<input type="button" value="&lt;&lt;返回"  onclick="javascript:window.open('/pa/admin/all','_self')"/>

</body>
</html>
