package cn.fyg.pa.interfaces.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.model.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.enums.IdrMonthPlanEnum;

public class AnalysisIdrMonthPlanBuilder {
	
	List<Department> departments;
	
	List<IdrMonthPlanBill> idrMonthPlanBills;

	private Map<Long, IdrMonthPlanEnum> idrMonthPlanBillStates;

	public AnalysisIdrMonthPlanBuilder(List<Department> departments,
			List<IdrMonthPlanBill> idrMonthPlanBills) {
		super();
		this.departments = departments;
		this.idrMonthPlanBills = idrMonthPlanBills;
	}
	
	public AnalysisIdrMonthPlanBean build(Long year, Long month) {
		generateIdrMonthPlanBillMap();
		List<DepartmentIdrMonthPlanBillStateBean> departmentIdrMonthPlanBillStateBeans=getDepartmentIdrMonthPlanBillStateBeans();
		AnalysisIdrMonthPlanBean analysisIdrMonthPlanBean = new AnalysisIdrMonthPlanBean();
		analysisIdrMonthPlanBean.setYear(year);
		analysisIdrMonthPlanBean.setMonth(month);
		analysisIdrMonthPlanBean.setDepartmentIdrMonthPlanBillStateBeans(departmentIdrMonthPlanBillStateBeans);
		analysisIdrMonthPlanBean.calculateSelf();
		return analysisIdrMonthPlanBean;
	}

	private void generateIdrMonthPlanBillMap() {
		Map<Long,IdrMonthPlanEnum> map=new HashMap<Long,IdrMonthPlanEnum>();
		for(IdrMonthPlanBill idrMonthPlanBill:this.idrMonthPlanBills){
			map.put(idrMonthPlanBill.getDepartment().getId(), idrMonthPlanBill.getState());
		}
		this.idrMonthPlanBillStates=map;
	}

	private List<DepartmentIdrMonthPlanBillStateBean> getDepartmentIdrMonthPlanBillStateBeans() {
		List<DepartmentIdrMonthPlanBillStateBean> returnList=new ArrayList<DepartmentIdrMonthPlanBillStateBean>();
		for(Department department:this.departments){
			DepartmentIdrMonthPlanBillStateBean departmentIdrMonthPlanBillStateBean = new DepartmentIdrMonthPlanBillStateBean();
			departmentIdrMonthPlanBillStateBean.setDepartmentName(department.getName());
			departmentIdrMonthPlanBillStateBean.setState(getDepartmentIdrMonthPlanBillState(department.getId()));
			returnList.add(departmentIdrMonthPlanBillStateBean);
		}
		return returnList;
	}

	private IdrMonthPlanEnum getDepartmentIdrMonthPlanBillState(Long id) {
		IdrMonthPlanEnum state=this.idrMonthPlanBillStates.get(id);
		if(state==null){
			return IdrMonthPlanEnum.NEW;
		}
		return state;
	}

}
