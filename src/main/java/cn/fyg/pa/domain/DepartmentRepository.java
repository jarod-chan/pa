package cn.fyg.pa.domain;

import java.util.List;

import cn.fyg.pa.domain.model.Department;

public interface DepartmentRepository {

	List<Department> getAllDepartmentsOrderById();
}
