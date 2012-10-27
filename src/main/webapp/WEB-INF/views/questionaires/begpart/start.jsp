<%@ page language="java" pageEncoding="UTF-8"%>
<div style="margin-left:20px;margin-top: 50px;">
<h2>协议条款：</h2>
<%@ include file="../common/message_nohide.jsp"%>
	<div>
	1.30分钟考核完成<br>
	2.认真填写
	</div>
	<div style="text-align:right; width: 600px;margin-top: 20px;">
		<input id="check" type="checkbox"> 我已认真阅读相关条款，同意参与本次调查。<button id="begques" type="button" disabled="disabled">开始调查</button>
	</div>
</div>
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
		$('<form/>',{action:'/${ctx}/qs/start',method:'post'})
		 	.appendTo($("body"))
		 	.submit();
	});
});
</script>

