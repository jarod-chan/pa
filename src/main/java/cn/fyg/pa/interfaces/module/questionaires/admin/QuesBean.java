package cn.fyg.pa.interfaces.module.questionaires.admin;

import cn.fyg.pa.domain.model.questionaires.ques.Ques;

public class QuesBean {
	
	private Ques ques;
	
	private int  valid;//有效key
	
	private int notFinish;//未完成
	
	private int finish;//已完成
	
	private int invalid;//无效key个数

	public Ques getQues() {
		return ques;
	}

	public void setQues(Ques ques) {
		this.ques = ques;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public int getNotFinish() {
		return notFinish;
	}

	public void setNotFinish(int notFinish) {
		this.notFinish = notFinish;
	}

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}

	public int getInvalid() {
		return invalid;
	}

	public void setInvalid(int invalid) {
		this.invalid = invalid;
	}
	
	

}
