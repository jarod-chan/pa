package cn.fyg.pa.service;

import cn.fyg.pa.model.IdrYearCompany;

public interface IdrYearCompanyService {
	
	IdrYearCompany findByYear(Long year);
	
	IdrYearCompany save(IdrYearCompany idrYear);
	

}
