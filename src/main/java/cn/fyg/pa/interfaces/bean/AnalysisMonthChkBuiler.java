package cn.fyg.pa.interfaces.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.MonthChk;
import cn.fyg.pa.domain.model.enums.MonthChkEnum;
import cn.fyg.pa.domain.person.Person;

public class AnalysisMonthChkBuiler {
	
	List<Person> persons;
	
	List<MonthChk> monthChks;
	
	Map<Long,MonthChkEnum> monthChkMap;

	public AnalysisMonthChkBuiler(List<Person> persons, List<MonthChk> monthChks) {
		super();
		this.persons = persons;
		this.monthChks = monthChks;
	}


	public AnalysisMonthChkBean build(Long year,Long month){
		generateMonthChkMap();
		List<AnalysisDepartmentBean> analysisDepartmentBeans=getAnalysisDepartmentBeans();
		AnalysisMonthChkBean analysisMonthChkBean=new AnalysisMonthChkBean();
		analysisMonthChkBean.setYear(year);
		analysisMonthChkBean.setMonth(month);
		analysisMonthChkBean.setAnalysisDepartmentBeans(analysisDepartmentBeans);
		analysisMonthChkBean.calculateSelf();
		return analysisMonthChkBean;
	}


	private List<AnalysisDepartmentBean> getAnalysisDepartmentBeans() {
		List<AnalysisDepartmentBean> returnList=new ArrayList<AnalysisDepartmentBean>();
		String tempDepartment= this.persons.get(0).getDepartment();
		AnalysisDepartmentBean tempDepartmentBean = newAnalysisDepartmentBeanAndAddList(returnList, tempDepartment);
		for(Person person:this.persons){
			String department=person.getDepartment();
			if(!department.equals(tempDepartment)){
				tempDepartment=department;
				tempDepartmentBean = newAnalysisDepartmentBeanAndAddList(returnList, tempDepartment);
			}
			PersonMonthChkStateBean personMonthChkStateBean = new PersonMonthChkStateBean();
			personMonthChkStateBean.setPersonName(person.getName());
			personMonthChkStateBean.setState(getMonthChkState(person.getId()));
			tempDepartmentBean.getPersonMonthChkStateBeans().add(personMonthChkStateBean);
		}
		return returnList;
	}


	private AnalysisDepartmentBean newAnalysisDepartmentBeanAndAddList(List<AnalysisDepartmentBean> returnList, String tempDepartment) {
		AnalysisDepartmentBean tempDepartmentBean=new AnalysisDepartmentBean();
		tempDepartmentBean.setDepartmentName(tempDepartment);
		returnList.add(tempDepartmentBean);
		return tempDepartmentBean;
	}


	private void generateMonthChkMap() {
		Map<Long,MonthChkEnum> map=new HashMap<Long,MonthChkEnum>();
		for(MonthChk monthChk:this.monthChks){
			map.put(monthChk.getPerson().getId(), monthChk.getState());
		}
		this.monthChkMap=map;
	}


	private MonthChkEnum getMonthChkState(Long id) {
		MonthChkEnum state=this.monthChkMap.get(id);
		if(state==null){
			return MonthChkEnum.NEW;
		}
		return state;
	}
}
