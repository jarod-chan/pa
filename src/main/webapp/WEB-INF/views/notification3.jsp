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

<div class="notification_title"><h2>2013年3-4月份通讯稿考核执行情况</h2></div>
<div class="notification_context">
	<div class="context">
		<div class="top">
			&nbsp;&nbsp;&nbsp;&nbsp;截止至4月28日，各职能部门通讯报道供稿情况通报如下：
		</div>
		<div class="middle">
			<table>
				<tr>
					<th style="width:120px;">部门</th>
					<th style="width:100px;">计划供稿<br>(3-4)月</th>
					<th style="width:200px;" colspan="2">供稿人及数量</th>
					<th style="width:100px;">合计供稿</th>
					<th style="width:90px;">发表稿件</th>
					<th style="width:90px;">执行结果</th>
				</tr>	
				<tr>
					<td rowspan="2">工程部</td><td rowspan="2">4</td><td style="width: 150px;">马 寄</td><td style="width: 50px;">1</td><td rowspan="2">4</td><td rowspan="2">1</td><td rowspan="2">完成</td>
				</tr>
				<tr>
					<td>董灵君</td><td>3</td>
				</tr>
				
				<tr>
					<td>产品部</td><td>1</td><td>/</td><td>/</td><td>0</td><td>0</td><td>未完成</td>
				</tr>
				
				<tr>
					<td>技术部</td><td>1</td><td>/</td><td>/</td><td>0</td><td>0</td><td>未完成</td>
				</tr>
				
				<tr>
					<td>成本管理部</td><td>1</td><td>邬亚丹</td><td>2</td><td>2</td><td>1</td><td>完成</td>
				</tr>
				
				<tr>
					<td rowspan="2">销售部</td><td rowspan="2">2</td><td>王黎闻</td><td>1</td><td rowspan="2" >2</td><td rowspan="2" >1</td><td rowspan="2" >完成</td>
				</tr>
				<tr>
					<td>王如虹</td><td>1</td>
				</tr>
				
				
				<tr>
					<td>采购部</td><td>1</td><td >王俊</td><td>1</td><td>1</td><td>(5月发)</td><td>完成</td>
				</tr>
				
				<tr>
					<td>财务部</td><td>1</td><td >林 俊</td><td>1</td><td>1</td><td>0</td><td>完成</td>
				</tr>
				
				<tr>
					<td rowspan="2">办公室</td><td rowspan="2">4</td><td >毛微</td><td>4</td><td rowspan="2">5</td><td rowspan="2">4</td><td rowspan="2">完成</td>
				</tr>
				<tr>
					<td>全小锋</td><td>1</td>
				</tr>
				
				<tr>
					<td colspan="7">本月优秀通讯员：董灵君</td>
				</tr>
			</table>
		</div>
		<div class="bottom">
			<strong>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;由于个别部门供稿存在敷衍应付现象，为进一步提升稿件质量，今后各部门所投稿件需满足以下条件，才视为当月合格稿件，计入考核结果：
			<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1）每月20日前供稿；
			<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2）稿件内容：
			</strong>
			<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新闻类稿件：围绕公司的重点要点工作，宣传公司或所在部门的各项工作进展及成果，包括重大会议、重要活动，管理中的新举措、新动态、新成就等
			<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;散文或杂文类稿件：注重采写工作中涌现出来的先进人物、管理经验、一线员工生活、个人工作体会等。
			<br/>
			<strong>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3）合格稿件录用标准（9分以上）。
			</strong>
		</div>
		
		<div class="middle">
			<table>
				<tr>
					<th style="width:100px;">类型</th>
					<th colspan="2" style="width:500px;">稿件质量要求</th>
					<th style="width:80px;">分值</th>
				</tr>	
				<tr>
					<td rowspan="8" >新闻类</td><td style="width:100px;">字数</td><td class="lft">500字或以上</td><td>5</td>
				</tr>
				<tr>
					<td>图片</td><td class="lft">有1幅或以上图片</td><td>2</td>
				</tr>
				
				<tr>
					<td rowspan="6">内容</td><td class="lft">有准确概括事件主要内容、观点的标题</td><td>1</td>
				</tr>
				<tr>
					<td class="lft">有明确的事件发生的时间、地点</td><td>1</td>
				</tr>
				<tr>
					<td class="lft">有主要人物对新闻事件看法描述</td><td>1</td>
				</tr>
				<tr>
					<td class="lft">有事实发生的经过、结果的记述</td><td>1</td>
				</tr>
				<tr>
					<td class="lft">有对事件的观点及评论</td><td>1</td>
				</tr>
				<tr>
					<td class="lft">稿件经部门负责人审核</td><td>2</td>
				</tr>
				
				
				<tr>
					<td rowspan="6">散文或杂文<br>(先进人物)<br>(管理经验)<br>(员工生活)<br>(工作体会)</td><td>字数</td><td class="lft">800字或以上</td><td>5</td>
				</tr>
				<tr>
					<td rowspan="5">内容</td><td class="lft">有鲜明的标题</td><td>1</td>
				</tr>
				<tr>
					<td class="lft">叙写的是真人真事（人、事、物、景）</td><td>2</td>
				</tr>
				<tr>
					<td class="lft">抒发了真情实感（观点、意义、价值）</td><td>2</td>
				</tr>
				<tr>
					<td class="lft">能给人以思想启迪、美的感受、情操的陶治</td><td>2</td>
				</tr>
				<tr>
					<td class="lft">立意深邃，观点独到，题材新颖</td><td>2</td>
				</tr>
			</table>
			（以上录用标准自三月份起试用，望各部门遵照执行。）
		</div>
	</div>
</div>