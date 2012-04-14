package cn.fyg.pa.interfaces.page;

import java.util.regex.Pattern;

public class PasswordPage {
	
	private String oldcsr;
	
	private String newcsr;
	
	private String confirmcsr;
	
	private String realcsr;
	
	private String message;
	
	private boolean pass=false;
	
	private static final String PASSWORD_PATTERN="^\\w+$";
	
	private static final int PASSWORD_LENGTH=6;
	
	public boolean isPass() {
		return pass;
	}

	public String getOldcsr() {
		return oldcsr;
	}

	public void setOldcsr(String oldcsr) {
		this.oldcsr = oldcsr;
	}

	public String getNewcsr() {
		return newcsr;
	}

	public void setNewcsr(String newcsr) {
		this.newcsr = newcsr;
	}

	public String getConfirmcsr() {
		return confirmcsr;
	}

	public void setConfirmcsr(String confirmcsr) {
		this.confirmcsr = confirmcsr;
	}

	public String getRealcsr() {
		return realcsr;
	}

	public void setRealcsr(String realcsr) {
		this.realcsr = realcsr;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void verifyPassword(){
		if(!oldcsr.equals(realcsr)){
			message="原密码输入错误！";
			pass=false;
			return;
		}
		if(!confirmcsr.equals(newcsr)){
			message="两次新密码输入不一致！";
			pass=false;
			return;
		}
		if(!verifyPattern(confirmcsr)){
			message="新密码只能输入数字字母下划线！";
			pass=false;
			return;
		}
		if(!verifyLength(confirmcsr)){
			message="新密码长度必须大于等于"+PASSWORD_LENGTH+"!";
			pass=false;
			return;
		}
		message="新密码保存成功！";
		pass=true;
	}
	
	private boolean verifyPattern(String str){
		Pattern pattern=Pattern.compile(PASSWORD_PATTERN);
		return pattern.matcher(str).matches();
	}
	
	private boolean verifyLength(String str){
		return str.length()>=PASSWORD_LENGTH;
	}

}
