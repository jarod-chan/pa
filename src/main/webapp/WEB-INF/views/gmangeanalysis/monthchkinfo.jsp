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
	
	div.context .left{
		float:left;
		width:700px;
		border-right:  1px solid #000000;
		padding-left: 5px;
	}
	
	div.context .left .task{
		padding-top:5px;
		padding-bottom:5px;
	}
	
	div.context .left .other .worktype{
		padding-bottom:5px;
		width: 200px;
		float: left;
	}
	
	div.context .left .other .workhour{
		padding-bottom:5px;
		width:300px;
		float: left;
	}
	
	div.context .right{
		padding-top:10px;
		text-align:center;
		float:left;
		width:70px;
	}
	
	div.context .none { clear:both; font-size:0px; height:0px; line-height:0px;}
	
</style>
<script type="text/javascript">
	$(document).ready(function() {
		
	});
</script>
</head>

<body>
<%@ include file="../common/message.jsp"%>
<div class="all">
	<div class="head">${monthChk.year}年${monthChk.month}月份${monthChk.person.name}工作完成情况&nbsp;&nbsp;【${monthChk.state.name}】</div>
	<c:forEach var="chkitem" items="${monthChk.monthChkItems}">
		<div class="context">
			<div class="left">
				<div class="task">工作内容:${chkitem.task}</div>
				<div class="other">
					<div class="worktype">工作性质:${chkitem.workType.worktype}</div>
					<div class="workhour">用时:${chkitem.workhour}小时</div>
				</div>
			</div>
			<div class="right">
						<c:choose>
		    			<c:when test="${chkitem.point=='5'}">优秀</c:when>
		    			<c:when test="${chkitem.point=='4'}">良好</c:when>
		    			<c:when test="${chkitem.point=='3'}">尽职</c:when>
		    			<c:when test="${chkitem.point=='2'}">需改进</c:when>
		    			<c:when test="${chkitem.point=='1'}">差</c:when>
						</c:choose>
			 </div>
			 <div class="none"></div>
		</div>
	</c:forEach>
</div>
</body>
</html>
