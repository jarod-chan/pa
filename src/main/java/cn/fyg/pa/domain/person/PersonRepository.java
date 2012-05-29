package cn.fyg.pa.domain.person;

import java.util.List;



public interface PersonRepository {

	Person find(Long id);

	Person save(Person person);
	
	Person findDepartmentMange(String department);

	Person findByName(String personname);

	int countStaffByType(TypeEnum type);

	List<Person> findPersonByManage(ManageEnum... mangeEnum);

	List<Person> getStaffByType(TypeEnum type);

	List<Person> getStaffByDept(String department);
}
