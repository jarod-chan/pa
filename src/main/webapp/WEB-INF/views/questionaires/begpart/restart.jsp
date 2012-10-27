<%@ page language="java" pageEncoding="UTF-8"%>
<div style="margin-left:20px;margin-top: 50px;">
<%@ include file="../common/message_nohide.jsp"%>
	<div>
	1.你已经完成本次调查，直接点击【安全退出】按钮退出。<br>
	2.如果你想修改部分结果，请点击【重新调查】按钮，重写填写。<br>
	3.请保证最后你完成本次调查，否则你的答案被视为无效。（你填写完问卷最后一页时，系统将出现一个【完成】按钮，点击后即可完成）。
	</div>
	<div style="text-align:right; width: 600px;margin-top: 20px;">
		<button id="btn_restart" type="button" >重新调查</button>&nbsp;&nbsp;<button id="btn_out" type="button" >安全退出</button>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("#btn_restart").click(function(){
			$('<form/>',{action:'/${ctx}/qs/start',method:'post'})
 		 	.appendTo($("body"))
 		 	.submit();
		});
		
		$("#btn_out").click(function(){
			$('<form/>',{action:'/${ctx}/qs/logout',method:'post'})
 		 	.appendTo($("body"))
 		 	.submit();
		});
	});
</script>

