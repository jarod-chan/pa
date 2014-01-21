<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>
<style type="text/css">
	.currRow{
		background-color:#77BBFF;
	}
</style>
<script type="text/javascript">

	var rowClick=function (){
		$(".tbldef tbody tr").removeClass("currRow");
		$(this).addClass("currRow");
	};
	
	var optColorArr=["#FF8080","#FECF78","#B9EA48","#00C462","#1E8EFF"];
	
	$(document).ready(function() {
		$(".tbldef tbody tr").click(rowClick);
		$(".tbldef tbody select[name='monthChkItems_point']").each(function(){
			$(this).find("option").each(function(idx){
				$(this).css("background-color",optColorArr[idx]);
			});
			$(this).bind("change",function(){
				$(this).css("background-color",optColorArr[this.selectedIndex])
			}).triggerHandler("change");
		});
	});

	function save(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		$(".tbldef tbody").formatName();
		actionFrom.attr("action",oldAction+"/save").submit();
	}
	
	function finish(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		var msg="单据完成以后，将无法撤销，确定完成？";
		if(confirm(msg)){
			$(".tbldef tbody").formatName();
			actionFrom.attr("action",oldAction+"/finish").submit();
		}
	}
	
	function back(){
		var oldAction=$("#monthChk").attr("action");
		var msg="单据打回以后，将交由提交人重新修改，确定打回？";
		if(confirm(msg)){
			$("#monthChk").attr("action",oldAction+"/back").submit();
		}
	}
</script> 
</head>

<c:set target="${pagefunc}" property="name" value="月度小结评价" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/monthsmy/manage" />  

<c:set target="${pagetitle}" property="name" value="员工月度小结评价" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/monthsmy/manage/${monthChk.id}" /> 

<c:set var="pagesize" value="990" scope="request"/>  

<body>

<div class="headdiv" >
<div class="headleft" >
部门:${mange.department}&nbsp;&nbsp;考核员工:${monthChk.person.name}&nbsp;&nbsp;考核周期:${monthChk.year}年${monthChk.month}月&nbsp;&nbsp;考核状态:${monthChk.state.name}
</div>
<div class="headright">
</div>
<div  class="headnone"></div>
</div>


<%@ include file="../../common/message.jsp"%>

<form id="monthChk" action="/${ctx}/monthsmy/manage/${monthChk.id}" method="post">

<table class="tbldef" border=1 style="table-layout:fixed;width:950px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:100px;">工作性质</th>
		<th style="width:600px;">工作内容</th>
		<th style="width:50px;">用时</th>
		<th style="width:150px;">评价</th>
	</tr>
</thead>
<tbody>
	<c:forEach var="item" items="${monthChk.monthChkItems}">
		<tr>
			<td style="display:none">
				<input type="text" name="monthChkItems_id"   value="${item.id}" />
			</td>
			<td>
				${item.sn}
			</td>
			<td>
				${item.workType.worktype}
			</td>
			<td>
				${item.task}
			</td>
			<td>
				${item.workhour}
			</td>
			<td>
				<select name="monthChkItems_point" >
					<option value="5" <c:if test="${item.point=='5'}">selected="true"</c:if> >优秀</option>
					<option value="4" <c:if test="${item.point=='4'}">selected="true"</c:if> >良好</option>
					<option value="3" <c:if test="${item.point==null||item.point=='3'}">selected="true"</c:if> >尽职</option>
					<option value="2" <c:if test="${item.point=='2'}">selected="true"</c:if> >需改进</option>
					<option value="1" <c:if test="${item.point=='1'}">selected="true"</c:if> >差</option>
				</select>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
<br/>
<input type="button" value="保存"  onclick="save()"/>
<input type="button" value="完成评价"  onclick="finish()"/>
<input type="button" value="打回员工修改"  onclick="back()"/>
<input type="button" value="<<返回"  onclick="javascript:window.open('/${ctx}/monthsmy/manage','_self')"/>
</form>

</body>
</html>
