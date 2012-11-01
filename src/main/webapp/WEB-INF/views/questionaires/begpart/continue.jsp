<%@ page language="java" pageEncoding="UTF-8"%>
<div style="margin-left:20px;margin-top: 30px; width: 600px;">
<%@ include file="../common/message_nohide.jsp"%>
	<div>
     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你上次已经填写部分调查表（已填写部分被保留了下来），但是没有最后完成，你可以点击下方【继续完成调查】按钮，完成本次调查。
	</div>
	<div style="text-align:right; width: 600px;margin-top: 20px;">
		<button id="btn_continue" type="button" >继续完成调查</button>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("#btn_continue").click(function(){
			$('<form/>',{action:'/${ctx}/qs/start',method:'post'})
 		 	.appendTo($("body"))
 		 	.submit();
		});
	});
</script>

