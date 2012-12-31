<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

<style type="text/css">
.mainul{list-style-type:none; margin:0;padding:5px 0px 0px 5px;width:100%; }
.mainul li { width:79px; float:left;background-color:#FFFFFF;margin: 0px 5px 5px 0px;border: 1px solid #000000; }
.mainul li select{width:79px; margin: 0px;padding: 0px;}
.mainul li div {text-align:center; padding: 0px;}

.btn_compare{width:79px;border-top:1px solid #000000; cursor:pointer; }
.hover_color{background-color: #0080FF;color: #FFFFFF;}

.block_div {display: none; }

.block_div  div{background-color:#C0C0C0;}

</style>

<script type="text/javascript">
	function save(){
		var actionFrom=$("form");
		var oldAction=actionFrom.attr("action");
		actionFrom.attr("action",oldAction+"/save").submit();
	}
	
	function commit(){
		if(confirm("提交以后，你将无法再对评价结果进行修改，确定提交？")){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/commit").submit();
		}
	}
	
	$(function() {
		
		var optColorArr=["#FF8080","#FECF78","#1E8EFF"];
		
		$("table .selectperson").each(function(){
			$(this).find("option").each(function(idx){
				$(this).css("background-color",optColorArr[idx]);
			});
			$(this).bind("change",function(){
				$(this).css("background-color",optColorArr[this.selectedIndex])
			}).triggerHandler("change");
		});
		
		$(".btn_compare").hover(
			  function () {
			    $(this).addClass("hover_color");
			  },
			  function () {
			    $(this).removeClass("hover_color");
			  }
		);
		var isOpen=false;
		$("#btn_open").click(function(){
			if(!isOpen){
				isOpen=true;
				var len=${fn:length(rowBeanList)};
				$(".mainul").width(len*85+5+len);
				$(".block_div").show();
				$(this).val("紧凑");
			}else{
				isOpen=false;
				var len=10;
				$(".mainul").width(len*85+5+len);
				$(".block_div").hide();
				$(this).val("平铺");
			}			
		});
	});
	
	function personCompare(colPersonId,rowPersonId){
		if(colPersonId=='') return;
		if(rowPersonId=='') return;
		OpenEnvDefineWin("/${ctx}/person/${person.id}/yearchk/personchk/"+colPersonId+"/comparework/"+rowPersonId,880,600);
	}
	
	
</script>
</head>
<c:set target="${pagefunc}" property="name" value="年终员工评价" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/person/${person.id}/yearchk" />  


<c:set var="pagesize" value="960" scope="request"/> 
<body>

<div class="headdiv" >
<div class="headleft"  >
考核年份:${year}&nbsp;&nbsp;状态:<c:choose><c:when test="${pageBean.commit==true}">已提交</c:when><c:when test="${pageBean.finish==true}">已完成</c:when><c:otherwise>未完成</c:otherwise></c:choose>
</div>
<div class="headright" >
	<c:if test="${not pageBean.commit}">
		<input type="button" value="保存" onclick="save()"/>
		<input type="button" value="提交评价结果" onclick="commit()"/>
	</c:if>
</div>
<div  class="headnone"></div>
</div>
<%@ include file="../../common/message.jsp"%>

<form action="/${ctx}/person/${person.id}/yearchk/${year}"  method="post" >
<table border=1  >
<thead>
	<tr>
		<th style="width: 80px;">员工</th>
		<th >对比评价结果&nbsp;&nbsp;<input type="button" id="btn_open" value="平铺"></th>
	</tr>
</thead>
<tbody>
	<% int num=0;%>
	<c:forEach var="rowBean" items="${rowBeanList}">
	<tr>
		<td>
			${rowBean.rowPerson.name}
		</td>
		<td>
			<ul class="mainul" style="width: 860px;">
			<c:forEach var="cellBean" items="${rowBean.cellBeans}">
				<c:choose>
					<c:when test="${not empty cellBean}">
						<li>
							<div>
							 	<input type="hidden"          name="ids[<%=num %>]"  value="${cellBean.fycheck.id}"/> 
								<select class="selectperson"  name="val[<%=num %>]" >
									<option value="1"    <c:if test="${cellBean.fycheck.val=='1'}">selected="true"</c:if> >${cellBean.colPerson.name}优</option>
									<option value="0"    <c:if test="${cellBean.fycheck.val=='0'}">selected="true"</c:if> >${cellBean.colPerson.name}平</option>
									<option value="-1"   <c:if test="${cellBean.fycheck.val=='-1'}">selected="true"</c:if>>${cellBean.colPerson.name}劣</option>
								</select>
								<div class="btn_compare" onclick="personCompare( ${cellBean.colPerson.id},${cellBean.rowPerson.id})">对比参考</div>
							</div>
						</li>
						<% num++;%>
					</c:when>
					<c:otherwise>
						<li class="block_div">
							<div >
								<div style="height: 18px;"></div>
								<div style="border-top: 1px solid #000000;height: 18px;"></div>
							</div>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>	
			</ul>
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>
</form>


</body>
</html>
