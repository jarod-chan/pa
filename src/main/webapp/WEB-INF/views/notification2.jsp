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
	width:802px;
	margin-top:10px;
	font-size:20px;
	margin-left:${(pagewidth-802)/2}px;
	text-align:left;
}

div.middle{
	margin-top:10px;
	text-align: center;
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
	font-size: 15px;
}   
table caption {font-size:14px;font-weight:bolder;}   
table th, table td {border:solid #000000;border-width:0 1px 1px 0;padding:2px;text-align: center;}  /* 设置表格每个td的边框， 只设置下侧和右侧的边框 */   

.title{
	background-color: #F2DBDB;
}
.middle table tr th{
	background-color: #F2DBDB;
}

</style>

<div class="notification_title"><h2>2012年度《方远报》通讯稿发表稿件统计</h2></div>
<div class="notification_context">
	<div class="context">
		<div class="top">
			&nbsp;&nbsp;&nbsp;&nbsp;自公司《通讯稿考核管理办法》颁布以来，各部门积极响应，及时供稿，稿件内容围绕公司的重点要点工作展开，塑造了公司良好的企业形象。现将各位员工2012年度在《方远报》实际发稿数量通报如下：
		</div>
		<div class="middle">
			<table>
				<tr>
					<th style="width: 85px;">姓名</th>
					<th style="width: 55px;">1月</th>
					<th style="width: 55px;">2月</th>
					<th style="width: 55px;">3月</th>
					<th style="width: 55px;">4月</th>
					<th style="width: 55px;">5月</th>
					<th style="width: 55px;">6月</th>
					<th style="width: 55px;">7月</th>
					<th style="width: 55px;">8月</th>
					<th style="width: 55px;">9月</th>
					<th style="width: 55px;">10月</th>
					<th style="width: 55px;">11月</th>
					<th style="width: 55px;">12月</th>
					<th style="width: 55px;">合计</th>
				</tr>
				<tr>
					<td class="title">毛 微</td>
					<td>2</td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>2</td>
					<td>1</td>
					<td>3</td>
					<td>1</td>
					<td>1</td>
					<td>2</td>
					<td>1</td>
					<td>1</td>
					<td>4</td>
					<td>19</td>
				</tr>
				<tr>
					<td class="title">王如虹</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>4</td>
				</tr>
				<tr>
					<td class="title">全小锋</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>3</td>
				</tr>
				<tr>
					<td class="title">王 俊</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>2</td>
				</tr>
				<tr>
					<td class="title">倪荷君</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>2</td>
				</tr>
				<tr>
					<td class="title">夏卫林</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>1</td>
				</tr>
				<tr>
					<td class="title">王黎闻</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>1</td>
				</tr>
				<tr>
					<td class="title">杨继红</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
				</tr>
				<tr>
					<td class="title">董灵君</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
				</tr>
				<tr>
					<td class="title">霍秋俊</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
				</tr>
				<tr>
					<td class="title">马 寄</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
				</tr>
				<tr>
					<td class="title">王海刚</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
				</tr>
				<tr>
					<td class="title">薛小燕</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>1</td>
					<td>1</td>
				</tr>
			</table>
		</div>
		<div class="bottom">
			说明:各部门每月供稿数量基本全部达标，部分稿件因质量不符合《方远报》要求未予发表，望今后不断提升稿件质量。
		</div>
	</div>
</div>

<br/>
<br/>
<br/>
<div class="notification_title"><h2>2013年通讯稿考核标准</h2></div>
<div class="notification_context">
	<div class="context">
		<div class="top">
			&nbsp;&nbsp;&nbsp;&nbsp;2013年，公司将继续执行《通讯稿考核管理办法》，供稿数量纳入年度绩效目标进行考核，望各职能部门、项目部积极配合。
稿件以职能部门为单位，按照月度应供稿数量标准进行分配，具体如下：
		</div>
		<div class="middle">
			<table>
				<tr>
					<th style="width: 200px;">部门名称</th>
					<th style="width: 300px;">月度供稿量</th>
					<th style="width: 300px;">说明</th>
				</tr>
				<tr>
					<td class="title">工程部</td>
					<td>2</td>
					<td>每月供稿2篇</td>
				</tr>
				<tr>
					<td class="title">产品部</td>
					<td>0.5</td>
					<td>每2月供稿1篇</td>
				</tr>
				<tr>
					<td class="title">技术部</td>
					<td>0.5</td>
					<td>每2月供稿1篇</td>
				</tr>
				<tr>
					<td class="title">成本管理部</td>
					<td>0.5</td>
					<td>每2月供稿1篇</td>
				</tr>
				<tr>
					<td class="title">销售部</td>
					<td>1</td>
					<td>每月供稿1篇</td>
				</tr>
				<tr>
					<td class="title">采购部</td>
					<td>0.5</td>
					<td>每2月供稿1篇</td>
				</tr>
				<tr>
					<td class="title">财务部</td>
					<td>0.5</td>
					<td>每2月供稿1篇</td>
				</tr>
				<tr>
					<td class="title">办公室</td>
					<td>2</td>
					<td>每月供稿2篇</td>
				</tr>
			</table>
		</div>
		<div class="company" style="margin-top: 10px;">
			<div>方远建设集团房地产开发有限公司</div>
			<div>二○一三年二月一日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		</div>
	</div>
</div>

