package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.domain.person.ManageEnum;
import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.person.PersonRepository;
import cn.fyg.pa.domain.person.TypeEnum;
import cn.fyg.pa.interfaces.bean.LoginBean;
import cn.fyg.pa.interfaces.bean.LoginRetBean;
import cn.fyg.pa.interfaces.tool.Constant;

@Service
public class PersonRepositoryJpa implements PersonRepository {
	
	@Resource
	private PersonDao personDao;
	
	
	
	@Override
	public Person findByName(String personname){
		return personDao.findByName(personname);
	}

	@Override
	public Person save(Person person) {
		return personDao.save(person);
	}

	@Override
	public Person find(Long id) {
		return personDao.find(id);
	}

	@Override
	public Person getDeptMange(String department) {
		return personDao.findDeptMange(department);
	}
	
	@Override
	public List<Person> getPersonByManage(ManageEnum... mangeEnum) {
		return personDao.findByManage(mangeEnum);
	}

	@Override
	public int countStaffByType(TypeEnum type) {
		return personDao.countStaffByType(type);
	}
	
	@Override
	public List<Person>  getStaffByType(TypeEnum type){
		return personDao.getStaffByType(type);
	}


}
