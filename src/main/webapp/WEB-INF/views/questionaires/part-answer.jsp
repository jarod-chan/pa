<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
<script type="text/javascript">
	$(function(){
		
		$("textarea").attr({"maxlength":"500"}).iemaxlength();
		
		$("#btn_prev").click(function(){
			window.open('/pa/qs/part/${prev.id}','_self');
		});
		
		$("#btn_next").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/next_answer").submit();
		});
		
	});
</script>
</head>
<body>
<div style="margin-left:20px;margin-top: 50px;">
<h3>${part.name}(共${fn:length(shortBeanList)}个问题)</h3>
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
	
	<c:forEach var="shortBean"  items="${shortBeanList}" varStatus="status">
		<div style="margin-bottom: 20px;">
			<div>${status.count}.${shortBean.shortAnswer.subject}<span style="color: red;">（可以不填，字数控制500以内）</span></div>
			<div style="margin-left: 20px;">
				<input type="hidden" name="shortBeans[${status.index}].id" value="${shortBean.id}">
				<input type="hidden" name="shortBeans[${status.index}].problemid" value="${shortBean.problemid}">
				<textarea name="shortBeans[${status.index}].value" style="width: 600px;height: 200px;">${shortBean.value}</textarea>
			</div>
		</div>	
	</c:forEach>
</form>

<div style="width:600px;">
	<div style="width:300px;float: left;">
		<c:choose>
			<c:when test="${not empty prev}">
				<input id="btn_prev" type="button" value="上一页">
			</c:when>
		</c:choose>	
		&nbsp;
	</div>
	<div style="width:300px;float: right;text-align: right;">
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
