package cn.fyg.pa.domain.service;

import cn.fyg.pa.application.bean.LoginBean;
import cn.fyg.pa.application.bean.LoginRetBean;
import cn.fyg.pa.domain.model.Person;

public interface PersonService {
	
	LoginRetBean checkLoginPerson(LoginBean login);
	
	Person save(Person person);
	
	Person find(Long id);
	
	Person getDeptMange(String department);
}
