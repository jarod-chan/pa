<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>

<head>
<%@ include file="../common/head.jsp"%>

</head>
<body>
<h2>公司KPI分解</h2>
年度：${idrYearCompany.year}
<%@ include file="../common/message.jsp"%>

<table border="1" class="tbldef" >
		<thead>
			<tr>
				<th >序号</th>
				<th >编码</th>
				<th >类别</th>
				<th>指标类容</th>
				<th>量化指标</th>
				<th>工作标准</th>
				<th>计算方式</th>
				<th>考核周期</th>
				<th>权重</th>
				<th>类别权重</th>
				<th>实际权重</th>
			</tr>
		</thead>
		<tbody>
	  		 <c:forEach var="item" items="${idrYearCompany.idrCompany}">
			<tr>
				<td>
					${item.sn}
				</td>
				<td>
					${item.number}
				</td>
				<c:if test="${rowSpan[item.sn]!=null}">
					<td rowspan="${rowSpan[item.sn]}">
						${item.idrTypeWeight.idrType.name}
					</td>
				</c:if>
				<td>
					${item.context}
				</td>
				<td>
					${item.quantization}
				</td>
				<td>
					${item.standard}
				</td>
				<td>
					${item.computeMode}
				</td>
				<td>
					${item.period}
				</td>
				<td>
					${item.weight}
				</td>
				<c:if test="${rowSpan[item.sn]!=null}">
					<td rowspan="${rowSpan[item.sn]}">
						${item.idrTypeWeight.weight}
					</td>
				</c:if>
				<td>
					${item.realWeight}
				</td>
			</tr>
			</c:forEach> 
		</tbody>
</table>

</body>
</html>
