package cn.fyg.pa.domain.model.person;

import java.util.List;



public interface PersonRepository {

	Person find(Long id);

	Person save(Person person);
	
	void remove(Person person);

	void saveAll(List<Person> persons);
	
	Person findDepartmentMange(String department);

	Person findByName(String personname);

	int countStaffByType(TypeEnum type);

	List<Person> findPersonByManageOrderByDepartment(ManageEnum... mangeEnum);

	List<Person> getStaffByType(TypeEnum type);
	
	/**
	 * 获得同类型人员，过滤掉无效人员
	 * @param type
	 * @return
	 */
	List<Person> getStaffByTypeValid(TypeEnum type);
	
	List<Person> getStaffByTypeNotDeptValid(TypeEnum type,String department);

	List<Person> getStaffByDept(String department);
	
	/**
	 * 获得部门人员，过滤掉无效人员
	 * @param department
	 * @return
	 */
	List<Person> getStaffByDeptValid(String department);
	
	List<Person> getAllFyperson();
	
	List<Person> findPersonByManageOrderById(ManageEnum... mangeEnum);

}
