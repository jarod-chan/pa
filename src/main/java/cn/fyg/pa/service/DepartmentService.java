package cn.fyg.pa.service;

import java.util.List;

import cn.fyg.pa.model.Department;

public interface DepartmentService {
	
	Department findByName(String name);

	List<Department> getAllDepartmentsOrderById();

}
