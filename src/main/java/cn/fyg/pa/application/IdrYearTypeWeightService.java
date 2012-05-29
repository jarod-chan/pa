package cn.fyg.pa.application;

import cn.fyg.pa.domain.model.yeartypeweight.IdrYearTypeWeight;

public interface IdrYearTypeWeightService {
	
	IdrYearTypeWeight getByYear(Long year);
	
	IdrYearTypeWeight save(IdrYearTypeWeight idrYearTypeWeight);

}
