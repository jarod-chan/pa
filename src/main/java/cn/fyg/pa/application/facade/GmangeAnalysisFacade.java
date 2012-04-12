package cn.fyg.pa.application.facade;

import cn.fyg.pa.application.bean.AnalysisIdrMonthPlanBean;
import cn.fyg.pa.application.bean.AnalysisMonthChkBean;

public interface GmangeAnalysisFacade {

	AnalysisIdrMonthPlanBean analyseIdrMonthPlan(Long year, Long month);

	AnalysisMonthChkBean analyseMonthChk(Long year, Long month);

}