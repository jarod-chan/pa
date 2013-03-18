package cn.fyg.pa.interfaces.module.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.fyg.pa.interfaces.module.system.login.UrlNameBean;



@Controller
@RequestMapping("/admin")
public class AdminCtl {
	
	@RequestMapping(value="/all")
	public String list(Map<String,Object> map) {
		String title="管理员页面";
		List<UrlNameBean> list=new ArrayList<UrlNameBean>();
		list.add(new UrlNameBean("用户管理","/pa/admin/person"));
		list.add(new UrlNameBean("考核报表","/pa/admin/rpt/point/asc"));
		list.add(new UrlNameBean("1.KPI指标类别","/pa/admin/idrtype"));
		list.add(new UrlNameBean("2.KPI类别权重","/pa/admin/idrtypeweight/edit/2012"));
		list.add(new UrlNameBean("3.公司KPI分解","/pa/admin/idrcompany/edit/2012"));
		list.add(new UrlNameBean("4.公司KPI指标分配部门","/pa/admin/deptindicator/2012"));
		list.add(new UrlNameBean("5.公司KPI指标部门分解","/pa/admin/deptkpi/2012"));
		
		list.add(new UrlNameBean("A.流程发布","/pa/admin/workflow/deploy"));
		list.add(new UrlNameBean("B.流程管理","/pa/admin/workflow/manage"));
		list.add(new UrlNameBean("C.运行状态","/pa/admin/workflow/running"));
		list.add(new UrlNameBean("D.流程历史","/pa/admin/workflow/history"));
		
		
		map.put("title", title);
		map.put("urls", list);
		
		return "route/all";
	}
}
