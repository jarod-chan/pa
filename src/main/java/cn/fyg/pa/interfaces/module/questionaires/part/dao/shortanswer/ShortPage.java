package cn.fyg.pa.interfaces.module.questionaires.part.dao.shortanswer;

import java.util.List;

public class ShortPage {
	
	private boolean finish;
	
	List<ShortBean> shortBeans;

	public List<ShortBean> getShortBeans() {
		return shortBeans;
	}

	public void setShortBeans(List<ShortBean> shortBeans) {
		this.shortBeans = shortBeans;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	
}
