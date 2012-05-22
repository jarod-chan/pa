package cn.fyg.pa.domain.service;

import java.util.List;

import cn.fyg.pa.domain.indicatortype.IdrType;

public interface IdrTypeService {
	
	IdrType find(Long id);
	
	IdrType save(IdrType idrType);

	void remove(Long id);

	List<IdrType> findAll();
}
