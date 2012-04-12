<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table border=1 style="table-layout:fixed;width:800px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:500px;">工作完成情况表</th>
	</tr>
</thead>
		<tbody>
			<c:forEach var="item" items="${monthChks}" varStatus="status">
				<tr>
					<td rowspan="2">${status.count}</td>
					<td>
						${item.year}年${item.month}月份${item.person.name}工完成情况【${item.state.name}】
					</td>
				</tr>
				<tr>
					<td>
					
						<c:forEach var="chkitem" items="${item.monthChkItems}">
							<ul style="margin: 0px;padding: 0px;">
								<li style="list-style:none;clear: both;">&nbsp;</li>
								<li style="list-style:none;text-align:left;clear: both;">&nbsp;&nbsp;工作内容:${chkitem.task}</li>
								<li style="list-style:none;clear: both;">
									<div style="width:150px;float:left;">&nbsp;&nbsp;工作性质:${chkitem.workType.worktype}</div>
									<div style="width:150px;float:left;">&nbsp;&nbsp;用时:${chkitem.workhour}小时</div>
									<div style="width:150px;float:left;">评价:<c:choose>
						     			<c:when test="${chkitem.point=='5'}">优秀</c:when>
						     			<c:when test="${chkitem.point=='4'}">良好</c:when>
						     			<c:when test="${chkitem.point=='3'}">尽职</c:when>
						     			<c:when test="${chkitem.point=='2'}">需改进</c:when>
						     			<c:when test="${chkitem.point=='1'}">差</c:when>
									</c:choose>
									</div>
									
								</li>
							</ul>
						</c:forEach>
					
					</td>
				</tr>
			</c:forEach>
		</tbody>
</table>
