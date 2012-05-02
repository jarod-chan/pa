package cn.fyg.pa.domain.yearchk;

public class FycheckFactory {
	
	public static Fycheck createFycheck(Long year,Long colPersonId,Long rowPersonId,Long chkId){
		Fycheck fycheck=new Fycheck();
		fycheck.setYear(year);
		fycheck.setColId(colPersonId);
		fycheck.setRowId(rowPersonId);
		fycheck.setChkId(chkId);
		fycheck.setVal(0L);
		return fycheck;
	}

}
