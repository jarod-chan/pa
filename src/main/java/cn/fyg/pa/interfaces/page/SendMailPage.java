package cn.fyg.pa.interfaces.page;

import org.apache.commons.lang.StringUtils;

public class SendMailPage {
	
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean checkPage(){
		return StringUtils.isNotBlank(username);
	}

}
