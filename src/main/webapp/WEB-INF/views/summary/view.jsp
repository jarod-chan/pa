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
		overflow-y:scroll; 
		border: 1px solid #000000;
	}
	
	.all_head{
		font-size:40px;
		font-weight: bold;
		text-align: center;
		width: 800px;
	}

	
	
</style>



</head>

<c:set target="${pagefunc}" property="name" value="年终员工总结" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/person/${person.id}/summary" />  

<c:set var="pagesize" value="1010" scope="request"/> 
<body> 

<%@ include file="../common/message.jsp"%>



<div class="all">
	<div class="all_head">${personSummary.year}年年终总结</div>
	<c:forEach var="titleContent" items="${personSummary.titleContents}" varStatus="status">
		<div class="title_content">
			<div class="title">
				${titleContent.title.no}.${titleContent.title.title}
			</div>
			<div class="content">
				<div class="content_text"  >${titleContent.content.content}</div>
			</div>
		</div>
	</c:forEach>
</div>


</body>
</html>
