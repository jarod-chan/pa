package cn.fyg.pa.domain.model.atten.opinion;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *用来记录领导对业务单据的审批记录
 */
@Entity
@Table(name="at_opinion")
public class Opinion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String businessCode;//业务编码，用来区分不同单据
	
	private Long businessId;//业务id，唯一确定一个id
	
	private String taskKey;//任务key
	
	private String taskName;//任务名称
	
	private String userKey;//审批人key
	
	private String userName;//审批人姓名
	
	@Enumerated(EnumType.STRING)
	private ResultEnum result;//是否同意
	
	@Column(length=512)
	private String content;//审批意见
	
	@Column(name="date_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;//日期
		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ResultEnum getResult() {
		return result;
	}

	public void setResult(ResultEnum result) {
		this.result = result;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	public String getBusinessCode() {
		return businessCode;
	}



	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}



	public Long getBusinessId() {
		return businessId;
	}



	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

		

}
