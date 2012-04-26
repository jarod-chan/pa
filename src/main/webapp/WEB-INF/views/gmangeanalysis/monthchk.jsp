<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<meta name="decorator" content="none"/>
<%@ include file="../common/head.jsp"%>
<style type="text/css">
ul{list-style-type:none; margin:0;padding:0;width:100%; }
ul li{ width:100px; float:left;background-color:#FFFFFF;margin: 4px;border: 1px solid #97CBFF; }
</style>
<script type="text/javascript">
	var swithShow=function(){
		if($(this).is(".swithon")){
			$("li.finished").hide();
			$(this).val("显示全部人员");
			$("td").has("ul").not(":has(li:visible)").each(function(){
					$(this).hide();		
			});
		}else{
			$("li.finished").show();
			$(this).val("过滤已完成人员");
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
<h2>公司员工月度工作概况</h2>
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
		<th>公司员工${queryBean.year}年${queryBean.month}月工作概况</th>
	</tr>
</thead>
		<tbody>
			<tr>
				<td>
				<div style="width:50%;float: left;">
				合计参与考核员工${analysisMonthChkBean.totalNum}人,<c:choose><c:when test="${analysisMonthChkBean.totalNum==analysisMonthChkBean.finishNum}">全部完成考核。</c:when><c:otherwise>${analysisMonthChkBean.finishNum}个已完成。</c:otherwise></c:choose>
				</div>
				<div style="width:50%;float:left;text-align: right;">
				<input id="swithbtn" type="button" value="过滤已完成人员" class="swithon" /> 
				</div>
				</td>
			</tr>
			<c:forEach var="item" items="${analysisMonthChkBean.analysisDepartmentBeans}">
				<tr>
					<td>${item.departmentName}  共${item.totalNum}人,<c:choose><c:when test="${item.totalNum==item.finishNum}">全部完成考核。</c:when><c:otherwise>${item.finishNum}个已完成。</c:otherwise></c:choose></td>
				</tr>
				<tr>
					<td>
					<ul>
						<c:forEach var="list" items="${item.personMonthChkStateBeans}">
							<li class="${fn:toLowerCase(list.state)} ">${list.personName}:${list.state.name}</li>
						</c:forEach>
					</ul>
					</td>
				</tr>
			</c:forEach>
		</tbody>
</table>
</body>
</html>
