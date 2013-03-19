package cn.fyg.pa.domain.model.group;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;


@Entity
@Table(name="at_group")
public class GroupEntity implements Group {
	
	@Id
	@Column(name="key_")
	private String key;//编码，用于外部调用
	
	private String name;
	
	@ManyToOne(targetEntity=GroupEntity.class)
	@JoinColumn(name = "parent_id")
	private Group parent;
	
	private String code;//编码，用于内部计算
	
	private String uuid;//用于代替id，不作为主键
	
	@PrePersist
	public void prePersist(){
        this.uuid=UUID.randomUUID().toString();
    }

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Group getParent() {
		return parent;
	}

	@Override
	public void setParent(Group parent) {
		this.parent = parent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
