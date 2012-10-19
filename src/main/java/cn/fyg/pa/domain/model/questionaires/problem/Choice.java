package cn.fyg.pa.domain.model.questionaires.problem;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *选择题
 */
@Table(name="qs_choice")
public class Choice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long qtid;//调查问卷id
	
	private String subject;//问题内容
	
	private List<Answer> answers;//问题答案

}
