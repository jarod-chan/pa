package cn.fyg.pa.interfaces.module.finance.summarysnapshot.dto;

import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.person.Person;

public class PageItemBean {
	
	List<StatusBean> statusBeans;
	
	Map<String,List<Person>> map;

	public List<StatusBean> getStatusBeans() {
		return statusBeans;
	}

	public void setStatusBeans(List<StatusBean> statusBeans) {
		this.statusBeans = statusBeans;
	}

	public Map<String, List<Person>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<Person>> map) {
		this.map = map;
	}
}
