package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.model.yearchk.Fycheck;

public interface YearCheckService {
	
	void saveFychecks(List<Fycheck> fychecks);
}
