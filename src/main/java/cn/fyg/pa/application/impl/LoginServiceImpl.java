package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.application.LoginService;
import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.person.PersonRepository;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;
import cn.fyg.pa.interfaces.module.system.login.LoginBean;
import cn.fyg.pa.interfaces.module.system.login.LoginRetBean;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Resource
	PersonRepository personRepository;
	
	@Override
	public LoginRetBean checkLoginPerson(LoginBean login){
		Person person=personRepository.findByName(login.getUsername());
		if(isPerson(person,login)){
			return getPerson(person);
		}
		if(isAdmin(person,login)){
			return getAdmin();
		}
		return getNopassPerson();
	}
	
	private LoginRetBean getNopassPerson() {
		LoginRetBean ret=new LoginRetBean();
		ret.setPass(false);
		return ret;
	}

	private boolean isPerson(Person person,LoginBean login) {
		return person != null 
				&& person.getChkstr().equals(login.getPassword());
	}
	
	private LoginRetBean getPerson(Person person) {
		LoginRetBean ret=new LoginRetBean();
		ret.setPersonid(person.getId().toString());
		ret.setName(person.getName());
		ret.setPass(true);
		ret.setChkstr(person.getChkstr());
		ret.setMange(person.getManage().toString());
		return ret;
	}

	private boolean isAdmin(Person person, LoginBean login) {
		return person == null
				&& Constant.ADMIN_USERNAME.equals(login.getUsername())
				&& Constant.ADMIN_PASSWORD.equals(login.getPassword());
	}

	private LoginRetBean getAdmin() {
		LoginRetBean ret=new LoginRetBean();
		ret.setName(Constant.ADMIN_USERNAME);
		ret.setPass(true);
		ret.setChkstr(Constant.ADMIN_PASSWORD);
		ret.setMange("A");
		return ret;
	}

	@Override
	public boolean checkLogin(String username, String password) {
		// TODO 系统待重构
		return false;
	}
}
