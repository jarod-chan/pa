package cn.fyg.pa.domain.service;

import cn.fyg.pa.domain.companykpi.IdrYearCompany;

public interface IdrYearCompanyService {
	
	IdrYearCompany findByYear(Long year);
	
	IdrYearCompany save(IdrYearCompany idrYear);

	IdrYearCompany sortIdrCompanyByIdrTypeWeight(IdrYearCompany idrYearCompany);
	
}
