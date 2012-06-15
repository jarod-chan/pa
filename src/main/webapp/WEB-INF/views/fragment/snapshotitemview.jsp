<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
	div.all{
		width:800px;
		margin-bottom:20px;
		border-width:2px;
		border-style:solid ;           /* dashed ; */
		border-color:  #000000;
	}
	
	div.status{
		font-size:20px;
		font-weight: bold;
		padding-left: 10px;
		border-bottom: 1px solid #000000;
	}
	
	div.context {
		margin: 5px;
		border-style:solid;           /* dashed ; */
		border-width: 1px;
		border-color:  #000000;
		height: 100%;
		width: 780px;
	}
	
	div.context .department {
		width:90px;
		padding: 5px 10px;
		float: left;
	}
	
	div.context .person {
		width:660px;
		padding-left: 5px;
		float: left;
		padding-bottom: 5px;
	}

	
	div.context .person  ul{list-style-type:none; margin:0;padding:0;width:100%; }
	div.context .person  ul li{ width:100px; float:left;background-color:#FFFFFF;margin: 4px;border: 1px solid #97CBFF; }
	div.context .none { clear:both; font-size:0px; height:0px; line-height:0px;}
</style>
<script type="text/javascript">
 	$(document).ready(function(){
		$("div.all div.context").each(function(){
			var departmentDiv=$(this).find(".department");
			var personDiv=$(this).find(".person");
			if(departmentDiv.height()>personDiv.height()){
				departmentDiv.css({"border-right": "1px solid #000000"});
			}else{
				personDiv.css({"border-left": "1px solid #000000"});
			}
		})
	}) 
</script>

<%  
    request.setAttribute("sn", 0);   
%>

<c:forEach var="ft" items="${pageItemBean.statusBeans}">
	<div class="all">
		<div class="status">${ft.status.name}</div>
		
		<c:forEach var="sd" items="${ft.departDescs}">
		<div class="context">
			<div class="department">${sd.departemnt.name}<br/>经理:${sd.manager.name}</div>
			<div class="person">
				<ul>
					<c:forEach var="td" items="${pageItemBean.map[sd.findPersonListKey]}">
						<li>${td.name}
							<c:set var="sn" value="${sn+1}" />
							<input type="hidden"  name="snapshotItems_sn"  value="${sn}"/>
							<input type="hidden"  name="snapshotItems_status"  value="${ft.status}"/>
							<input type="hidden"  name="snapshotItems_department.id"  value="${sd.departemnt.id}"/>
							<input type="hidden" 	name="snapshotItems_manager.id" value="${sd.manager.id}"/>
							<input type="hidden" 	name="snapshotItems_person.id" value="${td.id}"/>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="none">
				
			</div>
		</div>
		</c:forEach>
		</div>
</c:forEach>
