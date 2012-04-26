package cn.fyg.pa.application;

import cn.fyg.pa.interfaces.bean.LoginBean;
import cn.fyg.pa.interfaces.bean.LoginRetBean;

public interface LoginService {

	LoginRetBean checkLoginPerson(LoginBean login);
	
	boolean checkLogin(String username,String password);

}
