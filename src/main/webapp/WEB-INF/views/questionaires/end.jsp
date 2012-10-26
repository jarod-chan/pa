<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#check").bind("click",function(){
			if($(this).attr("checked")){
				$("#begques").removeAttr("disabled");
			}else{
				$("#begques").attr("disabled","disabled");
			}
		});
		$("#begques").click(function(){
			window.open('/pa/qs/part/${part.id}','_self');
		});
	});
</script>
</head>
<body>
<div style="margin-left:20px;margin-top: 50px;">
<%@ include file="common/message_nohide.jsp"%>
<div>
		调查问卷已经完成，感谢你的参与！
</div>
<br>

</div>
</body>
</html>
