package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.PersonRepository;
import cn.fyg.pa.domain.model.Person;
import cn.fyg.pa.domain.model.enums.ManageEnum;

@Repository
public class PersonRepositoryImp implements PersonRepository {
	
	@Resource
	PersonDao personDao;

	@Override
	public List<Person> getPersonByManage(ManageEnum... mangeEnum) {
		return personDao.findByManage(mangeEnum);
	}

}
