package cn.fyg.pa.interfaces.web.module.admin.yearchkrpt;

import java.util.List;

import cn.fyg.pa.infrastructure.persistence.jpa.RptJpa;



public interface PointHelp<T extends PointItem> {
	
	void initOriginalData(RptJpa rptJpa,Long year);
	
	void calculate();
		
	void orderByPoint(String order) throws Exception;

	List<T> getResult() throws Exception;
}
