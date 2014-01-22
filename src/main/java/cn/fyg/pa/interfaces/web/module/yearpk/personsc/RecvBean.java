package cn.fyg.pa.interfaces.web.module.yearpk.personsc;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.model.yearchk.Fycheck;

public class RecvBean {
	
	List<Fycheck> fk=new ArrayList<Fycheck>();

	public List<Fycheck> getFk() {
		return fk;
	}

	public void setFk(List<Fycheck> fk) {
		this.fk = fk;
	}
	
}
