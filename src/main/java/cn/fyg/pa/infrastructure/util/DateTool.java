package cn.fyg.pa.infrastructure.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class DateTool {
	public static Long BEG_YEAR=2012L;
	private final Calendar today;
	private final Calendar thisMonthFirstDay;
	private final Calendar prevMonthFirstDay;
	private final Calendar thisMonthLastDay;
	
	public DateTool(){
		this.today=Calendar.getInstance();
		
		Calendar var=copyCalendar(this.today);
		var.set(Calendar.DATE, 1);
		this.thisMonthFirstDay=var;
		
		var=copyCalendar(this.today);
		var.set(Calendar.DATE, 1);
		var.add(Calendar.MONTH, -1);
		this.prevMonthFirstDay=var;
		
		var=copyCalendar(this.today);
		var.set(Calendar.DATE, 1);
		var.add(Calendar.MONTH, 1);
		var.add(Calendar.DATE, -1);
		this.thisMonthLastDay=var;
	
	}
	
	private Calendar copyCalendar(final Calendar today) {
		Calendar newCalendar=Calendar.getInstance();
		newCalendar.setTime(today.getTime());
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
	
	public List<Long> theDaysAfterToday(){
		List<Long> days=new ArrayList<Long>();
		int begday=this.today.get(Calendar.DATE);
		int endday=this.thisMonthLastDay.get(Calendar.DATE);
		for(int i=begday;i<=endday;i++){
			days.add(new Long(i));
		}
		return days;
	}

}
