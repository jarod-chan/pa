package cn.fyg.pa.interfaces.module.manage.yearchk.dto;


public class CheckItem {
	
	private Boolean flag; //是否选中
	private Long id;// id
	private Long itemid;//评分项目id
	private Long val;//评分结果
	
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemid() {
		return itemid;
	}
	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}
	public Long getVal() {
		return val;
	}
	public void setVal(Long val) {
		this.val = val;
	}
	
	

}
