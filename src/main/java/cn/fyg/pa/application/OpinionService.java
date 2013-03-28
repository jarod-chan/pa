package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.model.atten.opinion.Opinion;

public interface OpinionService {
	
	Opinion append(Opinion opinion);
	
	List<Opinion> ListOpinions(String businessCode,Long businessId);
	
}
