package cn.fyg.pa.domain.model.questionaires.problem;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Part {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long qtid;//调查问卷id
	
	private String no;//序号
	
	private String name;//分组描述
	
	private PartEnum type;//问题类型，简答题或者选择题

}
