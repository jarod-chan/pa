<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
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
		text-align:center;
		border-bottom: 1px solid #000000;
	}
	div.toolbar{
		border-bottom: 1px solid #000000;
		overflow: hidden;
	}
	
	div.toolbar div{
		display:inline-block;
		width: 50%;
		float: left;
		padding: 3px 0px;
	}
	
	div.context {
		overflow: hidden;
	}
	
	.context ul{list-style-type:none; margin:0;padding:0;width:100%; }
	.context ul li{ width:150px; float:left;background-color:#FFFFFF;margin: 4px;border: 1px solid #97CBFF; }
	
	.context ul li a{text-decoration: none;color:#000000;white-space:nowrap;}
	.context ul li a:HOVER{background-color: #5BADFF;}
	
	
</style>
<script type="text/javascript">

	var selchange=function(){
		var selval=$(this).val();
		if(selval=="all"){
			$(".tbldef li:hidden").show();
			$(".tbldef td:hidden").show();
		}else{
			$(".tbldef li:not(."+selval+")").hide();
			$(".tbldef li."+selval).show();
			$(".tbldef td").has("ul").each(function(){
				if($(this).is(":has(li."+selval+")")){
					$(this).show();
				}else{
					$(this).hide();
				}	
			});
		}
	}
	
	function monthplan(monthplanId){
		if(monthplanId=='') return;
		OpenEnvDefineWin("/${ctx}/report/analysis/idrmonthplan/"+monthplanId,860,600);
	}

	 $(function() {
		$("#swithbtn").bind("change",selchange);
		$("#queryform select").bind("change",function(){
			$("#queryform").submit();
		});
	 });
</script>
</head>

<c:set target="${pagefunc}" property="name" value="公司考核情况查询" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/totalreport" />  

<c:set target="${pagetitle}" property="name" value="公司部门月度工作概况" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/report/analysis/idrmonthplan" /> 

<body>

<form id="queryform" action="/${ctx}/report/analysis/idrmonthplan" method="post">
年份：
<select name="year">
	<c:forEach var="item" items="${dateTool.allYears}">
		<option value="${item}" <c:if test="${item==report_analysis_idrmonthplan.year}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
月份：
<select name="month">
	<c:forEach var="item" items="${dateTool.allMonths}">
		<option value="${item}" <c:if test="${item==report_analysis_idrmonthplan.month}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
</form>
<%@ include file="../common/message.jsp"%>

<div class="all">
	<div class="head">公司部门${report_analysis_idrmonthplan.year}年${report_analysis_idrmonthplan.month}月工作概况</div>
	<div class="toolbar">
		<div>
		合计参与考核部门${analysisIdrMonthPlanBean.totalNum}个,<c:choose><c:when test="${analysisIdrMonthPlanBean.totalNum==analysisIdrMonthPlanBean.finishNum}">全部完成考核。</c:when><c:otherwise>${analysisIdrMonthPlanBean.finishNum}个已完成。</c:otherwise></c:choose>
		</div>
		<div style="text-align: right;">
			<select id="swithbtn">
				<option value="all">所有部门</option>
				<c:forEach var="item" items="${stateEnum}">
					<option value="${fn:toLowerCase(item)}">${item.name}</opiton>
				</c:forEach>
			</select>
		</div>
	</div>
	
	<div class="context tbldef">
		<ul>
			<c:forEach var="list" items="${analysisIdrMonthPlanBean.departmentIdrMonthPlanBillStateBeans}">
				<li class="${fn:toLowerCase(list.state)} ">
					<c:choose>
						<c:when test="${not empty list.monthPlanBillId}">
							<a href="javascript:void(0);" onclick="monthplan('${list.monthPlanBillId}')">${list.departmentName}:${list.state.name}</a>
						</c:when>
						<c:when test="${empty list.monthPlanBillId}">
							${list.departmentName}:${list.state.name}
						</c:when>
					</c:choose>
				</li>
			</c:forEach>
		</ul>
	</div>

</div>

</body>
</html>
