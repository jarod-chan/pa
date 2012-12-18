package cn.fyg.pa.domain.model.summary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *总结内容
 */
@Entity
@Table(name="am_content")
public class Content {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long titleId;
	
	@Column(name="personsummary_id")
	private Long personsummaryId;
	
	@Column(length=1024)
	private String content;
	
	public String getFormatContent(){
		return content.replaceAll("\n", "</br>");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getPersonsummaryId() {
		return personsummaryId;
	}

	public void setPersonsummaryId(Long personsummaryId) {
		this.personsummaryId = personsummaryId;
	}
	
}
