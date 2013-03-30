package cn.fyg.pa.domain.model.usership;

import javax.persistence.Id;

public class Usership {
	
	@Id
	private Long id;
	
	private String tree;
	
	private String key;
	
	private String parentKey;
	
	private String code;
	
	private String tag;

}
