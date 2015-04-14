package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.model.suggestion.Suggestion;

public interface SuggestionService {
	
	Suggestion create(Long personId,String content);

	Suggestion save(Suggestion suggestion);
	
	List<Suggestion> findByPersonId(Long personId);
	
	void delete(Long id);
}
