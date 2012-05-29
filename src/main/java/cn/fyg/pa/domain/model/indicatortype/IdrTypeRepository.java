package cn.fyg.pa.domain.model.indicatortype;

import java.util.List;

public interface IdrTypeRepository {

	IdrType find(Long id);
	
	List<IdrType> findAll();

	IdrType save(IdrType idrType);

	void remove(IdrType idrType);
	
}
