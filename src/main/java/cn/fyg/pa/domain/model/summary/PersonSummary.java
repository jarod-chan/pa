package cn.fyg.pa.domain.model.summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 *用户总结
 */
@Entity
@Table(name="am_personsummary")
public class PersonSummary {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long year;

	private Long personId;
	
	@Enumerated(EnumType.STRING)
	private SummaryEnum summaryEnum;
	
	@OneToMany(fetch = FetchType.EAGER, 
			cascade = {CascadeType.ALL},
			targetEntity = Content.class,
			orphanRemoval=true)
	@OrderBy("id ASC")
	@JoinColumn(name="personsummary_id")  
	private List<Content> contents=new ArrayList<Content>();
	
	@Transient
	private List<TitleContent> titleContents=new ArrayList<TitleContent>();
	
	public void initTitleContent(List<Title> titles){
		Map<Long,Content> contentMap=getContentMap();
		this.titleContents.clear();
		for (Title title : titles) {
			TitleContent titleContent=new TitleContent();
			titleContent.setTitle(title);
			titleContent.setContent(contentMap.get(title.getId()));
			this.titleContents.add(titleContent);
		}
	}

	private Map<Long, Content> getContentMap() {
		Map<Long,Content> contentMap=new HashMap<Long,Content>();
		for(Content content:this.contents){
			contentMap.put(content.getTitleId(),content);
		}
		return contentMap;
	}

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

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public SummaryEnum getSummaryEnum() {
		return summaryEnum;
	}

	public void setSummaryEnum(SummaryEnum summaryEnum) {
		this.summaryEnum = summaryEnum;
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	public List<TitleContent> getTitleContents() {
		return titleContents;
	}

	public void setTitleContents(List<TitleContent> titleContents) {
		this.titleContents = titleContents;
	}
	
	

}
