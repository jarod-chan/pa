package cn.fyg.pa.domain.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.domain.model.indicatortype.IdrType;
import cn.fyg.pa.domain.service.IdrTypeService;
import cn.fyg.pa.infrastructure.perisistence.IdrTypeDao;

@Service
public class IdrTypeServiceImp implements IdrTypeService {
	
	@Resource
	IdrTypeDao idrTypeDao;

	@Override
	public IdrType find(Long id) {
		return idrTypeDao.find(id);
	}

	@Override
	@Transactional
	public IdrType save(IdrType idrType) {
		return idrTypeDao.save(idrType);
	}
	
	@Override
	@Transactional
	public void remove(Long id) {
		IdrType idrType=idrTypeDao.find(id);
		idrTypeDao.remove(idrType);
	}

	@Override
	public List<IdrType> findAll() {
		return idrTypeDao.findAll();
	}

}
