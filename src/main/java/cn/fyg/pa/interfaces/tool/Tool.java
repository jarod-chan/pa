package cn.fyg.pa.interfaces.tool;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Random;

public class Tool {
	
	private static char[] ch={'a','b','c','d','e',
			'f','g','h','i','j',
			'k','l','m','n','o',
			'p','q','r','s','t',
			'u','v','w','x','y',
			'z'};
	
	private static NumberFormat  df=NumberFormat.getInstance();
	static{
		df.setMaximumFractionDigits(3);
		df.setMinimumFractionDigits(0);
	}
	
	public static String getPassword(Long passlen){
		int len=(passlen==null?3:passlen.intValue());
		
		Random random = new Random();
		String psd="";
		for (int i = 0; i < len; i++) {
			int rd = Math.abs(random.nextInt() % 26);
			psd+=ch[rd];
		}
		return psd;
	}
	
	public static String format(BigDecimal num){
		return df.format(num);
	}

}
