package cn.fyg.pa.service;

import cn.fyg.pa.model.IdrYear;

public interface IdrYearService {
	
	IdrYear findByYear(Long year);
	
	IdrYear save(IdrYear idrYear);
	

}
