<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
<script type="text/javascript">
	$(function(){
		$(".produceKey").click(function(){
			var ipt=$(this);
			$('<form/>',{action:'/${ctx}/admin/ques/produceKey',method:'post'})
			.append($("<input type='hidden' name='qtid'/>").val(ipt.next().val()))
			.append($("<input type='hidden' name='keyNum'/>").val(ipt.next().next().val()))
 		 	.appendTo($("body"))
 		 	.submit();
		});
		
		$(".finishQues").click(function(){
			var ipt=$(this);
			$('<form/>',{action:'/${ctx}/admin/ques/finish',method:'post'})
			.append($("<input type='hidden' name='qtid'/>").val(ipt.next().val()))
 		 	.appendTo($("body"))
 		 	.submit();
		});
		
		$("#btn_recover").click(function(){
			var btn=$(this);
			$('<form/>',{action:'/${ctx}/admin/ques/recover',method:'post'})
			.append($("<input type='hidden' name='keystr'/>").val(btn.prev().val()))
 		 	.appendTo($("body"))
 		 	.submit();
		})
	});
</script>
</head>
<body>
<h2>满意度调查情况</h2>
<%@ include file="common/message_nohide.jsp"%>

<div>
	输入你要你要回收的key:<input type="text" value="" />&nbsp;&nbsp;<input id="btn_recover" type="button" value="回收">
</div>
<br>
<table border="1" class="tbldef" >
		<thead>
			<tr>
				<th>调查问卷</th>
				<th>有效key</th>
				<th>未完成Key</th>
				<th>已完成Key</th>
				<th>回收Key</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
	  		<c:forEach var="quesBean" items="${quesBeanList}" >
			<tr>
				<td>${quesBean.ques.name}</td>
				<td>${quesBean.valid}</td>
				<td>${quesBean.notFinish}</td>
				<td>${quesBean.finish}</td>
				<td>${quesBean.invalid}</td>
				<td><input class="produceKey" type="button" value="生成"><input type="hidden" value="${quesBean.ques.id}">:<input type="text" value="" size="5"/>个key</td>
				<td><input class="finishQues" type="button" value="完成"><input type="hidden" value="${quesBean.ques.id}"></td>
			</tr>
			</c:forEach> 
		</tbody>
</table>
<br>
<input type="button" onclick="window.open('/pa/admin/all','_self');" value="返回管理员页面<<">


</body>
</html>
