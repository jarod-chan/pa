package cn.fyg.pa.interfaces.web.module.report.analysismonthchk;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.interfaces.web.module.report.analysismonthchk.dto.AnalysisMonthChkBean;
import cn.fyg.pa.interfaces.web.shared.bean.YearAndPrevMonth;
import cn.fyg.pa.interfaces.web.shared.tool.DateTool;
@Controller
@RequestMapping("report/analysis/monthchk")
@SessionAttributes("report_analysis_monthchk")
public class AnalysisMonthChkCtl {
	
	@Resource
	AnalysisMonthChkFacade analysisMonthChkFacade;
	@Resource
	PersonRepository personRepository;
	@Resource
	MonthChkRepository monthChkRepository;

	@ModelAttribute("report_analysis_monthchk")
	public YearAndPrevMonth report_analysis_monthchk(){
		return new YearAndPrevMonth();
	}
	
	@RequestMapping(value="",method={RequestMethod.GET,RequestMethod.POST})
	public String analyseMonthChk(@ModelAttribute("report_analysis_monthchk")YearAndPrevMonth report_analysis_monthchk,Map<String,Object> map){
		AnalysisMonthChkBean analysisMonthChkBean = analysisMonthChkFacade.analyseMonthChk(report_analysis_monthchk.getYear(), report_analysis_monthchk.getMonth());
		map.put("dateTool", new DateTool());
		map.put("stateEnum", MonthChkEnum.values());
		map.put("analysisMonthChkBean", analysisMonthChkBean);
		return "gmangeanalysis/monthchk";
	}
	
	@RequestMapping(value="/{montchkId}",method=RequestMethod.GET)
	public String viewMonthChk(@PathVariable("montchkId")Long montchkId,Map<String,Object> map){
		MonthChk monthChk = monthChkRepository.find(montchkId);
		map.put("monthChk", monthChk);
		return "gmangeanalysis/monthchkinfo";
	}

}
