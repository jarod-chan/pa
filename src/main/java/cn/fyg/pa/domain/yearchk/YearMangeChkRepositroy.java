package cn.fyg.pa.domain.yearchk;

import java.util.List;

public interface YearMangeChkRepositroy {
	
	List<Object[]> getPseronPointByDepartment(Long year,String department);

}
