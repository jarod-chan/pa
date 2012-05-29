package cn.fyg.pa.application;

import cn.fyg.pa.domain.model.indicatortype.IdrType;

public interface IdrTypeService {
	
	IdrType save(IdrType idrType);

	void remove(Long id);

}
