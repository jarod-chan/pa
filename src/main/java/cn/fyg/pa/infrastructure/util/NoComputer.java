package cn.fyg.pa.infrastructure.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

/**
 *计算下个单据编号
 */
public class NoComputer {
	
	private static final String SEPARATE = "-";
	
	public static String computeNo(String businessCode,String key,String maxNo){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		String prev=businessCode+simpleDateFormat.format(calendar.getTime()).substring(2);
		String midl=key.toUpperCase();
		String post=getNextFlowNo(maxNo);
		return prev+SEPARATE+midl+SEPARATE+post;
	}


	private static String getNextFlowNo(String maxNo) {
		if(StringUtils.isBlank(maxNo)){
			return "1";
		}
		maxNo=maxNo.substring(maxNo.lastIndexOf(SEPARATE)+1);
		return String.valueOf(Integer.valueOf(maxNo).intValue()+1);
	}

}
