package cn.fyg.pa.interfaces.module.questionaires;

public class KeyBean {
	private static final String SEPARATE="-";
	
	private String keypart1;
	
	private String keypart2;
	
	private String keypart3;
	
	private String keypart4;
	
	public String getKey(){
		return keypart1+SEPARATE+keypart2+SEPARATE+keypart3+SEPARATE+keypart4;
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
	public String getKeypart3() {
		return keypart3;
	}
	public void setKeypart3(String keypart3) {
		this.keypart3 = keypart3;
	}
	public String getKeypart4() {
		return keypart4;
	}
	public void setKeypart4(String keypart4) {
		this.keypart4 = keypart4;
	}
	
	

}
