package cn.fyg.pa.domain.model.questionaires.problem;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="qs_answer")
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long qtid;//调查问卷id
	
	private int group;//答案分组，解决出现分组的情况
	
	private String no;//答案序号
	
	private String name;//答案内容
	
	private int value;//值
	
}
