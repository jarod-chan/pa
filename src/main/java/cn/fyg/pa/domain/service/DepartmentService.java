package cn.fyg.pa.domain.service;

import java.util.List;

import cn.fyg.pa.domain.model.Department;

public interface DepartmentService {
	
	Department findByName(String name);

	List<Department> getAllDepartmentsOrderById();

}
