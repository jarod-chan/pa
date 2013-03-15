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
	 * 预约打卡
	 */
	public static final String BUSINESS_CODE="YD";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;//id
	
	private String no;//编号
	
	@Enumerated(EnumType.STRING)
	private Prestate state; 
	
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
	
	@Column(length=512)
	private String  reason;//事由
	
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

	public Result verify() {
		if(this.reason==null||StringUtils.trim(this.reason).equals("")){
			return new Result().pass(Boolean.FALSE).cause("预约打卡原因不能为空");
		}
		return new Result().pass(Boolean.TRUE);
	}

	
	
}
