package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.MonthChkService;
import cn.fyg.pa.domain.monthchk.MonthChk;
import cn.fyg.pa.domain.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.monthchk.MonthChkFactory;
import cn.fyg.pa.domain.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.shared.state.StateChangeException;

@Service
public class MonthChkServiceImpl implements MonthChkService{
	
	@Resource
	MonthChkRepository monthChkRepository;
	
	@Override
	public MonthChk getCurrentMonthChk(Person person) {
		MonthChk monthChk=monthChkRepository.findMaxMonthMonthChk(person);
		if(monthChk==null){
			return MonthChkFactory.createInitMonthChk(person);
		}
		if(isMaxMonthMonthChkFinished(monthChk)){
			return MonthChkFactory.createNextMonthChk(person, monthChk.getYear(),monthChk.getMonth());
		}
		return monthChk;
	}

	private boolean isMaxMonthMonthChkFinished(MonthChk monthChk) {
		return monthChk.getState()==MonthChkEnum.FINISHED;
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
