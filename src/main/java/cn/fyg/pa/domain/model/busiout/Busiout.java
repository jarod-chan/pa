package cn.fyg.pa.domain.model.busiout;

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
import org.springframework.format.annotation.DateTimeFormat;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.shared.Result;

/**
 *公事外出
 */
@Entity
@Table(name="at_busiout")
public class Busiout {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String no;//编号
	
	@Enumerated(EnumType.STRING)
	private BusiState busiState;//状态
	
	private Long year;//请假年份
	
	private Long month;//请假月份
	
	private Boolean singel;//表明是否是时间短
	
	@Embedded
	@AttributeOverrides({
	    @AttributeOverride(name="date", column=@Column(name="beg_date")),
	    @AttributeOverride(name="ampm", column=@Column(name="beg_ampm"))
	})
	private Dayitem begDayitem;//开始日期
	
	@Embedded
	@AttributeOverrides({
		 @AttributeOverride(name="date", column=@Column(name="end_date")),
		 @AttributeOverride(name="ampm", column=@Column(name="end_ampm"))
	})
	private Dayitem endDayitem;//结束日期
	
	
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
	
	public Boolean getSingel() {
		return singel;
	}

	public void setSingel(Boolean singel) {
		this.singel = singel;
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

	public Result verifyself() {
		if(this.reason==null||StringUtils.trim(this.reason).equals("")){
			return new Result().pass(Boolean.FALSE).cause("公出原因不能为空");
		}
		//开始日期不为空
		if(this.begDayitem.getDate()==null){
			return new Result().pass(Boolean.FALSE).cause("开始日期不能为空");
		}
		//如果有结束日期，则不为空
		if(this.singel==false && this.endDayitem.getDate()==null){
			return new Result().pass(Boolean.FALSE).cause("如果公出超过半天，请选择结束日期");
		}
		//结束日期大于开始日期
		 if(this.singel==false){
			 if(this.getBegDayitem().getDate().compareTo(this.getEndDayitem().getDate())>0){
				 return new Result().pass(Boolean.FALSE).cause("开始日期不能在结束日期之前");
			 }
			 if(this.getBegDayitem().getDate().compareTo(this.getEndDayitem().getDate())==0){
				 
			 }
		 }
		
		
		return new Result().pass(Boolean.TRUE);
	}

	
}
