package cn.fyg.pa.domain.model.monthchk;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.interfaces.web.shared.tool.DateTool;

public class MonthChkFactory {

	
	/**
	 * 创建员工初始工作总结月份,默认为当前月份前一月份
	 * @param person
	 * @return
	 */
	public static MonthChk createInitMonthChk(Person person){
		DateTool dateTool=new DateTool();
		return createMonthChk(person,dateTool.getPrevMonthYear(),dateTool.getPrevMonth());
	}
	
	public static MonthChk createNextMonthChk(Person person,Long year,Long month){
		month=month+1L;
		if(month.intValue()>12){
			year=year+1L;
			month=1L;
		}
		return createMonthChk(person,year,month);
	}
	
	public static MonthChk createMonthChk(Person person,Long year,Long month){
		MonthChk monthChk=new MonthChk();
		monthChk.setPerson(person);
		monthChk.setYear(year);
		monthChk.setMonth(month);
		monthChk.setState(MonthChkEnum.NEW);
		return monthChk;
	}
	

}
