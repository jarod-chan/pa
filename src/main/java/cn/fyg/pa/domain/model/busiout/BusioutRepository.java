package cn.fyg.pa.domain.model.busiout;

import cn.fyg.pa.domain.model.person.Person;

public interface BusioutRepository {
	
	Busiout save(Busiout busiout);
	
	String getMaxNo(Person person,Long year,Long month);
	
}
