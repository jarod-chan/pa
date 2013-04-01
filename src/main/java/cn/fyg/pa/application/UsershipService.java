package cn.fyg.pa.application;

import cn.fyg.pa.domain.model.usership.Usership;


public interface UsershipService {
	
	Usership save(Usership usership);
	
	String parent(String tree,String userKey);
	
	String userTag(String tree,String userKey);

}
