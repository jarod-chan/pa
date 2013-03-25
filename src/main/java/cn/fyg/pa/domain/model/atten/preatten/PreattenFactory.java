package cn.fyg.pa.domain.model.atten.preatten;

import cn.fyg.pa.domain.model.atten.common.AMPM;
import cn.fyg.pa.domain.model.atten.common.Dayitem;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.infrastructure.util.DateTool;

public class PreattenFactory {
	
	public static Preatten create(Person person){
		DateTool dateTool=new DateTool();
		Preatten preatten=new Preatten();
		preatten.setState(Prestate.new_);
		Dayitem dayitem=new Dayitem();
		dayitem.setYear(dateTool.getCurrentYear());
		dayitem.setMonth(dateTool.getCurrentMonth());
		dayitem.setDay(dateTool.getCurrentDay());
		dayitem.setAmpm(AMPM.am);
		preatten.setDayitem(dayitem);
		preatten.setPerson(person);
		return preatten;
	}

}
