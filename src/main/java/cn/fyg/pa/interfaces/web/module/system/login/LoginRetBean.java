package cn.fyg.pa.interfaces.web.module.system.login;

public class LoginRetBean {
	
	private String personid;
	private String name;
	private String chkstr;
	private String mange;
	private boolean isPass;
	
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public boolean isPass() {
		return isPass;
	}
	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}
	public String getChkstr() {
		return chkstr;
	}
	public void setChkstr(String chkstr) {
		this.chkstr = chkstr;
	}
	public String getMange() {
		return mange;
	}
	public void setMange(String mange) {
		this.mange = mange;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
