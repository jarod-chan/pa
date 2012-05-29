package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.IdrYearTypeWeightService;
import cn.fyg.pa.domain.model.yeartypeweight.IdrYearTypeWeight;
import cn.fyg.pa.domain.model.yeartypeweight.IdrYearTypeWeightFactory;
import cn.fyg.pa.domain.model.yeartypeweight.IdrYearTypeWeightRepository;

@Service
public class IdrYearTypeWeightServiceImpl implements IdrYearTypeWeightService {

	@Resource
	IdrYearTypeWeightRepository idrYearTypeWeightRepository;
	
	@Override
	public IdrYearTypeWeight getByYear(Long year) {
		IdrYearTypeWeight idrYearTypeWeight=idrYearTypeWeightRepository.find(year);
		if(idrYearTypeWeight==null){
			return IdrYearTypeWeightFactory.createIdrYearTypeWeight(year);
		}
		return idrYearTypeWeight;
	}

	@Override
	@Transactional
	public IdrYearTypeWeight save(IdrYearTypeWeight idrYearTypeWeight) {
		return idrYearTypeWeightRepository.save(idrYearTypeWeight);
	}

}
