package cn.fyg.pa.domain.model.busiout;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.infrastructure.util.DateTool;

public class BusioutFactory {
	
	public static Busiout create(Person person){
		DateTool dateTool=new DateTool();
		Busiout busiout=new Busiout();
		busiout.setBusiState(BusiState.new_);
		busiout.setYear(dateTool.getCurrentYear());
		busiout.setMonth(dateTool.getCurrentMonth());
		busiout.setPerson(person);
		return busiout;
	}

}
