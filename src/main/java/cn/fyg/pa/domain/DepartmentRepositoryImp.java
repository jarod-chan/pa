package cn.fyg.pa.domain;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.Department;
import cn.fyg.pa.infrastructure.perisistence.DepartmentDao;

@Repository
public class DepartmentRepositoryImp implements DepartmentRepository {

	@Resource
	DepartmentDao departmentDao;
	
	@Override
	public List<Department> getAllDepartmentsOrderById() {
		return departmentDao.findAllDepartmentsOrderById();
	}

}
