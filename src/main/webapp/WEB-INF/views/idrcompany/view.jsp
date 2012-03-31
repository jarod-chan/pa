<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>

<head>
<%@ include file="../common/head.jsp"%>
<style type="text/css">
	.currRow{
		background-color:#77BBFF;
	}
</style>

<script type="text/javascript">

	var leftcss={"float":"left","width":"22px"};
	var leftAndMargin={"float":"left","margin-left":"10px","width":"22px"};
	
	var idrTypeWeightsJson=${idrTypeWeightsJson};
	
	var rowClick=function (){
		$(".tbldef tbody tr td:last-child :button").hide();
		$(this).find("td:last").find(":button").show();
		$(".tbldef tbody tr").removeClass("currRow");
		$(this).addClass("currRow");
	};
	
	var addLastRow=function(){
		var newtr=tr.clone();
		newtr.click(rowClick);
		newtr.find("select[name='idrCompany_idrTypeWeight.id']").bind("change",typeWeightChange);
		newtr.find(":input[name='idrCompany_weight']").bind("blur",numberBlur);
		if($('#triggerCol').val()=="->"){
			newtr.find('td:gt(4):lt(4)').hide();
		}
		$(".tbldef tbody").append(newtr); 
		reIndexTable();
	}
	
	var numberBlur=function(){
		if(!IsFloat($(this).val()))	{
			$(this).val("");
			var tr=$(this).parents("tr");
			tr.find(".realweight").html("");
		}else{
			var tr=$(this).parents("tr");
			var typeweight=tr.find(".typeweight").html();
			var realweight=FloatMul($(this).val(),typeweight);
			realweight=hold(realweight,4);
			tr.find(".realweight").html(realweight);
		}
	};
	
	var typeWeightChange=function(){
		var tr=$(this).parents("tr");
		tr.find(".typeweight").html(idrTypeWeightsJson[$(this).get(0).selectedIndex].weight);
		tr.find(":input[name='idrCompany_weight']").triggerHandler("blur");
	}
	
	var triggerCol=function(){
		if($(this).val()=="<-"){
			$(this).val("->");
			$('thead tr').find('th:gt(4):lt(4)').hide();
			$('tbody tr').find('td:gt(4):lt(4)').hide();
		}else{
			$(this).val("<-");
			$('thead tr').find('th:gt(4):lt(4)').show();
			$('tbody tr').find('td:gt(4):lt(4)').show();
		}
	}
	

	var sel=$("<select name='idrCompany_idrTypeWeight.id' ></select>");
	<c:forEach var="idrTypeWeight" items="${idrYearTypeWeight.idrTypeWeight}">
		sel.append("<option value='${idrTypeWeight.id}'>${idrTypeWeight.idrType.name}</option>");
	</c:forEach>

	var tr = $("<tr>");
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='idrCompany_id' />"))
		.append($("<input type='text' name='idrCompany_sn' />"))
		.css("display","none");
	tr.append("<td>");
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='idrCompany_number' />"));
	tr.append("<td>")
		.find("td:last")
		.append(sel);
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='idrCompany_context' />"));
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='idrCompany_quantization'/>"));
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='idrCompany_standard' />"));
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='idrCompany_computeMode' />"));
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='idrCompany_period' />"));
	tr.append("<td>")
		.find("td:last")
		.append($("<input type='text' name='idrCompany_weight' />"));
	tr.append("<td>")
		.find("td:last")
		.append($("<span>").attr({'class':'typeweight'}).html(idrTypeWeightsJson[0].weight));
	
	tr.append("<td>")
		.find("td:last")
		.append($("<span>").attr({'class':'realweight'}));
 	
	tr.append("<td>")
		.find("td:last")
		.append(
				$("<input type='button' class='add' onclick='add(this)' value='+'   />").css(leftcss).hide())
		.append(
				$("<input type='button' class='remove' onclick='remove(this)' value='-'   />").css(leftAndMargin).hide())
		.append(
				$("<input type='button' class='up' onclick='up(this)' value='/\\'  />").css(leftAndMargin).hide())
		.append(
				$("<input type='button' class='down' onclick='down(this)' value='\\/'  />").css(leftAndMargin).hide())
		.append("&nbsp;"); 

	$(document).ready(function() { 
		$(".add").css(leftcss).hide();
		$(".remove").add(".up").add(".down").css(leftAndMargin).hide();
		$(".tbldef tbody tr").click(rowClick);

		$(":input[name='idrCompany_weight']").bind("blur",numberBlur);
		$("select[name='idrCompany_idrTypeWeight.id']").bind("change",typeWeightChange)
		$(".addLast").bind('click',addLastRow);
		$("#triggerCol").bind('click',triggerCol).triggerHandler("click");
	});
	
	function add(obj) {
		var newtr=tr.clone();
		newtr.click(rowClick);
		newtr.find("select[name='idrCompany_idrTypeWeight.id']").bind("change",typeWeightChange);
		newtr.find(":input[name='idrCompany_weight']").bind("blur",numberBlur);
		if($("#triggerCol").val()=="->"){
			newtr.find('td:gt(4):lt(4)').hide();
		}
		$(obj).parent().parent().after(newtr);
		reIndexTable();
	}

	function remove(obj) {
		$(obj).parent().parent().remove();
		reIndexTable();
	}

	function up(obj) {
		var tr = $(obj).parent().parent();
		tr.prev().before(tr);
		reIndexTable();
	}

	function down(obj) {
		var tr = $(obj).parent().parent();
		tr.next().after(tr);
		reIndexTable();
	}
	
	function reIndexTable(){
		var index=1;
		$(".tbldef tbody").find("tr").each(function(){
			$(this).find("td").eq(0).find("[name='idrCompany_sn']").val(index);
			$(this).find("td").eq(1).html(index);
			index++;
		});
	}
	
	function save(){
		var actionFrom=$("#idrcompany");
		var oldAction=actionFrom.attr("action");
		$(".tbldef tbody").formatName();
		actionFrom.attr("action",oldAction+"/save").submit();
	}
	
	function sort(){
		var actionFrom=$("#idrcompany");
		var oldAction=actionFrom.attr("action");
		$(".tbldef tbody").formatName();
		actionFrom.attr("action",oldAction+"/sort").submit();
	}
	
	function commit(){
		var actionFrom=$("#idrcompany");
		var oldAction=actionFrom.attr("action");
		var msg="确定提交？";
		if(confirm(msg)){
			$(".tbldef tbody").formatName();
			actionFrom.attr("action",oldAction+"/commit").submit();
		}
	}
	
	function preview(){
		window.open('/${ctx}/admin/idrcompany/perview/${idrYearCompany.year}','_blank');
	}
