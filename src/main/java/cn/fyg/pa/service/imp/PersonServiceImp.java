package cn.fyg.pa.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.dao.PersonDao;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.page.LoginPage;
import cn.fyg.pa.page.LoginRet;
import cn.fyg.pa.service.PersonService;
import cn.fyg.pa.tool.Constant;

@Service
public class PersonServiceImp implements PersonService {
	
	@Resource
	private PersonDao personDao;
	
	public LoginRet checkLoginPerson(LoginPage login){
		Person person=personDao.findByName(login.getUsername());
		if(isPerson(person,login)){
			return getPerson(person);
		}
		if(isAdmin(person,login)){
			return getAdmin();
		}
		return getNopassPerson();
	}
	
	private LoginRet getNopassPerson() {
		LoginRet ret=new LoginRet();
		ret.setPass(false);
		return ret;
	}

	private boolean isPerson(Person person,LoginPage login) {
		return person != null 
				&& person.getChkstr().equals(login.getPassword());
	}
	
	private LoginRet getPerson(Person person) {
		LoginRet ret=new LoginRet();
		ret.setPersonid(person.getId().toString());
		ret.setPass(true);
		ret.setChkstr(person.getChkstr());
		ret.setMange(person.getManage().toString());
		return ret;
	}

	private boolean isAdmin(Person person, LoginPage login) {
		return person == null
				&& Constant.ADMIN_USERNAME.equals(login.getUsername())
				&& Constant.ADMIN_PASSWORD.equals(login.getPassword());
	}

	private LoginRet getAdmin() {
		LoginRet ret=new LoginRet();
		ret.setPass(true);
		ret.setChkstr(Constant.ADMIN_PASSWORD);
		ret.setMange("A");
		return ret;
	}

	@Override
	public Person save(Person person) {
		return personDao.save(person);
	}

	@Override
	public Person find(Long id) {
		return personDao.find(id);
	}

}
