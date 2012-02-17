package cn.fyg.pa.tool;

public class NumToChinese {
	
	private static String[] monthChinese={"一","二","三","四","五","六","七","八","九","十","十一","十二"};
	
	public static String monthChinese(Long month){
		int intMonth = month.intValue()-1;
		if(intMonth>=0&&intMonth<=11){
			return monthChinese[intMonth];
		}
		throw new IllegalArgumentException("月份超出范围");
	}

}
