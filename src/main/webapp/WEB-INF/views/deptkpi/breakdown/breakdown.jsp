<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>

<head>
<%@ include file="../../common/head.jsp"%>

<style type="text/css">

	.currRow{
		background-color:#77BBFF;
	}
	
	.context{
		width: 900px;
		margin-top: 10px;
	}
	
	.context .head{
		border:solid #000000;
		border-width:1px 0px 1px 1px;
		float: left;
	}

	.context .head div{
		border:solid #000000;
		border-width:0px 1px 0px 0px;
		float:left;
		padding: 5px;
		font-weight:bold;
	}
	
	.none { clear:both; font-size:0px; height:0px; line-height:0px;}

	.subtab{
		margin: 0px;
		border-collapse:collapse; /* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */   
		border:solid #000000; /* 设置边框属性：样式(solid=实线)、颜色(#999=灰) */   
		border-width:0px 0 0 1px; /* 设置边框状粗细：上 右 下 左 = 对应：1px 0 0 1px */  
	}
	
	.subtab th, .subtab td {
		border:solid #000000;
		border-width:0 1px 1px 0;
		padding:2px;
	}  /* 设置表格每个td的边框， 只设置下侧和右侧的边框 */

</style>

 
<script type="text/javascript">

	var contextSize={"width":"500px"};
	var contextlength={"maxlength":"50"};
	var pointCss={"width":"80px"};
	var actionButtonCss={"float":"left","margin-left":"10px","width":"22px"};
	
	var tr = $("<tr>");
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='hidden' name='deptKpiItems_id'   value='' />"))
		.append($("<input type='hidden' name='deptKpiItems_sn'   value='' />"))
		.append($("<input type='hidden' name='deptKpiItems_idrCompany.id'   value='' />"))
		.append($("<input type='hidden' name='deptKpiItemsKey'   value='' />"))
		.css("display","none");
	
	tr.append("<td>");
	
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='deptKpiItems_context' />"));
		
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='deptKpiItems_point' />"));
		
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='button' class='add'  value='+'   />"))
		.append($("<input type='button' class='remove'  value='-'   />"))
		.append($("<input type='button' class='up' value='/\\'  />"))
		.append($("<input type='button' class='down'  value='\\/'  />"))
		.append("&nbsp;"); 
	
	
	$(document).ready(function() { 

		$(".subtab tbody tr").each(function(index,tr){
			bindRowActionAndCss($(tr));
		});
		
		$(".addLast").bind("click",addLastRow);
		$(".triggerBtn").bind("click",triggerBtn);

	});
	
	var rowClick=function (){
		$(".subtab tbody tr td:last-child :button").hide();
		$(this).find("td:last").find(":button").show();
		$(".subtab tbody tr").removeClass("currRow");
		$(this).addClass("currRow");
	};
	
	var addLastRow=function(){
		var tbody=$(this).parents("thead").next();
		var idrCompanyId=$(this).parents(".context").find("#idrCompanyId").val(); 
		tbody.append(cloneTR(idrCompanyId)); 
		reIndexTable(tbody);
	};
	
	function cloneTR(idrCompanyId){
		var newtr=tr.clone();
		bindRowActionAndCss(newtr);
		newtr.find(":input[name='deptKpiItems_idrCompany.id']").val(idrCompanyId);
		return newtr;
	}
	
	function bindRowActionAndCss(tr){
		tr.bind("click",rowClick)
	 	  .find(":input[name='deptKpiItems_context']").css(contextSize).attr(contextlength).end()
		  .find(":input[name='deptKpiItems_point']").css(pointCss).bind("blur",numberBlur).end();
		tr.find("td:last :button").css(actionButtonCss).hide()
		  .filter(".add").bind("click",add).end()
		  .filter(".remove").bind("click",remove).end()
		  .filter(".up").bind("click",up).end()
		  .filter(".down").bind("click",down).end();
	}
	

	
	function add() {
		var idrCompanyId=$(this).parents(".context").find("#idrCompanyId").val();
		rowAction($(this),function(tr){
			tr.after(cloneTR(idrCompanyId)); 
		})
	}

	function remove() {
		rowAction($(this),function(tr){
			tr.remove();
		})
	}

	function up() {
		rowAction($(this),function(tr){
			tr.prev().before(tr);
		})
	}

	function down() {
		rowAction($(this),function(tr){
			tr.next().after(tr);
		})
	}
	
	function rowAction(obj,funcAction){
		var tr = $(obj).parents("tr:eq(0)");
		tbody=tr.parents("tbody:eq(0)");
		funcAction(tr);
		reIndexTable(tbody);
	}
	
	function reIndexTable(tbody){
		var index=0;
		tbody.find("tr").each(function(){
			index++;
			$(this).find("td").eq(0).find("[name='deptKpiItems_sn']").val(index);			
			$(this).find("td").eq(1).html(index);
		});
		tbody.parents(".context").find(".head .breakitems").html("项数:"+index);
	}
	
	var numberBlur=function(){
		if(IsFloat($(this).val(),"+"))	{
			var point=hold($(this).val(),0);
			$(this).val(point);
		}else{
			$(this).val("");
		}
	};
	
	var triggerBtn=function(){
		var btn=$(this);
		var tabdiv=btn.parents(".context").find(".tabdiv");
		if(tabdiv.is(":visible")){
			tabdiv.slideUp(500).queue(function(next){
				btn.val("\\/");
				next();
			});
		}else{
			tabdiv.slideDown(500).queue(function(next){
				btn.val("/\\");
				next();
			});
		}
	}
	
	function save(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		$(".subtab tbody tr").formatName();
		actionFrom.attr("action",oldAction+"/save").submit();
	}
	
	function commit(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		$(".subtab tbody tr").formatName();
		var msg="确定提交？";
		if(confirm(msg)){
			actionFrom.attr("action",oldAction+"/commit").submit();
		}
	}

	

