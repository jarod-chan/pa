package cn.fyg.pa.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fychkitem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private String content;
	private Long point;
	/**
	 * 工作计划
	 * 岗位职责
	 * 工作能力
	 * 创新成果
	 */
	private String type;
	private Boolean must;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getPoint() {
		return point;
	}
	public void setPoint(Long point) {
		this.point = point;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getMust() {
		return must;
	}
	public void setMust(Boolean must) {
		this.must = must;
	}
	
	

}
