package cn.fyg.pa.application.page;

import java.util.List;

public class DataPage {
	private List<Long> id;
	private List<Long> colId;
	private List<Long> rowId;
	private List<Long> val;
	public List<Long> getColId() {
		return colId;
	}
	public void setColId(List<Long> colId) {
		this.colId = colId;
	}
	public List<Long> getRowId() {
		return rowId;
	}
	public void setRowId(List<Long> rowId) {
		this.rowId = rowId;
	}
	public List<Long> getVal() {
		return val;
	}
	public void setVal(List<Long> val) {
		this.val = val;
	}
	public List<Long> getId() {
		return id;
	}
	public void setId(List<Long> id) {
		this.id = id;
	}
}
