package cn.fyg.pa.domain.model.atten.preatten;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.infrastructure.util.DateTool;

public class PreattenFactory {
	
	public static Preatten create(Person person){
		DateTool dateTool=new DateTool();
		Preatten preatten=new Preatten();
		preatten.setState(Prestate.new_);
		preatten.setYear(dateTool.getCurrentYear());
		preatten.setMonth(dateTool.getCurrentMonth());
		preatten.setPerson(person);
		return preatten;
	}

}
