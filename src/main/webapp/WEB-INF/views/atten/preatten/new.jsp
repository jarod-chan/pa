<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

<script type="text/javascript">
	$(function(){
		//ie6不支持maxlength属性
		$("input[name=place],input[name=leader],input[name=reason]").iemaxlength();
		
		$("#btnCommit").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/commit").submit();
		});
		
		$("#btnBack").click(function(){
			window.open("/${ctx}/atten/${person.id}/preatten/list","_self");
		});
	})
</script>

<style type="text/css">
.calendar-month  {
    border-right: 1px solid #999999;
    border-top: 1px solid #999999;
    width: 428px;
    line-height: 30px;
}

.calendar-month  th{
  	background: none repeat scroll 0 0 #666666;
    color: #FFFFFF;
}

.calendar-month td, .calendar-month th {
    border-bottom: 1px solid #999999;
    border-left: 1px solid #999999;
    padding: 0;
    text-align: center;
    width: 60px;
}

.calendar-title {
	width: 426px;
	text-align: right;
	border-right: 1px solid #999999;
    border-left: 1px solid #999999;
    border-top: 1px solid #999999;
}

.calendar-title .nav-prev{
	height: 18px;
}

.current-month .ampm{
	display: none;
	list-style:none;
	padding: 0px;
	margin: 0px;
}

.current-month .ampm li {
	float:left;
	background:#CCC;
	width:30px;
	margin-left:0x;
	line-height:30px;
}

.current-month .ampm li a{text-decoration: none;color:#000000;white-space:nowrap;display:block;text-align:center;height:30px;}
.current-month .ampm li a:HOVER{background-color: #5BADFF;height: 30px;}
.current-month .ampm li a:VISITED{color: #000000;height: 30px;}



</style>

</head>

<c:set target="${pagefunc}" property="name" value="临时公出" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/atten/${person.id}/preatten/list" />  

<c:set target="${pagetitle}" property="name" value="临时公出新建" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/atten/${person.id}/preatten/new" /> 

<c:set var="pagesize" value="990" scope="request"/>  
<body>

<span id="target">2013-3-20:上午</span><img id="idimg"  style="vertical-align: bottom;" src="/${ctx}/resources/img/calendar.gif">

<script type="text/javascript">
	$(function(){
		$("#idimg").calendarWidget({
		
			begdate: '2013-1-3:am',
			enddate: '2013-5-10:pm'
		});
	})
</script>


	
		

<%@ include file="../../common/message.jsp"%>

<form action="/${ctx}/atten/${person.id}/preatten" method="post">

<input type="hidden" name="year" value="${preatten.year}">
<input type="hidden" name="month" value="${preatten.month}">

<table>
<tr>
	<td style="width: 300px;">编号：系统自动生成</td> <td style="width: 500px;">状态：${preatten.state.name}</td>
</tr>
<tr>
	<td colspan="2">	
		日期：${preatten.year}年${preatten.month}月
		<select name="dayitem.date">
			<c:forEach var="day" items="${dayList}">
				<option value="${day}" <c:if test="${day==preatten.dayitem.date}">selected="true"</c:if> >${day}</option>
			</c:forEach>
		</select>日
		<select name="dayitem.ampm">
			<c:forEach var="ampm" items="${ampms}">
				<option value="${ampm}" <c:if test="${ampm==preatten.dayitem.ampm}">selected="true"</c:if> >${ampm.name}</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<td colspan="2">地点：<input type="text"  style="width: 190px;" name="place"  maxlength="10" value="${preatten.place}" /><font style="color:red">[10字以内，简略写明外出地点]</font></td>
</tr>
<tr>
	<td colspan="2">原因：<input type="text"  style="width: 500px;" name="reason" maxlength="50" value="${preatten.reason}" /><font style="color:red">[50字以内，简略写明外出原因]</font>
	</td>
</tr>
<tr>
	<td>上级：<input type="text"  style="width: 100px;" name="leader" maxlength="10" value="${preatten.leader}" />已同意本次外出。</td>
	<td>申请人：${preatten.person.name}</td>
</tr>
</table>
<br>
	<input type="button" id="btnCommit" value="提交">
	<input type="button" id="btnBack" value="取消">
</form>
</body>
</html>
