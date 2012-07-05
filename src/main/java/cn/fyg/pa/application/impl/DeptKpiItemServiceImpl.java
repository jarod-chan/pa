package cn.fyg.pa.application.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.DeptKpiItemService;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItemRepository;

@Service
public class DeptKpiItemServiceImpl implements  DeptKpiItemService{
	
	@Resource
	DeptKpiItemRepository deptKpiItemRepository;
	
	@Override
	@Transactional
	public void saveDeptKpiItems(List<DeptKpiItem> deptKpiItems) {
		for (DeptKpiItem deptKpiItem : deptKpiItems) {
			deptKpiItemRepository.save(deptKpiItem);
		}	
	}
}
