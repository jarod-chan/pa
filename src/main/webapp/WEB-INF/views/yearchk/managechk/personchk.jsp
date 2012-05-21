<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("input[name='flagchk']").bind('click',function(){
			var nextSel=$(this).parent().next().find("select");
			if($(this).attr("checked")){
				$(this).next().val("true");
				nextSel.show();
			}else{
				$(this).next().val("false");
				nextSel.hide();
			}
		});
	 });
	
	function save(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		actionFrom.attr("action",oldAction+"/save").submit();
	}
</script> 
</head>  
<c:set target="${pagefunc}" property="name" value="员工年度考核" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/mange/${person.id}/yearchk" />  

<c:set target="${pagetitle}" property="name" value="部门员工年度工作评价" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/mange/${person.id}/yearchk/person/${checkPerson.id}" />

<c:set var="pagesize" value="768" scope="request"/>
<body>


<div class="headdiv" >
<div class="headleft"  >
考核年份:${year}&nbsp;&nbsp;员工:${checkPerson.name}
</div>
<div class="headright" >
<input type="button" value="<<返回"  onclick="javascript:window.open('/pa/mange/${person.id}/yearchk','_self')"/>
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
	<input type="checkbox" name="flagchk"  <c:if test="${item.ischeck}">checked</c:if> <c:if test="${item.chkitem.must}">disabled</c:if> />
	<input type="hidden" name="flag" value="${item.ischeck}">
	</td>
	<td>
		<select name="val" <c:if test="${!item.ischeck}">style="display:none"</c:if> >
			<option value="5" <c:if test="${item.chkmange.val=='5'}">selected="true"</c:if> >优秀</option>
			<option value="4" <c:if test="${item.chkmange.val=='4'}">selected="true"</c:if> >良好</option>
			<option value="3" <c:if test="${item.chkmange.val=='3'}">selected="true"</c:if> >尽职</option>
			<option value="2" <c:if test="${item.chkmange.val=='2'}">selected="true"</c:if> >需改进</option>
			<option value="1" <c:if test="${item.chkmange.val=='1'}">selected="true"</c:if> >差</option>
		  </select>
		  <input type="hidden" name="id" value="${item.chkmange.id}">
		  <input type="hidden" name="itemid" value="${item.chkitem.id}">
	</td>
	</tr>
</c:forEach>
</table>
<br>
<input type="button" value="保存" onclick="save()"/>
</form>
</body>
</html>