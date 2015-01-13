package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@MappedSuperclass
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseEntity {

	@Column(name = "Site")
	private String site;

	@Id
	@Column(name = "Id")
	@XmlAttribute(name = "Id")
	private Long id;

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
