package cn.fyg.pa.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.dao.MonthChkDao;
import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.StateChangeException;
import cn.fyg.pa.model.enums.MonthChkEnum;
import cn.fyg.pa.service.MonthChkService;

@Service
public class MonthChkServiceImp implements MonthChkService{
	
	@Resource
	MonthChkDao monthChkDao;

	@Override
	public MonthChk find(Long id) {
		return monthChkDao.find(id);
	}

	@Override
	public List<MonthChk> getMonthChkByPeriodAndState(Long year, Long month,String department,MonthChkEnum... states) {
		return monthChkDao.findByPeriodAndState(year,month,department,states);
	}

	@Override
	public List<MonthChk> getMonthChkByDepartmentAndState(String department,MonthChkEnum... states) {
		return monthChkDao.findByPeriodAndState(null,null,department,states);
	}

	@Override
	public MonthChk getCurrentMonthChk(Person person) {
		return monthChkDao.getCurrMonthChk(person);
	}

	@Override
	public List<MonthChk> getAllFinishMonthChkByPerson(Person person) {
		return monthChkDao.getAllFinishMonthChkByPerson(person);
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

}
