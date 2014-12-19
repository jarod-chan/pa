package cn.fyg.pa.interfaces.web.shared.tool;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class DateTool {
	public static Long BEG_YEAR=2012L;
	private final Calendar thisMonthFirstDay;
	private final Calendar prevMonthFirstDay;
	
	public DateTool(){
		Calendar day=Calendar.getInstance();
		day.set(Calendar.DATE, 1);
		
		thisMonthFirstDay=createNewCalendar(day);
		day.add(Calendar.MONTH, -1);
		prevMonthFirstDay=createNewCalendar(day);
		
	
	}
	private static Calendar createNewCalendar(Calendar calendar){
		Calendar newCalendar=Calendar.getInstance();
		newCalendar.setTime(calendar.getTime());
		return newCalendar;
	}
	
	
	public Long getCurrentYear(){
		return 0L+thisMonthFirstDay.get(Calendar.YEAR);
	}
	
	public Long getCurrentMonth(){
		return 1L+thisMonthFirstDay.get(Calendar.MONTH);
	}
	
	public Long getPrevMonthYear(){
		return 0L+prevMonthFirstDay.get(Calendar.YEAR);
	}
	
	public Long getPrevMonth(){
		return 1L+prevMonthFirstDay.get(Calendar.MONTH);
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
