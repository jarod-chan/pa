package cn.fyg.pa.service;

import java.util.List;

import cn.fyg.pa.model.IdrType;

public interface IdrTypeService {
	
	IdrType find(Long id);
	
	IdrType save(IdrType idrType);

	void remove(Long id);

	List<IdrType> findAll();
}
