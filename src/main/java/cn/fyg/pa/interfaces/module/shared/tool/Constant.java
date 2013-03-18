package cn.fyg.pa.interfaces.module.shared.tool;

import java.math.BigDecimal;

public class Constant {

	public static final String PROCESS_FILE = "processfile";
	public static final String MESSAGE_NAME="message";
	
	
	public static final String ADMIN_USERNAME="admin";
	public static final String ADMIN_PASSWORD="fyg9900";
	
	public static final String INIT_PASSWORD="123456";
	public static final String COOKIE_FLAG="chkstr";
	public static final BigDecimal POINT_LEVEL=new BigDecimal("5");
	
	public static final BigDecimal ZERO=new BigDecimal(0);
	public static final BigDecimal HUNDRED=new BigDecimal(100);
	public static final int SCALE=6;
	public static final int ROUND_MODEL=BigDecimal.ROUND_HALF_UP;
}
