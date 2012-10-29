<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
<script type="text/javascript">
	$(function(){
		$("input[type=checkbox]").click(function(){
			var checkbox=$(this);
			checkbox.attr("checked",true);
			checkbox.parent().find(".partItemValue").val(checkbox.val());
			checkbox.parent().find("input[type=checkbox]").not(checkbox).removeAttr("checked");
			checkbox.parent().css("border-color","white");
		});
		
		$("#btn_prev").click(function(){
			window.open('/pa/qs/part/${prev.id}','_self');
		});
		
		$("#btn_next").click(function(){
			var partItems=$(".partItemValue[value='']");
			if(partItems.size()!=0){
				partItems.each(function(){
					$(this).parent().css("border-color","red");
				});
				alert("红色标注项目未被选择，无法跳转下一页！");
				return;
			}
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/next_choice").submit();
		});
	});
</script>
</head>
<body>
<div style="margin-left:20px;margin-top: 50px;">
<h3>${part.name}(共${fn:length(partBeanList)}个问题)</h3>
<%@ include file="common/message_nohide.jsp"%>

<form action="/${ctx}/qs/part/${part.id}" method="post">

	<c:choose>
		<c:when test="${not empty next}">
			<input type="hidden" name="finish" value="false">
		</c:when>
		<c:otherwise>
			<input type="hidden" name="finish" value="true">
		</c:otherwise>
	</c:choose>	

	<c:set value="0" var="index" /> 
	
	<c:forEach var="partBean"  items="${partBeanList}" varStatus="status">
		<div style="margin-bottom: 20px;">
			<div>${status.count}.${partBean.choice.subject}</div>
			<c:forEach var="partItem" items="${partBean.partItems}" >
				<div style="margin-left: 15px;border-left: 5px solid white;margin-top: 2px;">
					<input type="hidden" name="receiveBeans[${index}].id"    class="partItemId" value="${partItem.id}">
					<input type="hidden" name="receiveBeans[${index}].value" class="partItemValue" value="${partItem.value}">
					<c:forEach var="answer" items="${partItem.answerList}">
						<input type="checkbox" name="chk_${status.count}_${partItem.type}" value="${answer.id}" <c:if test="${answer.id==partItem.value}">checked="true"</c:if> />${answer.no}.${answer.name}&nbsp;&nbsp;
					</c:forEach>
				</div>
				<c:set value="${index+1}" var="index" />
			</c:forEach>
		</div>	
	</c:forEach>
</form>

<div style="width:800px;">
	<div style="width:400px;float: left;">
		<c:choose>
			<c:when test="${not empty prev}">
				<input id="btn_prev" type="button" value="上一页">
			</c:when>
		</c:choose>	
		&nbsp;
	</div>
	<div style="width:400px;float: right;text-align: right;">
		<c:choose>
			<c:when test="${not empty next}">
				<input id="btn_next" type="button" value="下一页">
			</c:when>
			<c:otherwise>
				<input id="btn_next" type="button" value="完成">
			</c:otherwise>
		</c:choose>	
		&nbsp;
	</div>
</div>

</div>
</body>
</html>
