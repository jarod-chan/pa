package cn.fyg.pa.interfaces.module.questionaires.login;

public class KeyBean {
	private static final String SEPARATE="-";
	
	private String keypart1;
	
	private String keypart2;
	
	
	public String getKey(){
		return keypart1+SEPARATE+keypart2;
	}
	
	public String getKeypart1() {
		return keypart1;
	}
	public void setKeypart1(String keypart1) {
		this.keypart1 = keypart1;
	}
	public String getKeypart2() {
		return keypart2;
	}
	public void setKeypart2(String keypart2) {
		this.keypart2 = keypart2;
	}

}
