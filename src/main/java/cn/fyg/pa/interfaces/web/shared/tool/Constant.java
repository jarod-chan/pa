package cn.fyg.pa.interfaces.web.shared.tool;

import java.math.BigDecimal;

public class Constant {
	public static String ADMIN_USERNAME="admin";
	public static String ADMIN_PASSWORD="fyg9900";
	public static String INIT_PASSWORD="123456";
	public static String COOKIE_FLAG="chkstr";
	public static String MESSAGE_NAME="message";
	public static BigDecimal POINT_LEVEL=new BigDecimal("5");
	
	public static BigDecimal ZERO=new BigDecimal(0);
	public static BigDecimal HUNDRED=new BigDecimal(100);
	public static int SCALE=6;
	public static int SCALE_TWO=2;
	public static int ROUND_MODEL=BigDecimal.ROUND_HALF_UP;
}
