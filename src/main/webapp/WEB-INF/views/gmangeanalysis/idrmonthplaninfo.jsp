<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<meta name="decorator" content="none"/>
<%@ include file="../common/head.jsp"%>
<style type="text/css">
	div.all{
		width:800px;
		margin-bottom:20px;
		border-width:2px;
		border-style:solid ;           /* dashed ; */
		border-color:  #000000;
	}
	
	div.head{
		font-size:20px;
		font-weight: bold;
		padding-left: 10px;
		border-bottom: 1px solid #000000;
	}
	
	div.context {
		margin: 5px;
		border-style:solid;           /* dashed ; */
		border-width: 1px;
		border-color:  #000000;
		height: 100%;
		width: 780px;
	}
	
	div.context .plan{
		padding-left: 5px;
	}
	
	div.context .summary{
		padding-left: 5px;
	}
	
	
	div.context .none { clear:both; font-size:0px; height:0px; line-height:0px;}
	
</style>
<script type="text/javascript">
	var summaryCss={"width":"530","vertical-align":"top"};
	var summaryLength={"maxlength":"500"};
	var summaryOption={minHeight:60,extraSpace:3};
	
	$(document).ready(function() { 
		$("textarea[name='idrTasks_summary']").css(summaryCss).attr(summaryLength).autoResize(summaryOption);
	});
</script>
</head>

<body>
<%@ include file="../common/message.jsp"%>
<div class="all">
	<div class="head">${bill.year}年${bill.month}月【${bill.department.name}】月度工作计划&nbsp;&nbsp;【${bill.state.name}】</div>
	<c:forEach var="item" items="${bill.idrTasks}">
		<div class="context">
			<div class="plan">
				计划:${item.context}
			</div>
			<div class="summary">
				总结:<textarea style="border-style:none;background:transparent;"  readonly="readonly" name="idrTasks_summary">${item.summary}</textarea>
			</div>
			<div class="none"></div>
		</div>
	</c:forEach>
</div>
</body>
</html>
