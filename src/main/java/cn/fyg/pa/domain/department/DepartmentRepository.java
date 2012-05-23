package cn.fyg.pa.domain.department;

import java.util.List;


public interface DepartmentRepository {
	
	Department find(Long id);
	
	List<Department> getAllDepartmentsOrderById();
	
	Department findByName(String department);
}
