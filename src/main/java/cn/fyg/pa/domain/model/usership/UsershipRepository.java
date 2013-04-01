package cn.fyg.pa.domain.model.usership;

public interface UsershipRepository {
	
	Usership find(String tree,String userKey);

	Usership save(Usership usership);

}
