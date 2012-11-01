<%@ page language="java" pageEncoding="UTF-8"%>
<div style="margin-left:20px;margin-top: 30px;width: 600px;">
<%@ include file="../common/message_nohide.jsp"%>
	<div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了更好地推行卓越绩效管理体系，为公司各项人事政策与企业决策提供参考依据，进而更大程度满足员工需求，特开展2012年员工满意度调查，希望各位员工积极参与。<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调查说明：<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、此次调查采取匿名方式，问卷中涉及的信息将严格保密，请放心填写；<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、此问卷每题有两个维度：<br>
	<div style="margin-left: 80px;">
	第一个维度是被查者对问题本身的满意情况；<br>
	第二个维度是被查者对问题的关注程度。<br>
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、请您按实际情况填写以确保数据的真实性。<br>
	
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

