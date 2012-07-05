package cn.fyg.pa.interfaces.module.admin.yearchkrpt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.infrastructure.persistence.jpa.RptJpa;

@Controller
@RequestMapping("/admin/rpt")
public class RptCtl {
	
	@Autowired
	private RptJpa rptDao;
	
	@RequestMapping(value="/point/{order}")
	public ModelAndView point(@PathVariable("order")String order){
		
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
