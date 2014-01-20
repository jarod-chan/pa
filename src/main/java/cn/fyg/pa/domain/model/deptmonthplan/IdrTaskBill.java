package cn.fyg.pa.domain.model.deptmonthplan;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)  
public abstract class IdrTaskBill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	@OneToMany(mappedBy = "idrTaskBill",
			fetch = FetchType.EAGER, 
			cascade = {CascadeType.ALL},
			targetEntity = IdrTask.class)
	@OrderBy("sn ASC")
	private List<IdrTask> idrTasks=new ArrayList<IdrTask>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<IdrTask> getIdrTasks() {
		return idrTasks;
	}


	public void setIdrTasks(List<IdrTask> idrTasks) {
		this.idrTasks = idrTasks;
	}

}
