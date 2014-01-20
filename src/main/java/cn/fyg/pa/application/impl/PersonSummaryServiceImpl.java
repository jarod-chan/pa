package cn.fyg.pa.application.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.PersonSummaryService;
import cn.fyg.pa.domain.model.summary.Content;
import cn.fyg.pa.domain.model.summary.ContentRepository;
import cn.fyg.pa.domain.model.summary.PersonSummary;
import cn.fyg.pa.domain.model.summary.PersonSummaryFactory;
import cn.fyg.pa.domain.model.summary.PersonSummaryRepository;
import cn.fyg.pa.domain.model.summary.Title;
import cn.fyg.pa.domain.model.summary.TitleRepository;

@Service
public class PersonSummaryServiceImpl implements PersonSummaryService {

	@Resource
	PersonSummaryRepository personSummaryRepository;
	@Resource
	TitleRepository titleRepository;
	@Resource
	ContentRepository contentRepository;
	
	@Override
	@Transactional
	public PersonSummary findAndCreate(Long year, Long personId) {
		PersonSummary personSummary=personSummaryRepository.findByYearAndPersonId(year, personId);
		if(personSummary==null){
			personSummary=PersonSummaryFactory.create(year, personId);
			personSummary=personSummaryRepository.save(personSummary);
		}
		List<Title> titles=titleRepository.findByYearOrderByNoASC(year);
		personSummary.initTitleContent(titles);
		return personSummary;
	}
	
	@Override
	public PersonSummary find(Long year, Long personId) {
		PersonSummary personSummary=personSummaryRepository.findByYearAndPersonId(year, personId);
		if(personSummary==null){
			personSummary=PersonSummaryFactory.create(year, personId);
		}
		List<Title> titles=titleRepository.findByYearOrderByNoASC(year);
		personSummary.initTitleContent(titles);
		return personSummary;
	}


	@Override
	@Transactional
	public PersonSummary save(PersonSummary personSummary) {
		return personSummaryRepository.save(personSummary);
	}

	@Override
	@Transactional
	public Content save(Content content) {
		return contentRepository.save(content);
	}

}
