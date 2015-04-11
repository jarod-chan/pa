package cn.fyg.pa.domain.model.department;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import cn.fyg.pa.domain.shared.CommonEnum;

@Entity
public class Department {
	
	public enum State implements CommonEnum {
		enable("启用"),
		disable("禁用");

		private String name;
		
		private State(String name) {
			this.name=name;
		}
		
		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public void setName(String name) {
			this.name=name;
		}

	}
	
	@Id
	private Long id;
	
	private String number;//编码
	
	private String name;//名称
	
	private Long gmange_id;//分管副总
	
	@Enumerated(EnumType.STRING)
	private State state;//状态

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getGmange_id() {
		return gmange_id;
	}

	public void setGmange_id(Long gmange_id) {
		this.gmange_id = gmange_id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}
