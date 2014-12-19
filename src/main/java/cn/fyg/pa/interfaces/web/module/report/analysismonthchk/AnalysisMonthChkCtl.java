package cn.fyg.pa.interfaces.web.module.report.analysismonthchk;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.interfaces.web.module.report.analysismonthchk.dto.AnalysisMonthChkBean;
import cn.fyg.pa.interfaces.web.shared.bean.YearAndPrevMonth;
import cn.fyg.pa.interfaces.web.shared.tool.DateTool;
@Controller
@RequestMapping("/report/analysis/monthchk")
public class AnalysisMonthChkCtl {
	
	@Resource
	AnalysisMonthChkFacade analysisMonthChkFacade;
	@Resource
	PersonRepository personRepository;
	@Resource
	MonthChkRepository monthChkRepository;

	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String analyseMonthChk(YearAndPrevMonth queryBean,Map<String,Object> map){
		AnalysisMonthChkBean analysisMonthChkBean = analysisMonthChkFacade.analyseMonthChk(queryBean.getYear(), queryBean.getMonth());
		map.put("dateTool", new DateTool());
		map.put("stateEnum", MonthChkEnum.values());
		map.put("queryBean", queryBean);
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
