package cn.fyg.pa.application;

import cn.fyg.pa.domain.monthchk.MonthChk;
import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public interface MonthChkService {
	
	MonthChk save(MonthChk monthChk);
	
	MonthChk getCurrentMonthChk(Person person);
	
	MonthChk next(Long id) throws StateChangeException;
	
	MonthChk back(Long id) throws StateChangeException;

}
