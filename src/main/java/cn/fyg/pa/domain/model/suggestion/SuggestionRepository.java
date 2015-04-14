package cn.fyg.pa.domain.model.suggestion;

import java.util.List;

public interface SuggestionRepository {
	
	Suggestion save(Suggestion suggestion);

	List<Suggestion> findByPersonId(Long personId);

	void delete(Long id);

}
