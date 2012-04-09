package cn.fyg.pa.domain.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.domain.model.WorkType;
import cn.fyg.pa.domain.service.WorkTypeService;
import cn.fyg.pa.infrastructure.perisistence.WorkTypeDao;

@Service
public class WorkTypeServiceImp implements WorkTypeService {

	@Resource
	WorkTypeDao workTypeDao;
	
	@Override
	public List<WorkType> getAllWorkType() {
		return workTypeDao.getAll();
	}

}
