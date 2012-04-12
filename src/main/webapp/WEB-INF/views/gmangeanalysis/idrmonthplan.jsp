<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<style type="text/css">
ul{list-style-type:none; margin:0;padding:0;width:100%; }
ul li{ width:150px; float:left;background-color:#FFFFFF;margin: 4px;border: 1px solid #97CBFF; }
</style>
<script type="text/javascript">
	var swithShow=function(){
		if($(this).is(".swithon")){
			$("li.finished").hide();
			$(this).val("显示全部部门");
			$("td").has("ul").not(":has(li:visible)").each(function(){
					$(this).hide();		
			});
		}else{
			$("li.finished").show();
			$(this).val("过滤已完成部门");
			$("td:hidden").show();
		}
		$(this).toggleClass("swithon");
	}

	 $(document).ready(function() {
		$("#swithbtn").bind("click",swithShow);
	 });
</script>
</head>
<body>
<h2>公司部门月度工作概况</h2>
<form action="" method="get">
年份：
<select name="year">
	<c:forEach var="item" items="${dateTool.allYears}">
		<option value="${item}" <c:if test="${item==queryBean.year}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
月份：
<select name="month">
	<c:forEach var="item" items="${dateTool.allMonths}">
		<option value="${item}" <c:if test="${item==queryBean.month}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="查询" />
</form>
<%@ include file="../common/message.jsp"%>
<table border=1 style="table-layout:fixed;width:810px;">
<thead>
	<tr>
		<th>公司部门${queryBean.year}年${queryBean.month}月工作概况</th>
	</tr>
</thead>
		<tbody>
			<tr>
				<td>
				<div style="width:50%;float: left;">
				合计参与考核部门${analysisIdrMonthPlanBean.totalNum}人,<c:choose><c:when test="${analysisIdrMonthPlanBean.totalNum==analysisIdrMonthPlanBean.finishNum}">全部完成考核。</c:when><c:otherwise>${analysisIdrMonthPlanBean.finishNum}个已完成。</c:otherwise></c:choose>
				</div>
				<div style="width:50%;float:left;text-align: right;">
				<input id="swithbtn" type="button" value="过滤已完成部门" class="swithon" /> 
				</div>
				</td>
			</tr>
			<tr>
				<td>
				<ul>
					<c:forEach var="list" items="${analysisIdrMonthPlanBean.departmentIdrMonthPlanBillStateBeans}">
						<li class="${fn:toLowerCase(list.state)} ">${list.departmentName}:${list.state.name}</li>
					</c:forEach>
				</ul>
				</td>
			</tr>
		</tbody>
</table>
</body>
</html>
