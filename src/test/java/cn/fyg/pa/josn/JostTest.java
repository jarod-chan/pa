package cn.fyg.pa.josn;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.junit.Test;

import cn.fyg.pa.domain.model.companykpiitem.IdrCompany;
import cn.fyg.pa.domain.model.yearchk.Fychkitem;
import cn.fyg.pa.domain.model.yeartypeweight.IdrTypeWeight;
import cn.fyg.pa.interfaces.module.shared.tool.JsonUtil;

public class JostTest {

	@Test
	public void test(){
		
		Fychkitem chkitem=new Fychkitem();
		Long id=1L;
		chkitem.setId(id);
		chkitem.setContent("test");
		System.out.println("==============Java Bean >>> JSON Object==================");
		JSONArray ja = JSONArray.fromObject(chkitem);
		JSONObject jo = JSONObject.fromObject(chkitem);
		JSON jsja = JSONSerializer.toJSON(chkitem);
		
		System.out.println("JSONArray: \r\n"+ja+"\r\n"+"JSONObject: \r\n"+jo+"\r\n"+"JSONSerializer: \r\n"+jsja);
		
		IdrCompany idc=new IdrCompany();
		idc.setId(123456L);
		
		IdrTypeWeight idrTypeWeight=new IdrTypeWeight();
		idrTypeWeight.setId(200L);
		idc.setIdrTypeWeight(idrTypeWeight);
		
		ja=JSONArray.fromObject(idc);
		jo=JSONObject.fromObject(idc);
		jsja=JSONSerializer.toJSON(idc);
		
		
		System.out.println("JSONArray: \r\n"+ja+"\r\n"+"JSONObject: \r\n"+jo+"\r\n"+"JSONSerializer: \r\n"+jsja);
	}
	
	@Test
	public void toJsonTest(){
		IdrCompany idc=new IdrCompany();
		idc.setId(123456L);
		
		IdrTypeWeight idrTypeWeight=new IdrTypeWeight();
		idrTypeWeight.setId(200L);
		idc.setIdrTypeWeight(idrTypeWeight);
		
		System.out.println("JSONArray: \r\n"+JsonUtil.toObjectStr(idc)+"\r\n"+"JSONObject: \r\n"+JsonUtil.toArrayStr(idc));
	}
	
	@Test
	public void testMap(){
		Map<String,Object> map=new HashMap<String,Object> ();
		map.put("bigdeimal", new BigDecimal("3.20"));
		System.out.println(JsonUtil.toObjectStr(map));
	}

}
