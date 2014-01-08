package cn.fyg.pa.interfaces.module.shared.session;

public interface SessionUtil {

	void setValue(String key, Object value);

	<T> T getValue(String key);

	void invalidate();
	
	String createToken();
	
	boolean checkToken(String token);

}