package cn.fyg.pa.interfaces.module.gmange.report.analysismonthchk;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.interfaces.module.gmange.report.analysismonthchk.dto.AnalysisMonthChkBean;
import cn.fyg.pa.interfaces.module.shared.bean.YearAndPrevMonth;
import cn.fyg.pa.interfaces.module.shared.tool.DateTool;
@Controller
@RequestMapping("/gmange/{personId}/analysis/monthchk")
public class AnalysisMonthChkCtl {
	
	@Resource
	AnalysisMonthChkFacade analysisMonthChkFacade;
	@Resource
	PersonRepository personRepository;
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String analyseMonthChk(YearAndPrevMonth queryBean,Map<String,Object> map){
		AnalysisMonthChkBean analysisMonthChkBean = analysisMonthChkFacade.analyseMonthChk(queryBean.getYear(), queryBean.getMonth());
		map.put("dateTool", new DateTool());
		map.put("stateEnum", MonthChkEnum.values());
		map.put("queryBean", queryBean);
		map.put("analysisMonthChkBean", analysisMonthChkBean);
		return "gmangeanalysis/monthchk";
	}
	


}
