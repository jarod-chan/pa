package cn.fyg.pa.interfaces.module.person.summary;

import javax.servlet.http.HttpSession;

import cn.fyg.pa.interfaces.module.system.login.LoginCtl;

public class SummaryCommon {
	
	public static void hideSummaryInfo(HttpSession session){
		session.setAttribute(LoginCtl.IS_SHOW_PERSON_SUMMARY_INFO,Boolean.FALSE);
	}

}