</script>

</head>
<body>
<h2>${listPage.department.name}KPI分解列表</h2>
<br/>
<a href="/${ctx}/admin/deptkpi/${listPage.year}">部门KPI列表</a>
&gt;<a href="/${ctx}/admin/deptkpi/${listPage.year}/department/${listPage.department.id}">${listPage.department.name}KPI分解列表</a>
<br/>
<br/>
年度：${listPage.year}&nbsp;&nbsp;部门:${listPage.department.name}

<%@ include file="../../common/message.jsp"%>

<form action="/${ctx}/admin/deptkpi/${listPage.year}/department/${listPage.department.id}" method="post">


<c:forEach var="item" items="${listPage.pageItems}" varStatus="status">
<div class="context">
	<div class="head">
		<div style="width: 549px;">公司KPI指标:${item.idrCompany.context}<input type="hidden" id="idrCompanyId"   value="${item.idrCompany.id}" /></div> 
		<div style="width: 84px;">
			<c:if test="${item.mustSelect}">
				必选
			</c:if>
			&nbsp;
		</div> 
		<div class="breakitems" style="width: 90px;">
			项数:${item.deptKpiItemNum}
		</div>
		<div style="width: 43px;padding: 0px;">
			<c:choose>
				<c:when test="${item.deptKpiItemNum==0}">
					 <input type="button" value="\/" style="height:27px;width:43px; "  class="triggerBtn"/>
				</c:when>
				<c:otherwise>
					 <input type="button" value="/\" style="height:27px;width:43px; "  class="triggerBtn"/>
				</c:otherwise>
			</c:choose>
			
		</div>
	</div>
	<div class="none"></div>
	<div class="tabdiv"  <c:if test="${item.deptKpiItemNum==0}">style="display:none"</c:if>>
		<table class="subtab" >
			<thead>
				<tr>
					<th style="display:none">id</th>
					<th style="width: 30px;">序号</th>
					<th style="width: 520px;">部门指标内容</th>
					<th style="width: 90px;">分值<font style="color:red">[整数]</font></th>
					<th style="width: 140px;">操作<input type="button" class="addLast" value="+"  /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item2" items="${item.deptKpiItems}">
					<tr>
						<td style="display:none">
							<input type="hidden" name="deptKpiItems_id"   value="${item2.id}" />
							<input type="hidden" name="deptKpiItems_sn"   value="${item2.sn}" />
							<input type="hidden" name="deptKpiItems_idrCompany.id"   value="${item2.idrCompany.id}" />
							<input type="hidden" name="deptKpiItemsKey"   value="${item2.id}" />
						</td>
						<td >
							${item2.sn}
						</td>
						<td>
							<input type="text" name="deptKpiItems_context"   value="${item2.context}" />
						</td>
						<td>
							<input type="text" name="deptKpiItems_point"   value="${item2.point}" />
						</td>
						<td>
							<input type="button" class="add"  value="+"  />
							<input type="button" class="remove" value="-"  />
							<input type="button" class="up"  value="/\" />
							<input type="button" class="down"  value="\/" />
							&nbsp;
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>		
	</div>
</div>
</c:forEach>
</form>

<br/>
<input type="button" value="保存" onclick="save()"/>
<input type="button" value="提交" onclick="commit()"/>

</body>
</html>
