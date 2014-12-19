<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>

<head>
<%@ include file="../../common/head.jsp"%>

<script type="text/javascript">
	function save(){
		var actionFrom=$("form");
		$("div.month").formatName();
		actionFrom.submit();
	}
	
	$(document).ready(function() { 
		$(":checkbox.checkinput").bind("click",clickCheckBox);
	});
	
	var clickCheckBox=function(){
		if($(this).is(':checked')){
			$(this).next().val(true);
		}else{
			$(this).next().val(false);
		}
	}
	
</script>

<style type="text/css">
	div.context {
		margin: 10px 5px;
		border: 2px solid #000000;
		height: 100%;
		width: 630px;
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
	
	div.context  .input .title{
		margin-top: 5px;
	}
	
	div.context  .input .month{
		margin-bottom: 5px;
	}
	

	
</style>

</head>
<body>
<h2>部门KPI计划</h2>
<br/>
<a href="/${ctx}/admin/deptkpi/${previewPage.year}">部门KPI列表</a>
&gt;<a href="/${ctx}/admin/deptkpi/${previewPage.year}/department/${previewPage.department.id}/planmonth">${previewPage.department.name}KPI计划</a>
<br/>
<br/>
年度:${previewPage.year}&nbsp;&nbsp;部门:${previewPage.department.name}<br/>
<%@ include file="../../common/message.jsp"%>

<form action="/${ctx}/admin/deptkpi/${previewPage.year}/department/${previewPage.department.id}/planmonth" method="post">
<table border="1" class="tbldef" >
		<thead>
			<tr>
				<th>序号</th>
				<th style="width:650px;">公司KPI指标分解</th>
			</tr>
		</thead>
		<tbody>
	  		 <c:forEach var="item" items="${previewPage.previewItems}" varStatus="status">
				<tr>
					<td rowspan="2">
						${status.count}
					</td>
					<td>
						公司KPI指标:${item.idrCompany.context }
					</td>
				</tr>
				<tr>
					<td>
						<c:forEach var="item2" items="${item.deptKpiItems}">
							<div class="context">
								<div class="view">
									<div class="ft_left">${item2.sn}.${item2.context}</div>
									<div class="ft_right">分值:${item2.point}</div>
									<div class="none"></div>
								</div>
								<div class="input">
									<div class="title">计划月份:</div>
									<div class="month">
										<c:forEach var="monthcheck" items="${item2.monthCheck}" varStatus="mcstatus">
											<input type="checkbox" class="checkinput"  <c:if test="${monthcheck}">checked="checked"</c:if> ><input type="hidden" name="deptKpiItems_monthCheck" value="${monthcheck}">${mcstatus.count}月
										</c:forEach>
									</div>
								</div>
							</div>
						</c:forEach> 
					</td>
				</tr>
			</c:forEach> 
		</tbody>
</table>

<br/>
<input type="button" value="保存" onclick="save()"/>
</form>
</body>
</html>
