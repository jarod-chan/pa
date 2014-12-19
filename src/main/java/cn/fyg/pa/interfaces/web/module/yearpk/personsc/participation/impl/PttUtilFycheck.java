package cn.fyg.pa.interfaces.web.module.yearpk.personsc.participation.impl;

import java.util.List;

import cn.fyg.pa.domain.model.yearchk.Fycheck;
import cn.fyg.pa.interfaces.web.module.yearpk.personsc.participation.Ptt;
import cn.fyg.pa.interfaces.web.module.yearpk.personsc.participation.PttUtil;

public class PttUtilFycheck implements PttUtil {

	private List<Fycheck> fychecks;

	public PttUtilFycheck(List<Fycheck> fychecks) {
		super();
		this.fychecks = fychecks;
	}

	@Override
	public Ptt computer() {
		int winOrLose = 0;
		int draw = 0;
		for (Fycheck fycheck : this.fychecks) {

			if (fycheck == null)
				continue;
			if (fycheck.getVal().intValue() == 0) {
				draw++;
			} else {
				winOrLose++;
			}

		}
		Ptt ptt = new Ptt(winOrLose, draw);
		return ptt;
	}

}
