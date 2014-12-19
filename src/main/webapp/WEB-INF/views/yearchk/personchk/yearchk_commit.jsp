<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

<style type="text/css">
.mainul{list-style-type:none; margin:0px 100px 0px 100px;padding:5px 0px 0px 5px; }
.mainul li { width:75px; float:left;margin: 0px 5px 5px 0px;text-align: center; border: 1px solid #000000;}
.win{
background-color: #FF8080;
}
.draw{
background-color: #FECF78;
}

.lose{
background-color: #1E8EFF;
}


</style>
</head>
<c:set target="${pagefunc}" property="name" value="年度横向评价" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/person/${person.id}/yearchk" />  


<c:set var="pagesize" value="960" scope="request"/> 
<body>

<div class="headdiv" >
<div class="headleft"  >
考核年份:${year}&nbsp;&nbsp;状态:已提交
</div>
<div class="headright" >
</div>
<div  class="headnone"></div>
</div>
<%-- <%@ include file="../../common/message.jsp"%> --%>


<div style="color: red;font-size: 30px; width: 850px;" >
	您已经成功提交了${year}年度员工横向评价结果，谢谢您的参与!
</div>

<table border=1  >
<thead>
	<tr>
		<th style="width: 80px;">员工</th>
		<th >您的员工横向评价结果统计</th>
	</tr>
</thead>
<tbody>
	<c:forEach var="item" items="${resultBeanList}">
	<tr>
		<td style="text-align: center;">
			${item.person.name}
		</td>
		<td >
		<ul class="mainul">
			<c:if test="${item.win>0}">
					<li class="win">胜:${item.win}</li>
			</c:if>
			<c:if test="${item.draw>0}">
					<li class="draw">平:${item.draw}</li>
			</c:if>
			<c:if test="${item.lose>0}">
				<li class="lose">负:${item.lose}</li>
			</c:if>

		</ul>
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>
</body>
</html>
