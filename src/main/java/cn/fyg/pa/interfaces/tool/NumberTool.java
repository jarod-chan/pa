package cn.fyg.pa.interfaces.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO  有错误
public class NumberTool {
	/**
	 * 检查日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static boolean checkDate(String date) {
		String eL = "^((//d{2}(([02468][048])|([13579][26]))[//-/////s]?((((0?[13578])|(1[02]))[//-/////s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[//-/////s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[//-/////s]?((0?[1-9])|([1-2][0-9])))))|(//d{2}(([02468][1235679])|([13579][01345789]))[//-/////s]?((((0?[13578])|(1[02]))[//-/////s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[//-/////s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[//-/////s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(//s(((0?[0-9])|([1][0-9])|([2][0-3]))//:([0-5]?[0-9])((//s)|(//:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date);
		boolean b = m.matches();
		return b;
	}

	/**
	 * 检查整数
	 * 
	 * @param num
	 * @param type
	 *            "0+":非负整数 "+":正整数 "-0":非正整数 "-":负整数 "":整数
	 * @return
	 */
	public static boolean checkNumber(String num, String type) {
		String eL = "";
		if (type.equals("0+"))
			eL = "^//d+$";// 非负整数
		else if (type.equals("+"))
			eL = "^//d*[1-9]//d*$";// 正整数
		else if (type.equals("-0"))
			eL = "^((-//d+)|(0+))$";// 非正整数
		else if (type.equals("-"))
			eL = "^-//d*[1-9]//d*$";// 负整数
		else
			eL = "^-?//d+$";// 整数
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(num);
		boolean b = m.matches();
		return b;
	}

	/**
	 * 检查浮点数
	 * 
	 * @param num
	 * @param type
	 *            "0+":非负浮点数 "+":正浮点数 "-0":非正浮点数 "-":负浮点数 "":浮点数
	 * @return
	 */
	public static boolean checkFloat(String num, String type) {
		if(num==null){
			return false;
		}
		String eL = "";
		if (type.equals("0+"))
			eL = "^//d+(//.//d+)?$";// 非负浮点数
		else if (type.equals("+"))
			eL = "^((//d+//.//d*[1-9]//d*)|(//d*[1-9]//d*//.//d+)|(//d*[1-9]//d*))$";// 正浮点数
		else if (type.equals("-0"))
			eL = "^((-//d+(//.//d+)?)|(0+(//.0+)?))$";// 非正浮点数
		else if (type.equals("-"))
			eL = "^(-((//d+//.//d*[1-9]//d*)|(//d*[1-9]//d*//.//d+)|(//d*[1-9]//d*)))$";// 负浮点数
		else
			eL = "^(-?//d+)(//.//d+)?$";// 浮点数
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(num);
		boolean b = m.matches();
		return b;
	}
}