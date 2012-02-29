package cn.fyg.pa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.dao.RptDao;
import cn.fyg.pa.page.Point;
import cn.fyg.pa.tool.PointUtil;

@Controller
@RequestMapping("/admin/rpt")
public class RptCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(RptCtl.class);
	
	@Autowired
	private RptDao rptDao;
	
	@RequestMapping(value="/point/{order}")
	public ModelAndView point(@PathVariable("order")String order){
		logger.info("show point");
		
		PointUtil pointUtil=new PointUtil(rptDao.getCheckPoint(),rptDao.getVal());
		List<Point> points=null;
		pointUtil.calculate();
		try {
			pointUtil.orderByPoint(order);
			points = pointUtil.getResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("points",points);
		mav.setViewName("rpt/point");
		return mav;
	}

}
