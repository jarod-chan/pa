package cn.fyg.pa.domain.model.yearchk;

import java.util.List;

import cn.fyg.pa.domain.model.person.Person;

public interface YearMangeChkRepositroy {
	
	List<Object[]> getPseronPointsByDepartment(Long year,String department);
	
	List<Fychkmange> getPseronChkmange(Long year,Person person);
	
	void removeList(List<Fychkmange> list);
	
	void saveList(List<Fychkmange> list);

}
