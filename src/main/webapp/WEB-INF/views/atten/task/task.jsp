<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%> 

 	<script type="text/javascript">
	    $(function() {
	    	$('.btn_execute').click(function(){
	    		var param=jQuery.parseJSON($(this).attr("param"));
	    		var url=param.formKey.replace('{personId}',${person.id}).replace('{businessId}',param.businessId);
	    		$('<form/>',{action:'/${ctx}/'+url})
	    			.append($('<input/>',{type:'hidden',name:'taskId',value:param.taskId}))
					.appendTo($("body"))
				.submit();
	    	});
	    });
    </script>


</head>
<c:set target="${pagefunc}" property="name" value="任务中心" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/atten/${person.id}/task/list" />  

<body class="tbody">
		<h2>任务中心</h2>
		<%@ include file="../../common/message.jsp"%> 

		<table border="1">
			<thead>
				<tr>
					<th style="width: 300px;">业务流程</th>
					<th style="width: 300px;">待办任务</th>
					<th style="width: 100px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="processTask" items="${processTasks}">
					<tr>
						<td>${processTask.processName}</td>
						<td>${processTask.taskName}</td>
						<td>
							<button class="btn_execute" param='{"taskId":"${processTask.taskId }","formKey":"${processTask.formKey}","businessId":"${processTask.businessId}"}'>处理</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


</body>
</html>