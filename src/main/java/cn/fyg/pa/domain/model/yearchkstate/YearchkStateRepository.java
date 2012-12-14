package cn.fyg.pa.domain.model.yearchkstate;

public interface YearchkStateRepository {
	
	YearchkState save(YearchkState yearchkState);
	
	YearchkState findByYearAndPersonId(Long year,Long personId);

}
