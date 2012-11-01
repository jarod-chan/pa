<%@ page language="java" pageEncoding="UTF-8"%>
<div style="margin-left:20px;margin-top: 30px;width: 600px;">
<%@ include file="../common/message_nohide.jsp"%>
	<div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了更好地推进卓越绩效管理体系，为公司政策与高层决策提供参考依据，更好地推进人力资源管理工作，特开展2012年度员工满意度调查，希望公司全体员工积极参与，主张自身的意愿和权利。<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本次调查采取匿名方式，请按照您的意愿认真填写：<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、本次调查是2012年度例行性员工满意度调查工作，并不针对特定群体和事件；<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、问卷共分五个部份，共计76个问题和3个书面建议，全部回答76个问题后问卷有效；<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、每个问题需要回答2个结果：满意情况和权重程度；<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、调查没有涉及的情况可以在调查问卷最后的书面建议部分说明；<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5、调查结果将作为公司决策依据，您的参与将会提升公司的管理；<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6、如有不适问题，可放弃调查或直接联系办公室反映；<br>
	
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

