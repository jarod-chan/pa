package cn.fyg.pa.domain.model.atten.preatten;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;

import cn.fyg.pa.domain.model.atten.common.Dayitem;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.shared.Result;

@Entity
@Table(name="at_preatten")
public class Preatten {
	
	/**
	 * 临时公出
	 */
	public static final String BUSINESS_CODE="LS";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;//id
	
	private String no;//编号
	
	@Enumerated(EnumType.STRING)
	private Prestate state; 
	
	private String place;//地点
	
	private String leader;//领导
	
	@Column(length=256)
	private String  reason;//事由
	
	@Column(name="year_")
	private Long year;//年份
	
	@Column(name="month_")
	private Long month;//月份
	
	@Embedded
	@AttributeOverrides({
		 @AttributeOverride(name="date", column=@Column(name="di_date")),
		 @AttributeOverride(name="ampm", column=@Column(name="di_ampm"))
	})
	private Dayitem dayitem;//预约时间
	
	
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;//申请人
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date commitDate;//提交日期

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Prestate getState() {
		return state;
	}

	public void setState(Prestate state) {
		this.state = state;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Dayitem getDayitem() {
		return dayitem;
	}

	public void setDayitem(Dayitem dayitem) {
		this.dayitem = dayitem;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public Result verify() {
		if(StringUtils.isBlank(this.place)){
			return new Result().pass(Boolean.FALSE).cause("【地点】不能为空");
		}
		if(StringUtils.isBlank(this.leader)){
			return new Result().pass(Boolean.FALSE).cause("【上级】不能为空");
		}
		if(StringUtils.isBlank(this.reason)){
			return new Result().pass(Boolean.FALSE).cause("【原因】不能为空");
		}
		
		return new Result().pass(Boolean.TRUE);
	}

	
	
}
