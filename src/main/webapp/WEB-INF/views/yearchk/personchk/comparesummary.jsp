<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<meta name="decorator" content="none"/>
<%@ include file="../../common/head.jsp"%>
<style type="text/css">
	.all{
 		width:48%;
		margin-bottom:20px;
		border-width:2px;
		border-style:solid ;           /* dashed ; */
		border-color:  #000000;
		float: left;
		margin-left: 5px; 
	}
	
	.head{
		font-size:20px;
		font-weight: bold;
		padding-left: 10px;
		border-bottom: 1px solid #000000;
		margin-bottom: 5px;
	}
	
	.content {
		border-width: 1px;
		border-style: dashed ;           /* dashed ; */
		border-color:  #000000;
		margin: 0px 5px 10px 5px;
	}
	
	.content  .title{
		margin: 5px;
	}
	
	.content .content_text{
		margin-left: 5px;
		margin-bottom: 5px;
	}
	
	
	
	
	div.context .none { clear:both; font-size:0px; height:0px; line-height:0px;}
	
</style>
<script type="text/javascript">
	$(function() {
		$("#btn_jump").click(function(){
			window.open('/${ctx}/person/${person.id}/yearchk/personchk/${colPerson.id}/comparework/${rowPerson.id}','_self');
		});
	});
</script>
</head>

<body>
<%@ include file="../../common/message.jsp"%>
<div style="text-align: center;">
<span style="font-size: 50px;">述职报告对比 </span> 
</div>
<div style="margin-left: 5px;">
<input type="button" id="btn_jump" value="查看双方月度小结"/>
<input type="button"  value="查看双方述职报告" disabled="disabled"/>
</div>
<br>
<div class="all">
		<div class="head">${year}年${colPerson.name}述职报告</div>
		
		<c:forEach var="titleContent" items="${colSummary.titleContents}">
		<div class="content">
			<div class="title">${titleContent.title.no}.${titleContent.title.title}</div>
			<div class="content_text"  >${titleContent.content.content}</div>
		</div>
		</c:forEach>
</div>

<div class="all">
		<div class="head">${year}年${rowPerson.name}述职报告</div>
		
		<c:forEach var="titleContent" items="${rowSummary.titleContents}">
		<div class="content">
			<div class="title">${titleContent.title.no}.${titleContent.title.title}</div>
			<div class="content_text"  >${titleContent.content.formatContent}</div>
		</div>
		</c:forEach>
</div>


</body>
</html>
