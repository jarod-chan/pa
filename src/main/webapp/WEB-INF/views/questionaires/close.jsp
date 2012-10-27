<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#btn_out").click(function(){
			$('<form/>',{action:'/${ctx}/qs/logout',method:'post'})
 		 	.appendTo($("body"))
 		 	.submit();
		});
	});
</script>
</head>
<body>
<div style="margin-left:20px;margin-top: 50px;">
<%@ include file="common/message_nohide.jsp"%>
<div>
		你输入的认证码已经通过验证，但是【${ques.name}】已经结束，你无法进行其它操作！
</div>
<div style="width: 600px;text-align: right;margin-top: 10px;">
	<input type="button" id="btn_out" value="安全退出">
</div>
<br>

</div>
</body>
</html>
