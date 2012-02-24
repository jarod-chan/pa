package cn.fyg.pa.tool;

import java.util.regex.Pattern;

public class PasswordPage {
	
	private String chkstr;
	
	private String confirmchkstr;
	
	private String message;
	
	public static final String PASSWORD_PATTERN="^\\w+$";
	
	public static final int PASSWORD_LENGTH=6;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getChkstr() {
		return chkstr;
	}

	public void setChkstr(String chkstr) {
		this.chkstr = chkstr;
	}

	public String getConfirmchkstr() {
		return confirmchkstr;
	}

	public void setConfirmchkstr(String confirmchkstr) {
		this.confirmchkstr = confirmchkstr;
	}
	
	public boolean verifyPassword(){
		if(!confirmchkstr.equals(chkstr)){
			message="两次密码输入不一致！";
			return false;
		}
		if(!verifyPattern()){
			message="密码只能输入数字字母下划线";
			return false;
		}
		if(!verifyLength()){
			message="密码长度必须大于等于"+PASSWORD_LENGTH;
			return false;
		}
		return true;
	}
	
	private boolean verifyPattern(){
		Pattern pattern=Pattern.compile(PASSWORD_PATTERN);
		return pattern.matcher(confirmchkstr).matches();
	}
	
	private boolean verifyLength(){
		return confirmchkstr.length()>=PASSWORD_LENGTH;
	}

}
