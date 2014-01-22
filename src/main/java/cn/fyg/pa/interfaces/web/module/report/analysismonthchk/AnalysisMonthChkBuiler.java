package cn.fyg.pa.interfaces.web.module.report.analysismonthchk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.interfaces.web.module.report.analysismonthchk.dto.AnalysisDepartmentBean;
import cn.fyg.pa.interfaces.web.module.report.analysismonthchk.dto.AnalysisMonthChkBean;
import cn.fyg.pa.interfaces.web.module.report.analysismonthchk.dto.PersonMonthChkStateBean;

public class AnalysisMonthChkBuiler {
	
	List<Person> persons;
	
	List<MonthChk> monthChks;
	
	Map<Long,MonthChk> monthChkMap;

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
			personMonthChkStateBean.setMonthchkId(getMonthChkId(person.getId()));
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
		Map<Long,MonthChk> map=new HashMap<Long,MonthChk>();
		for(MonthChk monthChk:this.monthChks){
			map.put(monthChk.getPerson().getId(), monthChk);
		}
		this.monthChkMap=map;
	}

	private Long getMonthChkId(Long id) {
		MonthChk monthChk=this.monthChkMap.get(id);
		if (canReturnMonthChkId(monthChk)) {
			return monthChk.getId();
		}
		return null;
	}


	private boolean canReturnMonthChkId(MonthChk monthChk) {
		if(monthChk==null) return false;
		if(MonthChkEnum.FINISHED==monthChk.getState()){
			return true;
		}
		if(MonthChkEnum.SUBMITTED==monthChk.getState()){
			return true;
		}
		return false;
	}
	
	private MonthChkEnum getMonthChkState(Long id) {
		MonthChk monthChk=this.monthChkMap.get(id);
		if(monthChk==null){
			return MonthChkEnum.NEW;
		}
		return monthChk.getState();
	}
}
