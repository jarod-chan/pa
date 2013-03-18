<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%> 

 <script type="text/javascript">
 $(function() {
 	$('.btn_deploy').click(function(){
 		var param=jQuery.parseJSON($(this).attr("param"));
 		$('<form/>',{action:'/${ctx}/admin/workflow/deploy/'+param.filename+'/',method:'post'})
 			.appendTo($("body"))
 			.submit();
 	});
 });
 </script>
 
</head>

<body class="tbody">
	<h2>流程文件</h2>
	<%@ include file="../../common/message.jsp"%> 
	
	<table border="1">
		<thead>
			<tr>
				<th >流程文件</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${processFiles}" var="file">
				<tr>
					<td>${file.name}</td>
					<td><button class="btn_deploy" param='{"filename":"${file.name}"}'>部署流程</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<input type="button" onclick="window.open('/pa/admin/all','_self');" value="&lt;&lt;返回">
</body>

</html>
