package cn.fyg.pa.application.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.YearCheckService;
import cn.fyg.pa.domain.yearchk.Fycheck;
import cn.fyg.pa.domain.yearchk.YearChkRepositroy;

@Service
public class YearCheckServiceImpl implements YearCheckService {

	@Resource
	YearChkRepositroy yearChkRepositroy;
	
	@Override
	@Transactional
	public void saveFychecks(List<Fycheck> fychecks) {
		yearChkRepositroy.saveFychecks(fychecks);
	}

}
