package cn.fyg.pa.domain.model.yearchkstate;

public class YearchkStateFactory {
	
	public static YearchkState create(Long year,Long personId){
		YearchkState yearchkState=new YearchkState();
		yearchkState.setYear(year);
		yearchkState.setPersonId(personId);
		return yearchkState;
	}

}
