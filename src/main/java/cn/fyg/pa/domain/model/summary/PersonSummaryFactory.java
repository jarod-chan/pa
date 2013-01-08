package cn.fyg.pa.domain.model.summary;


public class PersonSummaryFactory {
	
	public static PersonSummary create(Long year,Long personId){
		PersonSummary personSummary = new PersonSummary();
		personSummary.setYear(year);
		personSummary.setPersonId(personId);
		personSummary.setSummaryEnum(SummaryEnum.save);
		return personSummary;
	}

}
