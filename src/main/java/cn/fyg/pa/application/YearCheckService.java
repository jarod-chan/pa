package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.yearchk.Fycheck;

public interface YearCheckService {
	
	void saveFychecks(List<Fycheck> fychecks);
}
