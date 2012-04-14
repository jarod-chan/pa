package cn.fyg.pa.domain.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.domain.model.Person;
import cn.fyg.pa.domain.service.PersonService;
import cn.fyg.pa.infrastructure.perisistence.PersonDao;
import cn.fyg.pa.interfaces.bean.LoginBean;
import cn.fyg.pa.interfaces.bean.LoginRetBean;
import cn.fyg.pa.interfaces.tool.Constant;

@Service
public class PersonServiceImp implements PersonService {
	
	@Resource
	private PersonDao personDao;
	
	public LoginRetBean checkLoginPerson(LoginBean login){
		Person person=personDao.findByName(login.getUsername());
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

}
