package cn.fyg.pa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PersonMonth implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne  
    @JoinColumn(name = "person_id")  
	private Person person;
	
	private Long year;
	
	private Long month;
}
