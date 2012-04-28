package cn.fyg.pa.application.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.application.YearChkService;
import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.yearchk.EnableYearNotExist;
import cn.fyg.pa.domain.yearchk.YearChkRepositroy;
import cn.fyg.pa.domain.yearchk.YearConfigRepositroy;
import cn.fyg.pa.interfaces.yearchk.PersonChkBean;

@Service
public class YearCheServiceImp implements YearChkService {
	
	@Resource
	YearConfigRepositroy yearConfigRepositroy;
	@Resource 
	YearChkRepositroy yearChkRepositroy;

	@Override
	public List<PersonChkBean> getPersonChkResult(Person person)throws EnableYearNotExist{
		Long year=yearConfigRepositroy.getEnableYear();
		return yearChkRepositroy.getPersonYearChkResult(year, person);
	}
}
