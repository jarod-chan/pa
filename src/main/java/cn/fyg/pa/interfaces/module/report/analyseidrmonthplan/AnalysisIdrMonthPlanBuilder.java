package cn.fyg.pa.interfaces.module.report.analyseidrmonthplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.interfaces.module.report.analyseidrmonthplan.dto.AnalysisIdrMonthPlanBean;
import cn.fyg.pa.interfaces.module.report.analyseidrmonthplan.dto.DepartmentIdrMonthPlanBillStateBean;

public class AnalysisIdrMonthPlanBuilder {
	
	List<Department> departments;
	
	List<IdrMonthPlanBill> idrMonthPlanBills;

	private Map<Long, IdrMonthPlanBill> idrMonthPlanBillStates;

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
		Map<Long,IdrMonthPlanBill> map=new HashMap<Long,IdrMonthPlanBill>();
		for(IdrMonthPlanBill idrMonthPlanBill:this.idrMonthPlanBills){
			map.put(idrMonthPlanBill.getDepartment().getId(), idrMonthPlanBill);
		}
		this.idrMonthPlanBillStates=map;
	}

	private List<DepartmentIdrMonthPlanBillStateBean> getDepartmentIdrMonthPlanBillStateBeans() {
		List<DepartmentIdrMonthPlanBillStateBean> returnList=new ArrayList<DepartmentIdrMonthPlanBillStateBean>();
		for(Department department:this.departments){
			DepartmentIdrMonthPlanBillStateBean departmentIdrMonthPlanBillStateBean = new DepartmentIdrMonthPlanBillStateBean();
			departmentIdrMonthPlanBillStateBean.setDepartmentName(department.getName());
			departmentIdrMonthPlanBillStateBean.setState(getDepartmentIdrMonthPlanBillState(department.getId()));
			departmentIdrMonthPlanBillStateBean.setMonthPlanBillId(getDepartmentIdrMonthPlanId(department.getId()));
			returnList.add(departmentIdrMonthPlanBillStateBean);
		}
		return returnList;
	}

	private Long getDepartmentIdrMonthPlanId(Long id) {
		IdrMonthPlanBill idrMonthPlanBill=this.idrMonthPlanBillStates.get(id);
		if(isCanReturnIdrmonthPlanBillId(idrMonthPlanBill)){
			return idrMonthPlanBill.getId();
		}
		return null;
	}

	private boolean isCanReturnIdrmonthPlanBillId(
			IdrMonthPlanBill idrMonthPlanBill) {
		if(idrMonthPlanBill==null) return false;
		if(IdrMonthPlanEnum.SUBMITTED==idrMonthPlanBill.getState()){
			return true;
		}
		if(IdrMonthPlanEnum.EXECUTE==idrMonthPlanBill.getState()){
			return true;
		}
		if(IdrMonthPlanEnum.FINISHED==idrMonthPlanBill.getState()){
			return true;
		}
		return false;
	}

	private IdrMonthPlanEnum getDepartmentIdrMonthPlanBillState(Long id) {
		IdrMonthPlanBill idrMonthPlanBill=this.idrMonthPlanBillStates.get(id);
		if(idrMonthPlanBill==null){
			return IdrMonthPlanEnum.NEW;
		}
		return idrMonthPlanBill.getState();
	}

}
