package cn.fyg.pa.domain.model.summarysnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.fyg.pa.domain.shared.state.StateChangeException;

@Entity
public class SummarySnapshot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long year;
	
	private Long month;
	
	@Enumerated(EnumType.STRING)
	private SnapshotEnum state;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date logDate;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(joinColumns=@JoinColumn(name="snapshot_id"))
	private List<SnapshotItem> snapshotItems=new ArrayList<SnapshotItem>();

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

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public SnapshotEnum getState() {
		return state;
	}

	public void setState(SnapshotEnum state) {
		this.state = state;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public List<SnapshotItem> getSnapshotItems() {
		return snapshotItems;
	}

	public void setSnapshotItems(List<SnapshotItem> snapshotItems) {
		this.snapshotItems = snapshotItems;
	}
	
	public void next() throws StateChangeException {
		this.state.next(this);
	}

	public void back() throws StateChangeException {
		this.state.back(this);
	}

}
