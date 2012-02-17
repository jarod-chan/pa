package cn.fyg.pa.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.MonthChkItem;

public class MonthChkPage {

	private Long year;

	private Long month;

	private List<String> monthChkItems_id;

	private List<String> monthChkItems_sn;

	private List<String> monthChkItems_task;
	
	

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public List<String> getMonthChkItems_id() {
		return monthChkItems_id;
	}

	public void setMonthChkItems_id(List<String> monthChkItems_id) {
		this.monthChkItems_id = monthChkItems_id;
	}

	public List<String> getMonthChkItems_sn() {
		return monthChkItems_sn;
	}

	public void setMonthChkItems_sn(List<String> monthChkItems_sn) {
		this.monthChkItems_sn = monthChkItems_sn;
	}

	public List<String> getMonthChkItems_task() {
		return monthChkItems_task;
	}

	public void setMonthChkItems_task(List<String> monthChkItems_task) {
		this.monthChkItems_task = monthChkItems_task;
	}

	public void updateMonthChk(MonthChk monthChk) {
		monthChk.setYear(year);
		monthChk.setMonth(month);
		
		Map<Long,MonthChkItem> map=new HashMap<Long,MonthChkItem>();
		for(MonthChkItem item:monthChk.getMonthChkItems()){
			map.put(item.getId(), item);
		}
		
		List<MonthChkItem> newItems=new ArrayList<MonthChkItem>();
		
		for(int i=0,len=monthChkItems_id.size();i<len;i++){
			String itemid=monthChkItems_id.get(i);
			Long sn=new Long(monthChkItems_sn.get(i));
			String task=monthChkItems_task.get(i);
			
			MonthChkItem item = StringUtils.isBlank(itemid) ? new MonthChkItem()
					: map.get(new Long(itemid));
			item.setMonthChk(monthChk);
			item.setSn(sn);
			item.setTask(task);
			newItems.add(item);
		}
		
		monthChk.setMonthChkItems(newItems);
	}

}
