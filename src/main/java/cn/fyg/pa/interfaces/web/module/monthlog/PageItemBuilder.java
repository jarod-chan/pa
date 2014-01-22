package cn.fyg.pa.interfaces.web.module.monthlog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.summarysnapshot.SnapshotItem;
import cn.fyg.pa.interfaces.web.module.monthlog.dto.DepartDescBean;
import cn.fyg.pa.interfaces.web.module.monthlog.dto.PageItemBean;
import cn.fyg.pa.interfaces.web.module.monthlog.dto.StatusBean;

public class PageItemBuilder {
	
	private List<SnapshotItem> snapshotItems;

	public PageItemBuilder(List<SnapshotItem> snapshotItems) {
		super();
		this.snapshotItems = snapshotItems;
	}
	
	public PageItemBean build(){
		PageItemBean pageItemBean=new PageItemBean();
		List<StatusBean> statusBeans=createStatusBeans();
		statusBeans=inflateStatusBeans(statusBeans);
		Map<String,List<Person>> personMap=createPersonMap();
		pageItemBean.setStatusBeans(statusBeans);
		pageItemBean.setMap(personMap);
		return pageItemBean;
	}

	private List<StatusBean> inflateStatusBeans(List<StatusBean> statusBeans) {
		MonthChkEnum[] status = MonthChkEnum.values();
		Arrays.sort(status, new Comparator<MonthChkEnum>(){
			@Override
			public int compare(MonthChkEnum o1, MonthChkEnum o2) {
				return -o1.compareTo(o2);
			}
		});
		for(int i=0,len=status.length;i<len;i++){
			if(!statusBeans.get(i).getStatus().equals(status[i])){
				StatusBean statusBean=new StatusBean();
				statusBean.setStatus(status[i]);
				statusBeans.add(i,statusBean );
			}
		}
		return statusBeans;
	}

	private List<StatusBean> createStatusBeans() {
		List<StatusBean> statusBeanList=new ArrayList<StatusBean>();
		StatusBean statusBean=new StatusBean();
		for (SnapshotItem snapshotItem : this.snapshotItems) {
			if(!snapshotItem.getStatus().equals(statusBean.getStatus())){
				statusBean=new StatusBean(); 
				statusBeanList.add(statusBean);
			}
			statusBean.setStatus(snapshotItem.getStatus());
			DepartDescBean departDescBean=new DepartDescBean();
			departDescBean.setManager(snapshotItem.getManager());
			departDescBean.setDepartemnt(snapshotItem.getDepartment());
			departDescBean.setFindPersonListKey(createFindPersonListKey(snapshotItem));
			statusBean.addDepartDescBean(departDescBean);
		}
		return statusBeanList;
	}

	private String createFindPersonListKey(SnapshotItem snapshotItem) {
		return ""+snapshotItem.getStatus()+snapshotItem.getDepartment().getId();
	}

	private Map<String, List<Person>> createPersonMap() {
		Map<String,List<Person>> personMap=new HashMap<String,List<Person>>();
		for (SnapshotItem snapshotItem : this.snapshotItems) {
			String personListKey=createFindPersonListKey(snapshotItem);
			List<Person> personList=personMap.get(personListKey);
			if(personList==null){
				personList=new ArrayList<Person>();
				personMap.put(personListKey,personList);
			}
			personList.add(snapshotItem.getPerson());
		}
		return personMap;
	}

}
