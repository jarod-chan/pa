package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.application.BusioutService;
import cn.fyg.pa.domain.model.busiout.Busiout;
import cn.fyg.pa.domain.model.busiout.BusioutFactory;
import cn.fyg.pa.domain.model.busiout.BusioutRepository;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.shared.Result;

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
		return busiout.verifyself();
	}

}
