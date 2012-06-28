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
	width:600px;
	margin-top:10px;
	font-size:20px;
	margin-left:${(pagewidth-600)/2}px;
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


</style>

<div class="notification_title"><h2>2012年 6月份公司宣传通讯稿执行情况通报</h2></div>
<div class="notification_context">
	<div class="context">
		<div class="top">
			&nbsp;&nbsp;&nbsp;&nbsp;根据《通讯稿考核管理办法》的要求，截止2012年6月27日（星期三）前，各职能部门通迅报道供稿的完成情况通报如下：
		</div>
		<div class="middle">
			<table>
				<tr>
					<th style="width:120px;">部门</th>
					<th style="width:120px;">计划供稿</th>
					<th style="width:120px;">实际供稿</th>
					<th style="width:100px;">发表稿件</th>
					<th style="width:140px;">月度执行情况</th>
				</tr>	
				<tr>
					<td>工程部</td><td>2</td><td>4</td><td>3</td><td>完成</td>
				</tr>
				<tr>
					<td>产品部</td><td>0.5</td><td>1</td><td>0</td><td>完成</td>
				</tr>
				<tr>
					<td>技术部</td><td>0.5</td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td>成本管理部</td><td>0.5</td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td>销售部</td><td>1</td><td>1</td><td>1</td><td>完成</td>
				</tr>
				<tr>
					<td>采购部</td><td>0.5</td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td>财务部</td><td>0.5</td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td>办公室</td><td>2</td><td>2</td><td>2</td><td>完成</td>
				</tr>
				<tr>
					<td colspan="4">月度优秀通讯员</td><td>工程部:王俊</td>
				</tr>
			</table>
		</div>
		<div class="bottom">
			注：1、工程部、销售部、办公室最终截止日期是：2012年6月30日（星期六），其他部门最终截止日期是：2012年7月31日（星期二）
			<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、在截止日期前没有完成“实际供稿”，会直接影响“执行率”指标，无法补回。
		</div>
		<div class="company">
			<div>方远建设集团房地产开发有限公司</div>
			<div>二○一二年六月二十七日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		</div>
	</div>
</div>