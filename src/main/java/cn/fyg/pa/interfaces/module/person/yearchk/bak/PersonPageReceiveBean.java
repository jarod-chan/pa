package cn.fyg.pa.interfaces.module.person.yearchk.bak;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.pa.domain.model.yearchk.Fycheck;

public class PersonPageReceiveBean {

	private List<Fycheck> fychecks=new ArrayList<Fycheck>();

	public List<Fycheck> getFychecks() {
		return fychecks;
	}

	public void setFychecks(List<Fycheck> fychecks) {
		this.fychecks = fychecks;
	}
}
