package cn.fyg.pa.domain.model.suggestion;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sy_suggestion")
public class Suggestion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long personId;
	
	@Column(length=1024)
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	
	//判断对象是否超时
	public boolean isOvertime() {
		Date now = new Date();
		Long diff=now.getTime()-this.createtime.getTime();
		return diff>24*60*60*1000;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
