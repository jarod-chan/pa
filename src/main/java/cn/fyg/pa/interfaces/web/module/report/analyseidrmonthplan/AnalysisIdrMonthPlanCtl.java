package cn.fyg.pa.interfaces.web.module.report.analyseidrmonthplan;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.interfaces.web.module.report.analyseidrmonthplan.dto.AnalysisIdrMonthPlanBean;
import cn.fyg.pa.interfaces.web.shared.bean.YearAndPrevMonth;
import cn.fyg.pa.interfaces.web.shared.tool.DateTool;

@Controller
@RequestMapping("report/analysis/idrmonthplan")
@SessionAttributes("report_analysis_idrmonthplan")
public class AnalysisIdrMonthPlanCtl{
	
	@Resource
	AnalysisIdrMonthPlanFacade gmangeAnalysisFacade;
	@Resource
	PersonRepository personRepository;
	@Resource
	IdrMonthPlanBillRepository idrMonthPlanBillRepository;
	
	@ModelAttribute("report_analysis_idrmonthplan")
	public YearAndPrevMonth report_analysis_idrmonthplan(){
		return new YearAndPrevMonth();
	}
	
	@RequestMapping(value="",method={RequestMethod.GET,RequestMethod.POST})
	public String analysisIdrMonthPlan(@ModelAttribute("report_analysis_idrmonthplan")YearAndPrevMonth report_analysis_idrmonthplan,Map<String,Object> map){
		AnalysisIdrMonthPlanBean analysisIdrMonthPlanBean=gmangeAnalysisFacade.analysisIdrMonthPlan(report_analysis_idrmonthplan.getYear(), report_analysis_idrmonthplan.getMonth());
		map.put("dateTool", new DateTool());
		map.put("stateEnum",IdrMonthPlanEnum.values());
		map.put("analysisIdrMonthPlanBean", analysisIdrMonthPlanBean);
		return "gmangeanalysis/idrmonthplan";
	}
	
	@RequestMapping(value="/{monthplanId}",method=RequestMethod.GET)
	public String viewMonthPlan(@PathVariable("monthplanId") Long monthplanId,Map<String,Object> map){
		IdrMonthPlanBill idrMonthPlanBill = idrMonthPlanBillRepository.find(monthplanId);
		map.put("bill", idrMonthPlanBill);
		return "gmangeanalysis/idrmonthplaninfo";
	}
}
