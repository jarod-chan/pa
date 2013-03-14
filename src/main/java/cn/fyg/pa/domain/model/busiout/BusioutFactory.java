package cn.fyg.pa.domain.model.busiout;

import cn.fyg.pa.domain.model.person.Person;

public class BusioutFactory {
	
	public static Busiout create(Person person){
		Busiout busiout=new Busiout();
		busiout.setBusiState(BusiState.new_);
		busiout.setSingel(Boolean.TRUE);
		busiout.setPerson(person);
		return busiout;
	}

}
