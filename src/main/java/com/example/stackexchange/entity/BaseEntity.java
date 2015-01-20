package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlTransient;

//@XmlRootElement(name = "row")
@MappedSuperclass
public class BaseEntity {
	@Column(name = "Site")
	@XmlTransient
	private String site;

	@Id
	@Column(name = "Id")
	@GeneratedValue
	// @XmlAttribute(name = "Id")
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