</script>
</head>
<body>
<h2>公司KPI分解</h2>
年度：${idrYearCompany.year}
<%@ include file="../common/message.jsp"%>

<table border="1" class="tbldef" >
		<thead>
			<tr>
				<th >序号</th>
				<th >编码</th>
				<th >类别</th>
				<th>指标类容</th>
				<th>量化指标</th>
				<th>工作标准</th>
				<th>计算方式</th>
				<th>考核周期</th>
				<th>权重</th>
				<th>类别权重</th>
				<th>实际权重</th>
			</tr>
		</thead>
		<tbody>
	  		 <c:forEach var="item" items="${idrYearCompany.idrCompany}">
			<tr>
				<td>
					${item.sn}
				</td>
				<td>
					${item.number}
				</td>
				<c:if test="${rowSpan[item.sn]!=null}">
					<td rowspan="${rowSpan[item.sn]}">
						${item.idrTypeWeight.idrType.name}
					</td>
				</c:if>
				<td>
					${item.context}
				</td>
				<td>
					${item.quantization}
				</td>
				<td>
					${item.standard}
				</td>
				<td>
					${item.computeMode}
				</td>
				<td>
					${item.period}
				</td>
				<td>
					${item.weight}
				</td>
				<c:if test="${rowSpan[item.sn]!=null}">
					<td rowspan="${rowSpan[item.sn]}">
						${item.idrTypeWeight.weight}
					</td>
				</c:if>
				<td>
					${item.realWeight}
				</td>
			</tr>
			</c:forEach> 
		</tbody>
</table>

</body>
</html>
