package cn.fyg.pa.domain.model.atten.busiout;

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

/**
 *公事外出
 */
@Entity
@Table(name="at_busiout")
public class Busiout {
	
	/**
	 * 短期公出
	 */
	public static final String BUSINESS_CODE="DQ";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String no;//编号
	
	@Enumerated(EnumType.STRING)
	private BusiState busiState;//状态
	
	private String place;//地点
	
	private String leader;//领导
	
	@Column(length=256)
	private String  reason;//事由

	@Embedded
	@AttributeOverrides({
		 @AttributeOverride(name="year", column=@Column(name="beg_year")),
		 @AttributeOverride(name="month", column=@Column(name="beg_month")),
		 @AttributeOverride(name="day", column=@Column(name="beg_day")),
		 @AttributeOverride(name="ampm", column=@Column(name="beg_ampm")),
		 @AttributeOverride(name="value", column=@Column(name="beg_value"))
	})
	private Dayitem begDayitem;//开始日期
	
	@Embedded
	@AttributeOverrides({
		 @AttributeOverride(name="year", column=@Column(name="end_year")),
		 @AttributeOverride(name="month", column=@Column(name="end_month")),
		 @AttributeOverride(name="day", column=@Column(name="end_day")),
		 @AttributeOverride(name="ampm", column=@Column(name="end_ampm")),
		 @AttributeOverride(name="value", column=@Column(name="end_value"))
	})
	private Dayitem endDayitem;//结束日期
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;//申请人
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date commitDate;//提交日期
	
	public void computeDayitemValueBeforeSave(){
		this.begDayitem.computeValue();
		this.endDayitem.computeValue();
	}

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

	public Dayitem getBegDayitem() {
		return begDayitem;
	}

	public void setBegDayitem(Dayitem begDayitem) {
		this.begDayitem = begDayitem;
	}

	public Dayitem getEndDayitem() {
		return endDayitem;
	}

	public void setEndDayitem(Dayitem endDayitem) {
		this.endDayitem = endDayitem;
	}

	public BusiState getBusiState() {
		return busiState;
	}

	public void setBusiState(BusiState busiState) {
		this.busiState = busiState;
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
		if(this.begDayitem.compareTo(this.endDayitem)>0){
			return new Result().pass(Boolean.FALSE).cause("结束日期不能在开始日期之前");
		}
		
		return new Result().pass(Boolean.TRUE);
	}

	
}
