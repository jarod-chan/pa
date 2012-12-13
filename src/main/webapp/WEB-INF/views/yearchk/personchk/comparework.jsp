<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<meta name="decorator" content="none"/>
<%@ include file="../../common/head.jsp"%>
<style type="text/css">
	div.all{
		width:800px;
		margin-bottom:20px;
		border-width:2px;
		border-style:solid ;           /* dashed ; */
		border-color:  #000000;
		float: left;
		margin-left: 5px;
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
	
	div.context .month{
		float:left;
		width:40px;
		border-right:  1px solid #000000;
		text-align: center;
		background-color: #000000;
		color: #FFFFFF;
		font-weight: bolder;
	}
	
	div.context .task{
		float:left;
		padding-left: 10px;
	}
	
	
	
	div.context .none { clear:both; font-size:0px; height:0px; line-height:0px;}
	
</style>
<script type="text/javascript">
	$(function() {
		$("#btn_jump").click(function(){
			window.open('/${ctx}/person/${person.id}/yearchk/personchk/${colPerson.id}/comparesummary/${rowPerson.id}','_self');
		});
	});
</script>
</head>

<body>
<%@ include file="../../common/message.jsp"%>
<div style="text-align: center;">
<span style="font-size: 50px;">月工作总结对比 </span> 
</div>
<div style="margin-left: 5px;">
<input type="button"  value="&lt;-月工作总结对比" disabled="disabled"/>
<input type="button" id="btn_jump" value="年终工作总结对比-&gt;" />
</div>
<br>
<div class="all">
		<div class="head">${year}年${colPerson.name}月工作总结</div>
		
		<c:forEach var="colItem" items="${colItems}">
			<div class="context">
				<div class="month">${colItem.monthChk.month}月</div>
				<div class="task" >${colItem.sn}.${colItem.task}</div>
				<div class="none"></div>
			</div>
		</c:forEach>
</div>


<div class="all">
		<div class="head">${year}年${rowPerson.name}月工作总结</div>
		
		<c:forEach var="rowItem" items="${rowItems}">
			<div class="context">
				<div class="month">${rowItem.monthChk.month}月</div>
				<div class="task" >${rowItem.sn}.${rowItem.task}</div>
				<div class="none"></div>
			</div>
		</c:forEach>
</div>
</body>
</html>
