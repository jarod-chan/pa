package cn.fyg.pa.interfaces.module.yearpk.managesc.dto;

import java.util.ArrayList;
import java.util.List;

public class CheckPage {
	
	private List<CheckItem> checkItems=new ArrayList<CheckItem>();

	public List<CheckItem> getCheckItems() {
		return checkItems;
	}
	
	public void setCheckItems(List<CheckItem> checkItems) {
		this.checkItems = checkItems;
	}
	
	
	private List<Long> id;
	private List<Boolean> flag;
	private List<Long> itemid;
	private List<Long> val;
	public List<Long> getId() {
		return id;
	}
	public void setId(List<Long> id) {
		this.id = id;
	}
	public List<Boolean> getFlag() {
		return flag;
	}
	public void setFlag(List<Boolean> flag) {
		this.flag = flag;
	}
	public List<Long> getItemid() {
		return itemid;
	}
	public void setItemid(List<Long> itemid) {
		this.itemid = itemid;
	}
	public List<Long> getVal() {
		return val;
	}
	public void setVal(List<Long> val) {
		this.val = val;
	}
	
	
}
