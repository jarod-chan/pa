package cn.fyg.pa.domain.model.usership;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *用户上下级关系
 */
@Entity
@Table(name="at_usership")
public class Usership {
	
	@Id
	private Long id;
	
	private String tree;
	
	@Column(name="key_")
	private String key;
	
	private String parentKey;
	
	private String code;
	
	private String tag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	

}
