package cn.fyg.pa.interfaces.module.admin.yearchkrpt;

import java.util.List;



public interface PointHelp<T extends PointItem> {
	
	void initOriginalData(List<Object[]> personInfo_deptScore,List<Object[]> personScore);
	
	void calculate();
		
	void orderByPoint(String order) throws Exception;

	List<T> getResult() throws Exception;
}
