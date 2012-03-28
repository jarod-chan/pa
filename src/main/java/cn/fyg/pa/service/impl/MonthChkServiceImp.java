package cn.fyg.pa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.dao.MonthChkDao;
import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.enums.StateEnum;
import cn.fyg.pa.service.MonthChkService;

@Service
public class MonthChkServiceImp implements MonthChkService{
	
	@Resource
	MonthChkDao monthChkDao;

	@Override
	public List<MonthChk> getMonthChkByPeriodAndState(Long year, Long month,String department,StateEnum... states) {
		return monthChkDao.findByPeriodAndState(year,month,department,states);
	}

}
