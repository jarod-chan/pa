package cn.fyg.pa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fycheck implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long colId;
	private Long rowId;
	private Long val;
	private Long chkId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getColId() {
		return colId;
	}
	public void setColId(Long colId) {
		this.colId = colId;
	}
	public Long getRowId() {
		return rowId;
	}
	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}
	public Long getVal() {
		return val;
	}
	public void setVal(Long val) {
		this.val = val;
	}
	public Long getChkId() {
		return chkId;
	}
	public void setChkId(Long chkId) {
		this.chkId = chkId;
	}

	

	
	

	
}
