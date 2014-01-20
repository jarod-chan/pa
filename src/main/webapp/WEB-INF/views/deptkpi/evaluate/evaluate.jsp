<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>

<head>
<%@ include file="../../common/head.jsp"%>

<script type="text/javascript">
	function save(){
		var actionFrom=$("form");
		$("div.input").formatName();
		actionFrom.submit();
	}
</script>

<style type="text/css">
	div.context {
		margin: 10px 5px;
		border: 2px solid #000000;
		height: 100%;
		width: 600px;
	}
	
	div.context .none { clear:both; font-size:0px; height:0px; line-height:0px;}
	
	div.context  .view {
		border-bottom: 1px solid #000000;
		padding: 5px 0px;
	}
	
	div.context  .view .ft_left{
		float: left;
		width: 400px;
		padding-left: 10px;
	}
	
	div.context  .input div{
		padding-left: 10px;
	}
	
	div.context  .input .evaluate{
		margin-top: 5px;
	}
	
	div.context  .input .desc{
		margin-bottom: 5px;
	}
	
	div.context  .input .desc textarea{ 
		width: 550px;
		height: 150px;
	}
	
</style>
</head>
<body>
<h2>部门KPI评价</h2>
<br/>
<a href="/${ctx}/admin/deptkpi/${year}">部门KPI列表</a>
&gt;<a href="/${ctx}/admin/deptkpi/${year}/department/${department.id}/evaluate">${department.name}KPI评价列表</a>
&gt;<a href="/${ctx}/admin/deptkpi/${year}/department/${department.id}/idrcompany/${idrCompany.id}/evaluate">${department.name}KPI评价</a>
<br/>
<br/>
年度:${year}&nbsp;&nbsp;部门:${department.name}<br/>
公司KPI指标:${idrCompany.context}
<%@ include file="../../common/message.jsp"%>

<form action="/${ctx}/admin/deptkpi/${year}/department/${department.id}/idrcompany/${idrCompany.id}/evaluate" method="post">
<div>
	<c:forEach var="item" items="${deptKpiItems}">
			<div class="context">
				<div class="view">
					<div class="ft_left">${item.sn}.${item.context}</div>
					<div class="ft_right">分值:${item.point}</div>
					<div class="none"></div>
				</div>
				<div class="input">
					<div class="evaluate">评价结果:<input type="hidden" name="deptKpiItems_id" value="${item.id}"/>
						 <select name="deptKpiItems_result">
							<option value="5" <c:if test="${item.result=='5'}">selected="true"</c:if> >优秀</option>
							<option value="4" <c:if test="${item.result=='4'}">selected="true"</c:if> >良好</option>
							<option value="3" <c:if test="${item.result==null||item.result=='3'}">selected="true"</c:if> >尽职</option>
							<option value="2" <c:if test="${item.result=='2'}">selected="true"</c:if> >需改进</option>
							<option value="1" <c:if test="${item.result=='1'}">selected="true"</c:if> >差</option>
						  </select>
					</div>
					<div class="desc">评价说明:
						<br/>
						<textarea name="deptKpiItems_resultDesc">${item.resultDesc}</textarea>
					</div>
				</div>
			</div>
	</c:forEach>	
</div>

<input type="button" value="保存" onclick="save()"/>
</form>

</body>
</html>
