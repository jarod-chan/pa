package cn.fyg.pa.domain.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.domain.model.Department;
import cn.fyg.pa.domain.service.DepartmentService;
import cn.fyg.pa.infrastructure.perisistence.DepartmentDao;

@Service
public class DepartmentServiceImp implements DepartmentService {

	@Resource
	DepartmentDao departmentDao;
	
	@Override
	public Department findByName(String name) {
		return departmentDao.findByName(name);
	}
	
	@Override
	public List<Department> getAllDepartmentsOrderById(){
		return departmentDao.findAllDepartmentsOrderById();
	}

}
