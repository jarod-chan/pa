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

	List<Person> findPersonByManage(ManageEnum... mangeEnum);

	List<Person> getStaffByType(TypeEnum type);

	List<Person> getStaffByDept(String department);
	
	List<Person> getAllFyperson();

}
