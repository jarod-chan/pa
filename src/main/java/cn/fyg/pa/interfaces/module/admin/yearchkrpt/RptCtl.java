package cn.fyg.pa.interfaces.module.admin.yearchkrpt;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.fyg.pa.infrastructure.persistence.jpa.RptJpa;
import cn.fyg.pa.interfaces.module.admin.yearchkrpt.year2011.Point_10;
import cn.fyg.pa.interfaces.module.admin.yearchkrpt.year2011.PointUtil_10;
import cn.fyg.pa.interfaces.module.shared.tool.DateTool;

@Controller
@RequestMapping("/admin/rpt")
public class RptCtl {
	public static List<Long> YEAR_SEL=new ArrayList<Long>();
	static{
		YEAR_SEL.add(2012l);
		YEAR_SEL.add(2011l);
	}
	
	@Autowired
	private RptJpa rptDao;
	
	@RequestMapping(value="/point/{order}")
	public String pointNoYear(@PathVariable("order")String order,Map<String,Object> map){
		Long year=new DateTool().getCurrentYear();
		if(year.compareTo(YEAR_SEL.get(0))>0){
			year=YEAR_SEL.get(0);
		}
		return String.format("redirect:./%s/%s",year,order);
	}
	
	/**
	 *2011年的绩效考核得分算法
	 */
	@RequestMapping(value="/point/2011/{order}")
	public String toPoint(@PathVariable("order")String order,Map<String,Object> map){
		final Long year=2011L;
		PointUtil_10 pointUtil=new PointUtil_10(rptDao.getCheckPoint(year),rptDao.getVal(year));
		List<Point_10> points=null;
		try {
			pointUtil.calculate();
			pointUtil.orderByPoint(order);
			points = pointUtil.getResult();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			map.put("error_info", sw.getBuffer().toString().replaceAll("\n","</br>"));
		}
		
		map.put("points",points);
		map.put("yearSel",YEAR_SEL);
		map.put("year", year);
		return "rpt/point"+year;

	}

}
