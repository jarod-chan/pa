package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.yearchk.Fychkmange;

public interface YearMangeChkService {
	
	void saveAndRemoveList(List<Fychkmange> saveList,List<Fychkmange> removeList);

}
