package cn.fyg.pa.domain.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.domain.model.MonthChk;
import cn.fyg.pa.domain.model.StateChangeException;
import cn.fyg.pa.domain.model.enums.MonthChkEnum;
import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.service.MonthChkService;
import cn.fyg.pa.infrastructure.perisistence.MonthChkDao;
import cn.fyg.pa.interfaces.tool.CMonthChk;

@Service
public class MonthChkServiceImp implements MonthChkService{
	
	@Resource
	MonthChkDao monthChkDao;

	@Override
	public MonthChk find(Long id) {
		return monthChkDao.find(id);
	}

	@Override
	@Transactional
	public MonthChk save(MonthChk monthChk) {
		return monthChkDao.save(monthChk);
	}

	@Override
	@Transactional
	public MonthChk next(Long id) throws StateChangeException {
		MonthChk monthChk=monthChkDao.find(id);
		monthChk.next();
		return monthChk;
	}

	@Override
	@Transactional
	public MonthChk back(Long id) throws StateChangeException {
		MonthChk monthChk=monthChkDao.find(id);
		monthChk.back();
		return monthChk;
	}

	@Override
	public MonthChk getCurrentMonthChk(Person person) {
		MonthChk monthChk=monthChkDao.findMaxMonthMonthChk(person);
		if(monthChk==null){
			return initMonthChk(person);
		}
		if(isMaxMonthMonthChkFinished(monthChk)){
			return nextMonthMonthChk(monthChk);
		}
		return monthChk;
	}

	private MonthChk initMonthChk(Person person) {
		MonthChk monthChk=new MonthChk();
		monthChk.setPerson(person);
		monthChk.setYear(CMonthChk.INIT_YEAR);
		monthChk.setMonth(CMonthChk.INIT_MONTH);
		monthChk.setState(MonthChkEnum.SAVED);
		return monthChk;
	}

	private boolean isMaxMonthMonthChkFinished(MonthChk monthChk) {
		return monthChk.getState()==MonthChkEnum.FINISHED;
	}

	private MonthChk nextMonthMonthChk(MonthChk finishedMonthChk) {
		MonthChk monthChk=new MonthChk();
		monthChk.setPerson(finishedMonthChk.getPerson());
		monthChk.setState(MonthChkEnum.SAVED);
		if(finishedMonthChk.getMonth().intValue()==12){
			monthChk.setYear(finishedMonthChk.getYear()+1);
			monthChk.setMonth(1L);
		}else{
			monthChk.setYear(finishedMonthChk.getYear());
			monthChk.setMonth(finishedMonthChk.getMonth()+1);
		}
		return monthChk;
	}

	@Override
	public List<MonthChk> getMonthChkByPeriodAndDepartmentAndState(Long year, Long month,String department,MonthChkEnum... states) {
		return monthChkDao.findByPeriodAndDepartmentAndPersonAndState(year,month,department,null,states);
	}

	@Override
	public List<MonthChk> getMonthChkByDepartmentAndState(String department,MonthChkEnum... states) {
		return monthChkDao.findByPeriodAndDepartmentAndPersonAndState(null,null,department,null,states);
	}

	@Override
	public List<MonthChk> getMonthChkByPersonAndState(Long year, Person person,MonthChkEnum... states) {
		return monthChkDao.findByPeriodAndDepartmentAndPersonAndState(year,null,null,person, states);
	}



}
