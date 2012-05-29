package cn.fyg.pa.interfaces.module.gmange.report;


public interface GmangeAnalysisFacade {

	AnalysisIdrMonthPlanBean analyseIdrMonthPlan(Long year, Long month);

	AnalysisMonthChkBean analyseMonthChk(Long year, Long month);

}