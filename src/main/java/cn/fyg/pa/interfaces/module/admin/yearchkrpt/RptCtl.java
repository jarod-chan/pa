package cn.fyg.pa.interfaces.module.admin.yearchkrpt;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.infrastructure.persistence.jpa.RptJpa;
import cn.fyg.pa.interfaces.module.admin.yearchkrpt.common.RptConfig;
import cn.fyg.pa.interfaces.module.shared.tool.DateTool;

@Controller
@RequestMapping("/admin/rpt")
public class RptCtl {

	@Autowired
	private RptJpa rptJpa;
	
	@RequestMapping(value="/point/{order}",method=RequestMethod.GET)
	public String pointNoYear(@PathVariable("order")String order,Map<String,Object> map){
		Long year=new DateTool().getCurrentYear();
		if(year.compareTo(RptConfig.YEAR_SEL.get(0))>0){
			year=RptConfig.YEAR_SEL.get(0);
		}
		return String.format("redirect:./%s/%s",year,order);
	}
	
	@RequestMapping(value="/point/{year}/{order}",method=RequestMethod.GET)
	public String toPoint(@PathVariable("year")Long year,@PathVariable("order")String order,Map<String,Object> map){
		PointHelp<?> pointHelp=RptConfig.getPointHelp(year);
		try {
			pointHelp.initOriginalData(rptJpa,year);
			pointHelp.calculate();
			pointHelp.orderByPoint(order);
			map.put("points",pointHelp.getResult());
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			map.put("error_info", sw.getBuffer().toString().replaceAll("\n","</br>"));
		}
		
		
		map.put("yearSel",RptConfig.YEAR_SEL);
		map.put("year", year);
		return "rpt/point"+year;

	}

}
