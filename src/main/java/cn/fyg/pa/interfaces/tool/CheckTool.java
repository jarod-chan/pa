package cn.fyg.pa.interfaces.tool;

public class CheckTool {
	
	//TODO 判断是否正浮点数的正则表达式
	public static boolean checkFloat(String numstr){
		if(numstr==null) return false;
		String reg="((\\+)?[0-9]+(\\.[0-9]+)?)+";
		return numstr.matches(reg);
	}

}
