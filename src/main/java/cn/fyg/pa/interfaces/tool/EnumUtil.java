package cn.fyg.pa.interfaces.tool;

import java.util.HashMap;
import java.util.Map;

import cn.fyg.pa.domain.shared.CommonEnum;

public class EnumUtil {

	public static Map<String, String> enumToMap(CommonEnum[] enums) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0, len = enums.length; i < len; i++) {
			map.put(enums[i].toString(), enums[i].getName());	
		}
		return map;
	}

}
