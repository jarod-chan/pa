package cn.fyg.pa.domain.deptindicator;

import java.util.List;

import cn.fyg.pa.domain.department.Department;

public interface DeptIndicatorRepository {
	
	DeptIndicator find(Long id);
	
	DeptIndicator findByYearAndDepartment(Long year,Department department);
	
	DeptIndicator save(DeptIndicator deptIndicator);
	
	List<DeptIndicator> findByYear(Long year);

}
