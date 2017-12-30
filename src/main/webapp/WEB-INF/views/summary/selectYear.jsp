<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="text-align: right;width: 800px;">
		<select id="selectYear">
			<option value="/${ctx}/yearsmy/" <c:if test="${personSummary.year==2017}">selected="true"</c:if> >2017年</option> 
			<option value="/${ctx}/yearsmy/2016" <c:if test="${personSummary.year==2016}">selected="true"</c:if> >2016年</option> 
			<option value="/${ctx}/yearsmy/2015" <c:if test="${personSummary.year==2015}">selected="true"</c:if> >2015年</option> 
			<option value="/${ctx}/yearsmy/2014" <c:if test="${personSummary.year==2014}">selected="true"</c:if> >2014年</option> 
			<option value="/${ctx}/yearsmy/2013" <c:if test="${personSummary.year==2013}">selected="true"</c:if> >2013年</option> 
			<option value="/${ctx}/yearsmy/2012" <c:if test="${personSummary.year==2012}">selected="true"</c:if> >2012年</option>
		</select>
		<script type="text/javascript">
			$(function(){
				$("#selectYear").change(function(){
					window.open($(this).val(),"_self");
					return false;
				})
			})
		</script>
	</div>