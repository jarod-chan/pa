package cn.fyg.pa.application.impl.temp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.MonthChkService;
import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkFactory;
import cn.fyg.pa.domain.model.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.shared.state.StateChangeException;

@Service
public class MonthChkServiceImpl implements MonthChkService{
	
	@Resource
	MonthChkRepository monthChkRepository;
	
	@Override
	public MonthChk getCurrentMonthChk(Person person) {
		MonthChk monthChk=monthChkRepository.findMonthChkByPeriodAndPerson(2015L, 3L, person);
		if(monthChk==null){
			return MonthChkFactory.createMonthChk(person,2015L,3L);
		}
		return monthChk;
	}
	
	@Override
	@Transactional
	public MonthChk save(MonthChk monthChk) {
		return monthChkRepository.save(monthChk);
	}
	
	@Override
	@Transactional
	public MonthChk next(Long id) throws StateChangeException {
		MonthChk monthChk=monthChkRepository.find(id);
		monthChk.next();
		return monthChk;
	}

	@Override
	@Transactional
	public MonthChk back(Long id) throws StateChangeException {
		MonthChk monthChk=monthChkRepository.find(id);
		monthChk.back();
		return monthChk;
	}

	
}
