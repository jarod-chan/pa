package cn.fyg.pa.interfaces.module.gmange.report.analyseidrmonthplan;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanBillRepository;
import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.interfaces.module.gmange.report.analyseidrmonthplan.dto.AnalysisIdrMonthPlanBean;
import cn.fyg.pa.interfaces.module.shared.bean.YearAndPrevMonth;
import cn.fyg.pa.interfaces.module.shared.tool.DateTool;

@Controller
@RequestMapping("/gmange/{personId}/analysis/idrmonthplan")
public class AnalysisIdrMonthPlanCtl{
	
	@Resource
	AnalysisIdrMonthPlanFacade gmangeAnalysisFacade;
	@Resource
	PersonRepository personRepository;
	@Resource
	IdrMonthPlanBillRepository idrMonthPlanBillRepository;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String analysisIdrMonthPlan(YearAndPrevMonth queryBean,Map<String,Object> map){
		AnalysisIdrMonthPlanBean analysisIdrMonthPlanBean=gmangeAnalysisFacade.analysisIdrMonthPlan(queryBean.getYear(), queryBean.getMonth());
		map.put("dateTool", new DateTool());
		map.put("stateEnum",IdrMonthPlanEnum.values());
		map.put("queryBean", queryBean);
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
