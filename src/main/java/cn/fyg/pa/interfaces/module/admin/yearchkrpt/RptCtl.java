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
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.infrastructure.persistence.jpa.RptJpa;
import cn.fyg.pa.interfaces.module.admin.yearchkrpt.year2011.Point_11;
import cn.fyg.pa.interfaces.module.admin.yearchkrpt.year2011.PointUtil_11;
import cn.fyg.pa.interfaces.module.admin.yearchkrpt.year2012.PointUtil_12;
import cn.fyg.pa.interfaces.module.admin.yearchkrpt.year2012.Point_12;
import cn.fyg.pa.interfaces.module.admin.yearchkrpt.year2012_V2.PointUtil_v2;
import cn.fyg.pa.interfaces.module.admin.yearchkrpt.year2012_V2.Point_v2;
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
	
	@RequestMapping(value="/point/{order}",method=RequestMethod.GET)
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
	@RequestMapping(value="/point/2011/{order}",method=RequestMethod.GET)
	public String toPoint2011(@PathVariable("order")String order,Map<String,Object> map){
		final Long year=2011L;
		PointUtil_11 pointUtil_11=new PointUtil_11(rptDao.getCheckPoint(year),rptDao.getVal(year));
		List<Point_11> points_11=null;
		try {
			pointUtil_11.calculate();
			pointUtil_11.orderByPoint(order);
			points_11 = pointUtil_11.getResult();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			map.put("error_info", sw.getBuffer().toString().replaceAll("\n","</br>"));
		}
		
		map.put("points",points_11);
		map.put("yearSel",YEAR_SEL);
		map.put("year", year);
		return "rpt/point"+year;

	}
	

//	@RequestMapping(value="/point/2012/{order}",method=RequestMethod.GET)
	public String toPoint2012(@PathVariable("order")String order,Map<String,Object> map){
		final Long year=2012L;
		PointUtil_12 pointUtil_12=new PointUtil_12(rptDao.getCheckPoint(year),rptDao.getVal(year));
		List<Point_12> points_12=null;
		try {
			pointUtil_12.calculate();
			pointUtil_12.orderByPoint(order);
			points_12 = pointUtil_12.getResult();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			map.put("error_info", sw.getBuffer().toString().replaceAll("\n","</br>"));
		}
		
		map.put("points",points_12);
		map.put("yearSel",YEAR_SEL);
		map.put("year", year);
		return "rpt/point"+year;

	}
	
	@RequestMapping(value="/point/2012/{order}",method=RequestMethod.GET)
	public String toPoint2012_V2(@PathVariable("order")String order,Map<String,Object> map){
		final Long year=2012L;
		PointUtil_v2 pointUtil_v2=new PointUtil_v2(rptDao.getCheckPoint(year),rptDao.getVal(year));
		List<Point_v2> points_v2=null;
		try {
			pointUtil_v2.calculate();
			pointUtil_v2.orderByPoint(order);
			points_v2 = pointUtil_v2.getResult();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			map.put("error_info", sw.getBuffer().toString().replaceAll("\n","</br>"));
		}
		
		map.put("points",points_v2);
		map.put("yearSel",YEAR_SEL);
		map.put("year", year);
		return "rpt/point"+year;

	}

}
