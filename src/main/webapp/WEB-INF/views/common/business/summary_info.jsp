<%@ page language="java" pageEncoding="UTF-8"%>

<c:if test="${show_pserson_summary}">

<style type="text/css">
	#summaryinfo{
		position:fixed;
		left:400px;
		top:200px;
		background-color: #FFFFFF;
		border: 2px solid #FF0000;
		padding: 5px;
		text-align: center;
		height: 100px;
	}
	#summaryinfo #summaryinfo_info{
		font-weight: bold;
		font-size: 30px;
		padding:10px 0px 10px 0px;
	}
</style>
<script type="text/javascript">
	$(function() {
		$("#summaryinfo_begin").click(function(){
			window.open('/${ctx}/${urlRole}/${loginRet.personid}/summary','_self');
		});
		$("#summaryinfo_next").click(function(){
			$("#summaryinfo").hide();
			$.post("/${ctx}/${urlRole}/${loginRet.personid}/${urlFunc}/hideSummaryInfo",function(){});
		});
	})
</script>
<div id="summaryinfo">
	<div id="summaryinfo_info">请您完成2012年个人述职报告！</div>
	<div id="summaryinfo_button">
		<input type="button" id="summaryinfo_begin" value="立即开始">
		<input type="button" id="summaryinfo_next"  value="下一次">
	</div>
</div>

</c:if>


