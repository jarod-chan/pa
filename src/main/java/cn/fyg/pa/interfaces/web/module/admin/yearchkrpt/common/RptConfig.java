package cn.fyg.pa.interfaces.web.module.admin.yearchkrpt.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.interfaces.web.module.admin.yearchkrpt.PointHelp;

public class RptConfig {
	
	public static List<Long> YEAR_SEL=new ArrayList<Long>();
	@SuppressWarnings("rawtypes")
	public static Map<Long,PointHelp> RPT=new HashMap<Long,PointHelp>();
	
	static{
		YEAR_SEL.add(2013l);
		YEAR_SEL.add(2012l);
		YEAR_SEL.add(2011l);
	}
	
	public static PointHelp<?>  getPointHelp(Long year){
		if(year==2013L){
			return new cn.fyg.pa.interfaces.web.module.admin.yearchkrpt.year2013.PointUtil();
		}else if(year==2012L){
			return new cn.fyg.pa.interfaces.web.module.admin.yearchkrpt.year2012.PointUtil();
		}else if(year==2011L){
			return new cn.fyg.pa.interfaces.web.module.admin.yearchkrpt.year2011.PointUtil();
		}
		return null;
	}

}
