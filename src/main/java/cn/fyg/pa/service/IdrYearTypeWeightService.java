package cn.fyg.pa.service;

import cn.fyg.pa.model.IdrYearTypeWeight;

public interface IdrYearTypeWeightService {
	
	IdrYearTypeWeight findByYear(Long year);
	
	IdrYearTypeWeight save(IdrYearTypeWeight idrYearTypeWeight);

}
