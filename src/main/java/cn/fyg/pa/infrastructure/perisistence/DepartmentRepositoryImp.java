package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.DepartmentRepository;
import cn.fyg.pa.domain.model.Department;

@Repository
public class DepartmentRepositoryImp implements DepartmentRepository {

	@Resource
	DepartmentDao departmentDao;
	
	@Override
	public List<Department> getAllDepartmentsOrderById() {
		return departmentDao.findAllDepartmentsOrderById();
	}

}
