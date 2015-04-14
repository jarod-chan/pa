package cn.fyg.pa.domain.model.suggestion;

import java.util.Date;

public class SuggestionFactory {


	public static Suggestion create(Long personId,String content){
		Suggestion suggestion = new Suggestion();
		suggestion.setPersonId(personId);
		suggestion.setCreatetime(new Date());
		suggestion.setContent(content);
		return suggestion;
	}

}
