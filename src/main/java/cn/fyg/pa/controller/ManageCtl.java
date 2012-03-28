package cn.fyg.pa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.fyg.pa.bean.UrlName;

@Controller
@RequestMapping("/mange/{personId}")
public class ManageCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(ManageCtl.class);
	
	@RequestMapping(value="/all")
	public String list(@PathVariable Long personId,Map<String,Object> map) {
		logger.info("show mange all");
		String title="经理页面";
		List<UrlName> list=new ArrayList<UrlName>();
		list.add(new UrlName("员工月度工作任务评价",String.format("/pa/mange/%s/monthchk",personId)));
		list.add(new UrlName("部门月度工作计划执行",String.format("/pa/mange/%s/idrmonthplan",personId)));
		map.put("title", title);
		map.put("urls", list);
		
		return "route/all";
	}

}
