package cn.fyg.pa.domain.service;

import cn.fyg.pa.application.page.LoginPage;
import cn.fyg.pa.application.page.LoginRet;
import cn.fyg.pa.domain.model.Person;

public interface PersonService {
	
	LoginRet checkLoginPerson(LoginPage login);
	
	Person save(Person person);
	
	Person find(Long id);
	
	Person getDeptMange(String department);
}
