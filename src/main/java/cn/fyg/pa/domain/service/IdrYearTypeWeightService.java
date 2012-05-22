package cn.fyg.pa.domain.service;

import cn.fyg.pa.domain.yeartypeweight.IdrYearTypeWeight;

public interface IdrYearTypeWeightService {
	
	IdrYearTypeWeight findByYear(Long year);
	
	IdrYearTypeWeight save(IdrYearTypeWeight idrYearTypeWeight);

}
