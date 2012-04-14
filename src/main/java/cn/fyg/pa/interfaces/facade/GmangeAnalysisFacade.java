package cn.fyg.pa.interfaces.facade;

import cn.fyg.pa.interfaces.bean.AnalysisIdrMonthPlanBean;
import cn.fyg.pa.interfaces.bean.AnalysisMonthChkBean;

public interface GmangeAnalysisFacade {

	AnalysisIdrMonthPlanBean analyseIdrMonthPlan(Long year, Long month);

	AnalysisMonthChkBean analyseMonthChk(Long year, Long month);

}