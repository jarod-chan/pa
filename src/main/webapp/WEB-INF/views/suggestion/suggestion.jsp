<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>

<html>
<head>
<%@ include file="../common/head.jsp"%>
<style type="text/css">
	div.all{
		width:622px;
		margin-bottom:20px;
		border-width:2px;
		border-style:solid ;           /* dashed ; */
		border-color:  #000000;
	}
	
	div.all p{
		margin: 0px;
		text-indent: 10px;
	}
	
	div.head{
		font-size:20px;
		border-bottom: 1px solid #000000;
	}
	div.head p{
		text-indent: 40px;
	}
	
	
	
	div.toolbar{
		overflow: hidden;
	}	
	div.toolbar div{
		display:inline-block;
		width: 50%;
		float: left;
		padding: 3px 0px;
	}
	div.toolbar .tb_right{
		text-align: right;
	}
	div.toolbar .tb_right input{
		margin-right: 10px;
	}
	
	
	div.context {
		overflow: hidden;
	}
	
	div.context .content_text{
		border: 1px dashed #000000;
	    margin: 10px;
	    width: 600px;
	    height: 300px;
	}
	
	.not_enough{
	 	display: none;   
		color:#FFFFFF;
		font-weight: bold;
		background-color: #8080FF;
		padding: 3px 5px;
	}
	
	.bd_top{
		border-top: 1px solid #000000;
	}
	.bd_bottom{
		border-bottom: 1px solid #000000;
	}
	.context_view{
		padding: 10px;
	}
	.context_view p{
		text-indent: 50px;
	}
	.mg_right_10{
	margin-right: 10px;
	}
	
</style>
<script type="text/javascript">
	$(function(){
		$("#btn_commit").click(function(){
			var content_text=$(".content_text");
			val=content_text.val();
			var len=Trim(val).length;
			if(len<5){
				$(this).prev().show();
				setTimeout(function(){
					$(".not_enough").hide();
				},5000);
				return;
			}
			$("form").submit();
		});
		$(".content_text").attr({"maxlength":"512"}).iemaxlength();
		
		$(".btn_delete").click(function(){
			var btn=$(this);
			btn.hide().prev().show();
			var id=btn.data("id");
			$.post("/${ctx}/suggestion/delete",{id:id},function(data){
				 if(data===true){
					btn.closest(".all").remove();
				} 
			});
		})
	})
</script>
</head>

<c:set target="${pagefunc}" property="name" value="意见建议" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/suggestion" />  
<c:set var="pagesize" value="960" scope="request"/> 

<body> 
<%@ include file="../common/message.jsp"%>

<form  action="/${ctx}/suggestion/save" method="post">
<div class="all">
	<div class="head">
		<strong>尊敬的方远房产员工：</strong>
		<p>您好！如果您对系统有任何的意见和建议，都可以在此留言。</p>
	</div>
	<div class="context">
		<textarea class="content_text"  name="content" ></textarea>
	</div>
	
	<div class="toolbar">
		<div>
			<p>24小时之内，您都可以删除自己的留言。</p>
		</div>
		<div class="tb_right">
			<span class="not_enough" >字数不够！</span>
			<input type="button" id="btn_commit" value="提交" />
		</div>
	</div>
</div>

<c:forEach var="sugt" items="${suggestionList}">
<div class="all">
	<div class="toolbar bd_bottom">
		<div>
			<p>提交时间:<fmt:formatDate  value="${sugt.createtime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></p>
		</div>
		<div class="tb_right" style="height: 20px;">
				<span class="not_enough mg_right_10" >删除中···</span>
				<c:if test="${!sugt.overtime}">
				<input type="button" class="btn_delete" value="删除"  data-id="${sugt.id}" />
				</c:if>
		</div>
	</div>
	<div class="context context_view">
		<p>${sugt.content}</p>
	</div>
</div>
</c:forEach>

</form>

</body>
</html>
