package cn.fyg.pa.domain.model.atten.opinion;

import java.util.List;



public interface OpinionRepository {

	Opinion save(Opinion opinion);
	
	Opinion find(Long id);
	
	List<Opinion> findByBusinessCodeAndBusinessIdOrderByIdAsc(String businessCode,Long businessId);
	
}
