package cn.fyg.pa.domain.model.summary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *总结内容标题
 *规范总结时输入的内容
 */
@Entity
@Table(name="am_title")
public class Title {
	
	@Id
	private Long id;
	
	private Long year;
	
	private Long no;
	
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
