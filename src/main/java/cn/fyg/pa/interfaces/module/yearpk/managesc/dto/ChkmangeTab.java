package cn.fyg.pa.interfaces.module.yearpk.managesc.dto;

import cn.fyg.pa.domain.model.yearchk.Fychkitem;
import cn.fyg.pa.domain.model.yearchk.Fychkmange;

public class ChkmangeTab {
	
	private Fychkitem chkitem;
	private Fychkmange chkmange;	
	/**
	 * Y
	 * N
	 */
	private String special;
	private String type;
	private Long typesum;
	private int rownum;
	private boolean ischeck;
	
	public boolean isIscheck() {
		return ischeck;
	}
	public void setIscheck(boolean ischeck) {
		this.ischeck = ischeck;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public Long getTypesum() {
		return typesum;
	}
	public void setTypesum(Long typesum) {
		this.typesum = typesum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Fychkitem getChkitem() {
		return chkitem;
	}
	public void setChkitem(Fychkitem chkitem) {
		this.chkitem = chkitem;
	}
	public Fychkmange getChkmange() {
		return chkmange;
	}
	public void setChkmange(Fychkmange chkmange) {
		this.chkmange = chkmange;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
}
