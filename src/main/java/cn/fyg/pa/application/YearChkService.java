package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.yearchk.EnableYearNotExist;
import cn.fyg.pa.interfaces.yearchk.PersonChkBean;

public interface YearChkService {

	List<PersonChkBean> getPersonChkResult(Person person)throws EnableYearNotExist;
	
}
