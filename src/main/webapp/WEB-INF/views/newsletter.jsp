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

<div class="notification_title"><h2>2013年11-12月份通讯稿考核执行情况</h2></div>
<div class="notification_context">
	<div class="context">
		<div class="top">
			&nbsp;&nbsp;&nbsp;&nbsp;截止至<span style="color:red;">12月31日</span>，各职能部门通讯报道供稿情况通报如下：
		</div>
		<div class="middle">
			<table>
				<tr>
					<th style="width:120px;">部门</th>
					<th style="width:100px;">计划供稿<br>(11-12)月</th>
					<th style="width:200px;" colspan="2">供稿人及数量</th>
					<th style="width:100px;">合计供稿</th>
					<th style="width:90px;">发表稿件</th>
					<th style="width:90px;">执行结果</th>
				</tr>	
				<tr>
					<td rowspan="2">工程部</td><td rowspan="2">4</td><td>董灵君</td><td>1</td><td >1</td><td >1</td><td rowspan="2">未完成</td>
				</tr>
				<tr>
											  <td>沈楝大</td><td>1</td><td >1</td><td >1</td>
				</tr>
				
				<tr>
					<td>产品部</td><td>1</td><td></td><td></td><td></td><td></td><td>未完成</td>
				</tr>
				
				<tr >
					<td>技术部</td><td>1</td><td></td><td></td><td></td><td></td><td>未完成</td>
				</tr>
				
				<tr>
					<td>成本管理部</td><td>1</td><td>应利君</td><td>1</td><td>1</td><td>0</td><td>完成</td>
				</tr>
				
				<tr>
					<td rowspan="2">销售部</td><td rowspan="2">2</td><td>杨继红</td><td>2</td><td>2</td><td>2</td><td rowspan="2">完成</td>
				</tr>
				<tr>
					                          <td>王黎闻</td><td>1</td><td>1</td><td>1</td>
				</tr>
				
				<tr>
					<td>采购部</td><td>1</td><td >王俊</td><td>1</td><td>1</td><td>0</td><td>完成</td>
				</tr>
				
				<tr>
					<td>财务部</td><td>1</td><td></td><td></td><td></td><td></td><td>未完成</td>
				</tr>
				
				<tr>
					<td rowspan="2">办公室</td><td rowspan="2">4</td><td >毛微</td><td>2</td><td>2</td><td>2</td><td rowspan="2">完成</td>
				</tr>
				
				<tr>
					<td >全小锋</td><td>2</td><td>2</td><td>2</td>
				</tr>
				
				<tr>
					<td colspan="7">本月优秀通讯员：无</td>
				</tr>
			</table>
		</div>
		<%@ include file="terms.jsp"%>
	</div>
</div>