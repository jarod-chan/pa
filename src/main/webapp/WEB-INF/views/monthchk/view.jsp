<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<style type="text/css">
	.currRow{
		background-color:#77BBFF;
	}
</style>
<script type="text/javascript">	
	var rowClick=function (){
		$("#tbl tr").removeClass("currRow");
		$(this).addClass("currRow");
	};
	
	$(document).ready(function() {
		$("#tbl tr").click(rowClick);
	});
</script> 
</head>
<c:set target="${pagefunc}" property="name" value="月度工作任务" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/person/${monthChk.person.id}/monthchk" />  


<c:set var="pagesize" value="990" scope="request"/> 
<body>
<div class="headdiv" >
<div class="headleft" >考核周期:${monthChk.year}年${monthChk.month}月&nbsp;&nbsp;考核状态:${monthChk.state.name}&nbsp;&nbsp;部门:${monthChk.person.department}&nbsp;&nbsp;上级主管:${mange.name}</div>
<div class="headright">
</div>
<div  class="headnone"></div>
</div>
<%@ include file="../common/message.jsp"%>


<style type="text/css">
	#summaryinfo{
		position:fixed;
		left:400px;
		top:200px;
		background-color: #FFFFFF;
		border: 2px solid #FF0000;
		padding: 5px;
		text-align: center;
		height: 100px;
	}
	#summaryinfo #summaryinfo_info{
		font-weight: bold;
		font-size: 30px;
		padding:10px 0px 10px 0px;
	}
</style>
<script type="text/javascript">
	$(function() {
		$("#summaryinfo_begin").click(function(){
			window.open('/${ctx}/person/${monthChk.person.id}/summary','_self');
		});
		$("#summaryinfo_next").click(function(){
			$("#summaryinfo").hide();
		});
	})
</script>
<div id="summaryinfo">
	<div id="summaryinfo_info">请您完成2012年个人述职报告！</div>
	<div id="summaryinfo_button">
		<input type="button" id="summaryinfo_begin" value="立即开始">
		<input type="button" id="summaryinfo_next"  value="下一次">
	</div>
</div>

<table border=1 style="table-layout:fixed;width:950px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:100px;">工作性质</th>
		<th style="width:600px;">工作内容</th>
		<td style="width:50px;">用时</td>
		<th style="width:150px;">评价</th>
	</tr>
</thead>
<tbody id="tbl">
	<c:forEach var="item" items="${monthChk.monthChkItems}">
		<tr>
			<td>
				${item.sn}
			</td>
			<td>
				${item.workType.worktype}
			</td>
			<td>
				${item.task}
			</td>
			<td>
				${item.workhour}
			</td>
			<td>
			<c:if test="${monthChk.state=='FINISHED'}">
			<c:choose>
     			<c:when test="${item.point=='5'}">优秀</c:when>
     			<c:when test="${item.point=='4'}">良好</c:when>
     			<c:when test="${item.point=='3'}">尽职</c:when>
     			<c:when test="${item.point=='2'}">需改进</c:when>
     			<c:when test="${item.point=='1'}">差</c:when>
			</c:choose>
			</c:if>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>


</body>
</html>
