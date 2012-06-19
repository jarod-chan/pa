<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>

<%@ include file="../common/head.jsp"%>
<style type="text/css">
tbody ul{list-style-type:none; margin:0;padding:0;width:100%; }
tbody ul li{ width:100px; float:left;background-color:#FFFFFF;margin: 4px;border: 1px solid #97CBFF; }

tbody ul li a{text-decoration: none;color:#000000;white-space:nowrap;}
tbody ul li a:HOVER{background-color: #5BADFF;}
tbody ul li a:VISITED{color: #000000;}
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
	
	function monthchk(monthchkId){
		if(monthchkId=='') return;
		OpenEnvDefineWin("/${ctx}/gmange/${person.id}/analysis/monthchk/"+monthchkId,840,600);
	}
	
	$(document).ready(function() {
		$("#swithbtn").bind("change",selchange);
	});
</script>
</head>
<c:set target="${pagefunc}" property="name" value="公司考核情况查询" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/gmange/${person.id}/totalreport" />  

<c:set target="${pagetitle}" property="name" value="公司员工月度工作概况" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/gmange/${person.id}/analysis/monthchk" /> 

<body>
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
<table border=1 style="table-layout:fixed;width:810px;" class="tbldef">
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
					<select id="swithbtn">
						<option value="all">所有员工</option>
						<c:forEach var="item" items="${stateEnum}">
							<option value="${fn:toLowerCase(item)}">${item.name}</opiton>
						</c:forEach>
					</select> 
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
							<li class="${fn:toLowerCase(list.state)} ">
								<c:choose>
									<c:when test="${not empty list.monthchkId}">
										<a href="javascript:void(0);" onclick="monthchk('${list.monthchkId}')"> ${list.personName}:${list.state.name}</a>
									</c:when>
									<c:when test="${empty list.monthchkId}">
										 ${list.personName}:${list.state.name}
									</c:when>
								</c:choose>
							</li>
						</c:forEach>
					</ul>
					</td>
				</tr>
			</c:forEach>
		</tbody>
</table>
</body>
</html>
