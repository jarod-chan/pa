package cn.fyg.pa.domain.model.questionaires.problem;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *简答题
 */
@Table(name="qs_shortanswer")
public class ShortAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long qtid;//调查问卷id
	
	private String subject;//问题内容
	
}
