package cn.fyg.pa.page;

import java.util.List;

import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.MonthChkItem;

public class ManageMonthChkPage {
	
	private List<Long> monthChkItems_id;

	private List<Long> monthChkItems_point;
	

	public List<Long> getMonthChkItems_id() {
		return monthChkItems_id;
	}


	public void setMonthChkItems_id(List<Long> monthChkItems_id) {
		this.monthChkItems_id = monthChkItems_id;
	}


	public List<Long> getMonthChkItems_point() {
		return monthChkItems_point;
	}


	public void setMonthChkItems_point(List<Long> monthChkItems_point) {
		this.monthChkItems_point = monthChkItems_point;
	}


	public void updateMonthChk(MonthChk monthChk) {
		List<MonthChkItem> items=monthChk.getMonthChkItems();
		for (int i = 0, len = items.size(); i < len; i++) {
			MonthChkItem monthChkItem = items.get(i);
			if(monthChkItem.getId().equals(monthChkItems_id.get(i))){
				monthChkItem.setPoint(monthChkItems_point.get(i));
			}
		}
	}
	
	public void initMonthChk(MonthChk monthChk){
		List<MonthChkItem> items=monthChk.getMonthChkItems();
		for (int i = 0, len = items.size(); i < len; i++) {
			MonthChkItem monthChkItem = items.get(i);
			monthChkItem.setPoint(null);
		}
	}

}
