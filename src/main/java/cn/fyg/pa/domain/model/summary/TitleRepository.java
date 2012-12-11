package cn.fyg.pa.domain.model.summary;

import java.util.List;

public interface TitleRepository {
	
	List<Title> findByYearOrderByNoASC(Long year);

}
