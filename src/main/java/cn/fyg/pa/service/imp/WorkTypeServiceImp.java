package cn.fyg.pa.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.dao.WorkTypeDao;
import cn.fyg.pa.model.WorkType;
import cn.fyg.pa.service.WorkTypeService;

@Service
public class WorkTypeServiceImp implements WorkTypeService {

	@Resource
	WorkTypeDao workTypeDao;
	
	@Override
	public List<WorkType> getAllWorkType() {
		return workTypeDao.getAll();
	}

}
