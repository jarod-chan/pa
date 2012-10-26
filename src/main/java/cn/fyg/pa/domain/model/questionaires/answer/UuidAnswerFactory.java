package cn.fyg.pa.domain.model.questionaires.answer;

public class UuidAnswerFactory {
	
	public static UuidAnswer create(String uuid,Long qtid,Long partid){
		UuidAnswer uuidAnswer=new UuidAnswer();
		uuidAnswer.setUuid(uuid);
		uuidAnswer.setQtid(qtid);
		uuidAnswer.setPartid(partid);
		return uuidAnswer;
	}

}
