package cn.fyg.pa.domain.model.monthchk;

import cn.fyg.pa.domain.model.person.Person;

public class MonthChkFactory {
	
	/**
     * XXX 此处待重构
     * 五月份有新员工加入，此处处理
     */
	private static Long INIT_YEAR=2012L;
    private static Long INIT_MONTH=5L;
	
	/**
	 * 创建员工初始工作总结月份
	 * @param person
	 * @return
	 */
	public static MonthChk createInitMonthChk(Person person){
		return createMonthChk(person,INIT_YEAR,INIT_MONTH);
	}
	
	public static MonthChk createNextMonthChk(Person person,Long year,Long month){
		month=month+1L;
		if(month.intValue()>12){
			year=year+1L;
			month=1L;
		}
		return createMonthChk(person,year,month);
	}
	
	private static MonthChk createMonthChk(Person person,Long year,Long month){
		MonthChk monthChk=new MonthChk();
		monthChk.setPerson(person);
		monthChk.setYear(year);
		monthChk.setMonth(month);
		monthChk.setState(MonthChkEnum.NEW);
		return monthChk;
	}
	

}
