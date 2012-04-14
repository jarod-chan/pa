package cn.fyg.pa.domain.service;

import cn.fyg.pa.domain.model.Person;
import cn.fyg.pa.interfaces.bean.LoginBean;
import cn.fyg.pa.interfaces.bean.LoginRetBean;

public interface PersonService {
	
	LoginRetBean checkLoginPerson(LoginBean login);
	
	Person save(Person person);
	
	Person find(Long id);
	
	Person getDeptMange(String department);
}
