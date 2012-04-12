package cn.fyg.pa.domain;

import java.util.List;

import cn.fyg.pa.domain.model.Person;
import cn.fyg.pa.domain.model.enums.ManageEnum;

public interface PersonRepository {

	List<Person> getPersonByManage(ManageEnum... mangeEnum);
}
