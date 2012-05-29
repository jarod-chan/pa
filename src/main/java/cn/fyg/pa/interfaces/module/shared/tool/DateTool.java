package cn.fyg.pa.interfaces.module.shared.tool;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class DateTool {
	public static Long BEG_YEAR=2012L;
	private Calendar today=Calendar.getInstance();
	
	public Long getCurrentYear(){
		return 0L+today.get(Calendar.YEAR);
	}
	
	public Long getCurrentMonth(){
		return 1L+today.get(Calendar.MONTH);
	}
	
	public List<Long> getAllYears(){
		List<Long> years=new ArrayList<Long>();
		for (int i = BEG_YEAR.intValue(); i <= this.getCurrentYear().intValue(); i++) {
			years.add(new Long(i));
		}
		Collections.reverse(years);
		return years;
	}
	
	public List<Long> getAllMonths(){
		List<Long> months=new ArrayList<Long>();
		for(int i=1;i<=12;i++){
			months.add(new Long(i));
		}
		Collections.reverse(months);
		return months;
	}

}
