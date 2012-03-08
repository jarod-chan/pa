package cn.fyg.pa.service;

import cn.fyg.pa.model.Person;
import cn.fyg.pa.page.LoginPage;
import cn.fyg.pa.page.LoginRet;

public interface PersonService {
	
	LoginRet checkLoginPerson(LoginPage login);
	
	Person save(Person person);
	
	Person find(Long id);
}
