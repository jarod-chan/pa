<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<style type="text/css">
	.title_content{
		width:800px;
		margin-bottom: 20px;
		border-width: 1px;
		border-style: dashed ;           /* dashed ; */
		border-color:  #000000;
	}
	
	.title_content .title{
		margin: 5px;
	}
	
	.title_content .content{
		margin: 0px 5px 5px 5px;
	}
	
	.title_content .content .content_text{
		width: 785px;
		height:450px;
	}
	
	.all_head{
		font-size:40px;
		font-weight: bold;
		text-align: center;
		width: 800px;
	}
	
	.title_info{
		display: none;
		color:red;
		font-weight: bold;
	}
	
	
</style>


<script type="text/javascript">
$(function() {
	$(".content_text").bind("keyup",function(){
		$(this).parents(".title_content").find(".title_flag").val("false");
	})
	
	$("#btn_save").click(function(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action"); 
		actionFrom.attr("action",oldAction+"/save").submit();
	});
	
	$("#btn_commit").click(function(){
		var content_text=$(".content_text");
		for(var i=0;i<content_text.size();i++){
			val=content_text.eq(i).val();
			if(Trim(val)==""){
				alert("所有内容必须填写！");
				return;
			}
		}
		if(confirm("提交以后，你的总结将无法修改,确定提交?")){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action"); 
			actionFrom.attr("action",oldAction+"/commit").submit();
		}
	});
	
	//每5分钟执行自动保存
	setInterval("autosave()",1000*60*5);
	//文本最大长度控制在1000以内
	$(".content_text").attr({"maxlength":"1000"}).iemaxlength();
});

/*ajax 请求状态， 默认为false,出错为true*/
var errorStatus=false;

function errorDeal() {
	if(errorStatus==false){
		errorStatus=true;
		alert("页面出错，请重新登录！");
		logout();
	} 
}

function autosave(){
	var title_flag=$(".title_flag");
	var content_id=$(".content_id");
	var content_titleId=$(".content_titleId");
	var content_personsummaryId=$(".content_personsummaryId");
	var content_text=$(".content_text");
	title_flag.each(function(index,t_flag){
		if($(t_flag).val()=="false"){
			$.post("/${ctx}/${urlRole}/${person.id}/summary/content/save",
					{
					  id:content_id.eq(index).val(),
					  titleId:content_titleId.eq(index).val(),
					  personsummaryId:content_personsummaryId.eq(index).val(),
					  content:content_text.eq(index).val()
					},
					function(data){
						if(data=="success"){
							$(".title_info").eq(index).show(2000);
							setTimeout(function(){$(".title_info").eq(index).hide();},5000);
							title_flag.eq(index).val("true");
						}else{
							errorDeal();							
						}
					}
			).error(errorDeal);
		}
	});
	
}
	
	
</script>
</head>

<c:set target="${pagefunc}" property="name" value="个人述职报告" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/${urlRole}/${person.id}/summary" />  

<c:set var="pagesize" value="1010" scope="request"/> 
<body> 
<!-- <input type="button" value="测试" onclick="autosave()">  -->

<%@ include file="../common/message.jsp"%>

<form  action="/${ctx}/${urlRole}/${person.id}/summary" method="post">
<input name="id" type="hidden" value="${personSummary.id}" /> 
<input name="year" type="hidden" value="${personSummary.year}" /> 
<input name="personId" type="hidden" value="${personSummary.personId}" /> 

<div class="all">
	<div class="all_head">${personSummary.year}年度个人述职报告</div>
	<c:forEach var="titleContent" items="${personSummary.titleContents}" varStatus="status">
		<div class="title_content">
			<div class="title">
				${titleContent.title.no}.${titleContent.title.title}<span style="color:red;">(必填，1000字以内)</span>
				<span  class="title_info" >草稿已经自动保存！</span>
				<input class="title_flag" type="hidden" value="true" /> 
			</div>
			<div class="content">
				<input    class="content_id"  name="contents[${status.index}].id" type="hidden" value="${titleContent.content.id}" /> 
				<input 	  class="content_titleId" name="contents[${status.index}].titleId" type="hidden" value="${titleContent.title.id}" /> 
				<input    class="content_personsummaryId"  name="contents[${status.index}].personsummaryId" type="hidden" value="${personSummary.id}" /> 
				<textarea class="content_text"  name="contents[${status.index}].content" >${titleContent.content.content}</textarea>
			</div>
		</div>
	</c:forEach>
</div>

<br/>
<input type="button" id="btn_save"   value="保存年终总结" />
<input type="button" id="btn_commit" value="提交年终总结" />
</form>

</body>
</html>
