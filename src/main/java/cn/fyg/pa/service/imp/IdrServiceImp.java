package cn.fyg.pa.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.dao.IdrTypeDao;
import cn.fyg.pa.model.IdrType;
import cn.fyg.pa.service.IdrTypeService;

@Service
public class IdrServiceImp implements IdrTypeService {
	
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
