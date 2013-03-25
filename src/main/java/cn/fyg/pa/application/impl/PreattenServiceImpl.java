package cn.fyg.pa.application.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.application.PreattenService;
import cn.fyg.pa.domain.model.atten.preatten.Preatten;
import cn.fyg.pa.domain.model.atten.preatten.PreattenFactory;
import cn.fyg.pa.domain.model.atten.preatten.PreattenRepository;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.shared.Result;
import cn.fyg.pa.infrastructure.util.NoComputer;

@Service
public class PreattenServiceImpl implements PreattenService {
	
	@Resource
	PreattenRepository preattenRepository;

	@Override
	public Preatten create(Person person) {
		return PreattenFactory.create(person);
	}

	@Override
	public Result verify(Preatten preatten) {
		return preatten.verify();
	}

	@Override
	public String getNextNo(Person person, Long year, Long month) {
		String maxNo=preattenRepository.getMaxNo(person,year,month);
		String nextNo=NoComputer.computeNo(Preatten.BUSINESS_CODE, person.getKey(), maxNo);
		return nextNo;
	}

	@Override
	public Preatten save(Preatten preatten) {
		preatten.computeDayitemValueBeforeSave();
		return preattenRepository.save(preatten);
	}

	@Override
	public Preatten find(Long id) {
		return preattenRepository.find(id);
	}

	@Override
	public List<Preatten> getBusioutByPersonAndYearAndMonth(Person person,
			Long year, Long month) {
		return preattenRepository.findByPersonAndYearAndMonth(person,year,month);
	}

}
