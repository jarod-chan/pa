package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.IdrTypeService;
import cn.fyg.pa.domain.model.indicatortype.IdrType;
import cn.fyg.pa.domain.model.indicatortype.IdrTypeRepository;

@Service
public class IdrTypeServiceImpl implements IdrTypeService {
	
	@Resource
	IdrTypeRepository idrTypeRepository;

	@Override
	@Transactional
	public IdrType save(IdrType idrType) {
		return idrTypeRepository.save(idrType);
	}

	@Override
	@Transactional
	public void remove(Long id) {
		IdrType idrType = idrTypeRepository.find(id);
		idrTypeRepository.remove(idrType);
	}

}
