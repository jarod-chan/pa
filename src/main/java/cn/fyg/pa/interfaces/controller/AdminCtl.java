package cn.fyg.pa.interfaces.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.fyg.pa.interfaces.bean.UrlNameBean;



@Controller
@RequestMapping("/admin")
public class AdminCtl {
	private static final Logger logger = LoggerFactory.getLogger(LoginCtl.class);
	
	@RequestMapping(value="/all")
	public String list(Map<String,Object> map) {
		logger.info("show admin all");
		String title="管理员页面";
		List<UrlNameBean> list=new ArrayList<UrlNameBean>();
		list.add(new UrlNameBean("用户管理","/pa/admin/person"));
		list.add(new UrlNameBean("考核报表","/pa/admin/rpt/point/asc"));
		map.put("title", title);
		map.put("urls", list);
		
		return "route/all";
	}
}
