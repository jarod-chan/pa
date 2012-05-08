package cn.fyg.pa.application.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.YearMangeChkService;
import cn.fyg.pa.domain.yearchk.Fychkmange;
import cn.fyg.pa.domain.yearchk.YearMangeChkRepositroy;

@Service
public class YearMangeChkServiceImpl implements YearMangeChkService {
	
	@Resource
	YearMangeChkRepositroy yearMangeChkRepositroy;


	@Override
	@Transactional
	public void saveAndRemoveList(List<Fychkmange> saveList,List<Fychkmange> removeList) {
		yearMangeChkRepositroy.saveList(saveList);
		yearMangeChkRepositroy.removeList(removeList);
		
	}

}
