package cn.fyg.pa.application;

import cn.fyg.pa.interfaces.web.module.system.login.LoginBean;
import cn.fyg.pa.interfaces.web.module.system.login.LoginRetBean;

public interface LoginService {

	LoginRetBean checkLoginPerson(LoginBean login);
	
	boolean checkLogin(String username,String password);

}
