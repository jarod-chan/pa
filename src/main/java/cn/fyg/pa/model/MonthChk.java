package cn.fyg.pa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.fyg.pa.model.enums.StateEnum;

@Entity
public class MonthChk implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne  
    @JoinColumn(name = "person_id")  
	private Person person;
	
	private Long year;
	private Long month;
	
	@Enumerated(EnumType.STRING)
	private StateEnum state;

	

}
