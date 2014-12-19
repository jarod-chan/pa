package cn.fyg.pa.interfaces.web.shared.tool;

import java.math.BigDecimal;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * @author jhon.chen@gmail.com
 * 把javabean转成json对象
 *
 */
public class JsonUtil {
	
	//排除可能引起循环的属性
	public static String toArrayStr(Object obj,String[] excludes) {
		JsonConfig config=new JsonConfig();
		config.setExcludes(excludes);
		config.registerJsonValueProcessor(BigDecimal.class,new BigDecimalToString());
		JSONArray jsonArray=JSONArray.fromObject(obj,config);
		return jsonArray.toString();
	}
	
	
	
	public static String toArrayStr(Object obj) {
		return toArrayStr(obj,new String[]{});
	}
	
	public static String toObjectStr(Object obj,String[] excludes){
		JsonConfig config=new JsonConfig();
		config.setExcludes(excludes);
		config.registerJsonValueProcessor(BigDecimal.class,new BigDecimalToString());
		JSONObject jsonObject = JSONObject.fromObject(obj,config);
		return jsonObject.toString();
	}
	
	public static String toObjectStr(Object obj){
		return toObjectStr(obj,new String[]{});
	}
	
	
	/**
	 * @author jhon.chen@gmail.com
	 * josn bigdecimal 当成数字处理造成小数位丢失
	 */
	private static class BigDecimalToString implements JsonValueProcessor {

		@Override
		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			return process(value);
		}

		@Override
		public Object processObjectValue(String key, Object value,  
	            JsonConfig jsonConfig) {
			return process(value);
		}
		
		 private Object process(Object value) {  
	        if (value == null) {  
	            value ="";
	        }  
	        return value.toString();  
	    }  
		
	}

}
