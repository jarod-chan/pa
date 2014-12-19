package cn.fyg.pa.application;

import cn.fyg.pa.domain.model.yearchkstate.YearchkState;

public interface YearchkStateService {
	
	YearchkState commitYearchk(Long year,Long personId);
	
	boolean isCommit(Long year,Long personId);

}
