package cn.fyg.pa.application.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.application.BusioutService;
import cn.fyg.pa.domain.model.atten.busiout.Busiout;
import cn.fyg.pa.domain.model.atten.busiout.BusioutFactory;
import cn.fyg.pa.domain.model.atten.busiout.BusioutRepository;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.shared.Result;
import cn.fyg.pa.infrastructure.util.NoComputer;

@Service
public class BusioutServiceImpl implements BusioutService {
	
	@Resource
	BusioutRepository busioutRepository;

	@Override
	public Busiout create(Person person) {
		Busiout busiout=BusioutFactory.create(person);
		return busiout;
	}

	@Override
	public Busiout save(Busiout busiout) {
		return busioutRepository.save(busiout);
	}

	@Override
	public Result verify(Busiout busiout) {
		return busiout.verify();
	}

	@Override
	public String getNextNo(Person person,Long year,Long month) {
		String maxNo = busioutRepository.getMaxNo(person, year, month);
		String nextNo = NoComputer.computeNo(Busiout.BUSINESS_CODE, person.getKey(), maxNo);
		return nextNo;
	}

	@Override
	public List<Busiout> getBusioutByPersonAndYearAndMonth(Person person,Long year, Long month) {
		return busioutRepository.getBusioutByPersonAndYearAndMonth(person,year,month);
	}

	@Override
	public Busiout find(Long id) {
		return busioutRepository.find(id);
	}
	
	

}
