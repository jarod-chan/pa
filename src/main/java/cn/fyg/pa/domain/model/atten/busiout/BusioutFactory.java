package cn.fyg.pa.domain.model.atten.busiout;

import cn.fyg.pa.domain.model.atten.common.AMPM;
import cn.fyg.pa.domain.model.atten.common.Dayitem;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.infrastructure.util.DateTool;

public class BusioutFactory {
	
	public static Busiout create(Person person){
		DateTool dateTool=new DateTool();
		Busiout busiout=new Busiout();
		busiout.setBusiState(BusiState.new_);
		
		Dayitem begDayitem=new Dayitem();
		begDayitem.setYear(dateTool.getCurrentYear());
		begDayitem.setMonth(dateTool.getCurrentMonth());
		begDayitem.setDay(dateTool.getCurrentDay());
		begDayitem.setAmpm(AMPM.am);
		busiout.setBegDayitem(begDayitem);
		
		Dayitem endDayitem=new Dayitem();
		endDayitem.setYear(dateTool.getCurrentYear());
		endDayitem.setMonth(dateTool.getCurrentMonth());
		endDayitem.setDay(dateTool.getCurrentDay());
		endDayitem.setAmpm(AMPM.am);
		busiout.setEndDayitem(endDayitem);
		
		busiout.setPerson(person);
		return busiout;
	}

}
