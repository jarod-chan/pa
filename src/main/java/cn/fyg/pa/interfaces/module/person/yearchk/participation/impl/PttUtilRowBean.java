package cn.fyg.pa.interfaces.module.person.yearchk.participation.impl;

import java.util.List;

import cn.fyg.pa.interfaces.module.person.yearchk.CellBean;
import cn.fyg.pa.interfaces.module.person.yearchk.RowBean;
import cn.fyg.pa.interfaces.module.person.yearchk.participation.Ptt;
import cn.fyg.pa.interfaces.module.person.yearchk.participation.PttUtil;

public class PttUtilRowBean implements PttUtil {

	private List<RowBean> rowBeans;
	

	public PttUtilRowBean(List<RowBean> rowBeans) {
		super();
		this.rowBeans = rowBeans;
	}
	
	
	@Override
	public Ptt computer() {
		int winOrLose = 0;
		int draw = 0;
		for (RowBean row : this.rowBeans) {
			for (CellBean cell : row.getCellBeans()) {
				if(cell==null) continue;
				if (cell.getFycheck().getVal().intValue() == 0) {
					draw++;
				} else {
					winOrLose++;
				}
			}

		}
		Ptt ptt = new Ptt(winOrLose, draw);
		return ptt;
	}

}
