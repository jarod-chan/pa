package cn.fyg.pa.domain.model.questionaires.answer;

public class UuidShortFactory {
	
	public static UuidShort create(String uuid,Long qtid,Long partid){
		UuidShort uuidShort=new UuidShort();
		uuidShort.setUuid(uuid);
		uuidShort.setQtid(qtid);
		uuidShort.setPartid(partid);
		return uuidShort;
	}

}
