<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%> 
</head>
<script type="text/javascript">
	function remove(){
		 	
	}
</script>

<body>
<h2>指标类型</h2>
<%@ include file="../common/message.jsp"%> 
<input type="button" value="新增" onclick="javascript:window.open('/${ctx}/admin/idrtype/add','_self')"/>
<table border=1>
	<thead>
		<tr>
			<td>序号</td><td>编码</td><td>类别</td><td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" varStatus="status" items="${idrTypes}" >
		<tr>
			<td>${status.count}</td>
			<td>${item.number}</td>
			<td>${item.name}</td>
			<td>
				<input type="button" value="修改" onclick="javascript:window.open('/${ctx}/admin/idrtype/edit/${item.id}','_self')"/>
				<input type="button" value="删除" onclick="if(confirm('确定删除？')){
			 		$('<form/>',{action:'/${ctx}/admin/idrtype/delete/${item.id}',method:'post'}).
					appendTo($('body')).end().submit();
				} " />
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<br>
<input type="button" onclick="window.open('/pa/admin/all','_self');" value="返回管理员页面<<">
</body>
</html>
