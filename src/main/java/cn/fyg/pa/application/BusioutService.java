package cn.fyg.pa.application;

import cn.fyg.pa.domain.model.busiout.Busiout;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.shared.Result;

public interface BusioutService {
	
	Busiout create(Person person);
	
	Result verify(Busiout busiout);
	
	Busiout save(Busiout busiout);
	
	String getNextNo(Person person,Long year,Long month);

}
