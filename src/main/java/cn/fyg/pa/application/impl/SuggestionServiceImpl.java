package cn.fyg.pa.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.SuggestionService;
import cn.fyg.pa.domain.model.suggestion.Suggestion;
import cn.fyg.pa.domain.model.suggestion.SuggestionFactory;
import cn.fyg.pa.domain.model.suggestion.SuggestionRepository;

@Service
public class SuggestionServiceImpl implements SuggestionService {

	@Autowired
	SuggestionRepository suggestionRepository;
	
	@Override
	public Suggestion create(Long personId, String content) {
		return SuggestionFactory.create(personId, content);
	}

	@Override
	@Transactional
	public Suggestion save(Suggestion suggestion) {
		return this.suggestionRepository.save(suggestion);
	}

	@Override
	public List<Suggestion> findByPersonId(Long personId) {
		return this.suggestionRepository.findByPersonId(personId);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.suggestionRepository.delete(id);
	}

}
