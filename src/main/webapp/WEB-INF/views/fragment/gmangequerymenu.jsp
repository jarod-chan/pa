<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
body {
     border:0;
     height:100%; /* 必须 */
     overflow-y:auto;/* 必须 */
    }
#fixdiv {display:block; bottom:10px; left:10px; width:800px; position:fixed;} /* IE并不认识fixed，而FF认识 */
* html #fixdiv {position:absolute;} /* 这个只有IE认识 */

</style>
<!--[if IE 6]>
   <style type="text/css">
   /*<![CDATA[*/
	html {overflow-x:auto; overflow-y:hidden;}
   /*]]>*/
   </style>
<![endif]-->
<div id="fixdiv" >
<strong>公司考核概况查询：</strong>
<input type="button" value="公司部门月度工作查询" onclick="javascript:window.open('/${ctx}/gmange/${person.id}/query/idrmonthplan','_blank')"/>
&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="公司员工月度工作查询" onclick="javascript:window.open('/${ctx}/gmange/${person.id}/query/monthchk','_blank')"/>
<br/><br/>
<strong>公司考核总体概况：</strong>
<input type="button" value="公司部门月度工作概况" onclick="javascript:window.open('/${ctx}/gmange/${person.id}/analysis/idrmonthplan','_blank')"/>
&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="公司员工月度工作概况" onclick="javascript:window.open('/${ctx}/gmange/${person.id}/analysis/monthchk','_blank')"/>
</div>