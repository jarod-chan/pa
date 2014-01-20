package cn.fyg.pa.interfaces.module.finance.summarysnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.summarysnapshot.SnapshotItem;

public class SnapshotItemBuilder {
	
	private List<Person> persons;
	private List<Person> managers;
	private List<Department> departments;
	private List<MonthChk> monthChks;
	
	
	
	public SnapshotItemBuilder(List<Person> persons,List<Person> managers,
			List<Department> departments, List<MonthChk> monthChks) {
		super();
		this.persons = persons;
		this.managers = managers;
		this.departments = departments;
		this.monthChks = monthChks;
	}



	public List<SnapshotItem> bulid(){
		Map<String,Person> managerMap=getManagerMap();
		Map<String,Department> departmentMap=getDepartmentMap();
		Map<Long,MonthChkEnum> monthEnumMap=getMonthEnumMap();
		List<SnapshotItem> itemList=createSnapshotItem(managerMap,departmentMap,monthEnumMap);
		itemList=sortSnapshotItem(itemList);
		return itemList;
	}



	private List<SnapshotItem> sortSnapshotItem(List<SnapshotItem> itemList) {
		Collections.sort(itemList, new Comparator<SnapshotItem>() {
			@Override
			public int compare(SnapshotItem o1, SnapshotItem o2) {
				int compareValue = o1.getStatus().compareTo(o2.getStatus());
				if (compareValue != 0)
					return -compareValue;
				compareValue = o1.getDepartment().getId()
						.compareTo(o2.getDepartment().getId());
				if (compareValue != 0)
					return compareValue;
				compareValue = o1.getPerson().getId()
						.compareTo(o2.getPerson().getId());
				if (compareValue != 0)
					return compareValue;
				return 0;
			}
		});
		return itemList;
	}



	private List<SnapshotItem> createSnapshotItem(
			Map<String, Person> managerMap,
			Map<String, Department> departmentMap,
			Map<Long, MonthChkEnum> monthEnumMap) {
		List<SnapshotItem> retList=new ArrayList<SnapshotItem>();
		for(Person person:this.persons){
			String departmentName=person.getDepartment();
			SnapshotItem snapshotItem=new SnapshotItem();
			snapshotItem.setPerson(person);
			snapshotItem.setManager(managerMap.get(departmentName));
			snapshotItem.setDepartment(departmentMap.get(departmentName));
			snapshotItem.setStatus(getEnumMapByDefalut(person.getId(),monthEnumMap));
			retList.add(snapshotItem);
		}
		return retList;
	}



	private MonthChkEnum getEnumMapByDefalut(Long id,Map<Long, MonthChkEnum> monthEnumMap) {
		MonthChkEnum monthChkEnum=monthEnumMap.get(id);
		if(monthChkEnum==null){
			return MonthChkEnum.NEW;
		}
		return monthChkEnum;
	}



	private Map<Long, MonthChkEnum> getMonthEnumMap() {
		HashMap<Long, MonthChkEnum> returnMap = new HashMap<Long,MonthChkEnum>();
		for (MonthChk monthChk:this.monthChks) {
			returnMap.put(monthChk.getPerson().getId(), monthChk.getState());
		}
		return returnMap;
	}



	private Map<String, Department> getDepartmentMap() {
		Map<String,Department> returnMap=new HashMap<String,Department>();
		for(Department department:this.departments){
			 returnMap.put(department.getName(),department);
		}
		return returnMap;
	}



	private Map<String, Person> getManagerMap() {
		Map<String, Person> returnMap=new HashMap<String, Person>();
		for(Person person:this.managers){
			//XXX 此处逻辑待重构
			if(person.getName().equals("产品部")||person.getName().equals("办公室")){
				continue;
			}
			returnMap.put(person.getDepartment(), person);
		}
		return returnMap;
	}

}
