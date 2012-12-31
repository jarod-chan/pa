package cn.fyg.pa.interfaces.module.person.yearchk;

import java.util.ArrayList;
import java.util.List;

public class RecvBean {
	
	private List<Long> ids=new ArrayList<Long>();
	
	private List<Long> val=new ArrayList<Long>();

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public List<Long> getVal() {
		return val;
	}

	public void setVal(List<Long> val) {
		this.val = val;
	}
	
	

}
