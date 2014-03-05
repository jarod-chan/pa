<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
.notification_title{
	width:${pagewidth}px;
	text-align:center;
} 
.notification_context{
	width:${pagewidth}px;
}

.notification_context .context{
	width:700px;
	margin-top:10px;
	font-size:20px;
	margin-left:${(pagewidth-700)/2}px;
	text-align:left;
}

div.middle{
	margin-top:10px;
}

div.bottom{
	margin-top:10px;
}

div.company{
	margin-top:60px;
	text-align: right;
}
 
table {   
	border-collapse:collapse; /* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */   
	border:solid #000000; /* 设置边框属性：样式(solid=实线)、颜色(#999=灰) */   
	border-width:2px; /* 设置边框状粗细：上 右 下 左 = 对应：1px 0 0 1px */   
}   
table caption {font-size:14px;font-weight:bolder;}   
table th, table td {border:solid #000000;border-width:0 1px 1px 0;padding:2px;text-align: center;}  /* 设置表格每个td的边框， 只设置下侧和右侧的边框 */   


.notfinish{
	background-color: #F2DBDB;
}

.lft{
	text-align: left;
}

</style>

<div class="notification_title"><h2>房产各部门2014年度《方远报》投稿指标</h2></div>
<div class="notification_context">
	<div class="context">
		<div class="middle">
			<table>
				<tr>
					<th style="width:120px;">部门</th>
					<th style="width:200px;">月度供稿量</th>
					<th style="width:400px;">说明</th>
				</tr>	
				<tr>
					<td>项目一部</td>
					<td>1</td>
					<td>每月供稿1篇</td>
				</tr>
				<tr>
					<td>项目二部</td>
					<td>1</td>
					<td>每月供稿1篇</td>
				</tr>
				<tr>
					<td>总工办</td>
					<td>0.5</td>
					<td>每2月供稿1篇</td>
				</tr>
				<tr>
					<td>工程部</td>
					<td>0.5</td>
					<td>每2月供稿1篇</td>
				</tr>
				<tr>
					<td>成本管理部</td>
					<td>0.5</td>
					<td>每2月供稿1篇</td>
				</tr>
				<tr>
					<td>销售部</td>
					<td>1.5</td>
					<td>每2月供稿3篇</td>
				</tr>
				<tr>
					<td>采购部</td>
					<td>0.5</td>
					<td>每2月供稿1篇</td>
				</tr>
				<tr>
					<td>财务部</td>
					<td>0.5</td>
					<td>每2月供稿1篇</td>
				</tr>
				<tr>
					<td>服务中心</td>
					<td>0.5</td>
					<td>每2月供稿1篇</td>
				</tr>
				<tr>
					<td>办公室</td>
					<td>2</td>
					<td>每月供稿2篇</td>
				</tr>
				
			</table>
		</div>
	</div>
</div>