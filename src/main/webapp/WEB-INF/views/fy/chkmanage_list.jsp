<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<html>
<%@ include file="head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
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
</script>  
<body>
<h3>经理${manage.name}对${person.name}考核</h3>
<c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:300px">${msg}</div>
</c:if>

<form action="save?mangeId=${manage.id}&personId=${person.id}" method="post">
	<div>
	  <input type="submit" value="保存"/>
	  <input type="button" value="返回"  onclick="javascript:window.open('../fymanage/list?personId=${manage.id}','_self')"/>
	</div>

<table border=1>
<tr>
	<td>考核要素</td><td>合计：${sumall}</td><td>得分</td><td>考核内容</td><td>必选</td><td>评价</td>
</tr>
<c:forEach items="${tabData}" var="item">
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
</form>
</body>
</html>