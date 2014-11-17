package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.solr.client.solrj.beans.Field;

@Entity
@Table(name = "badges")
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class Badge {

	@Column(name = "site_name")
	private static String siteName;

	@Id
	@Column
	@XmlAttribute(name = "Id")
	private Long Id;

	@Column
	@XmlAttribute(name = "UserId")
	@Field
	private Integer UserId;

	@Column
	@XmlAttribute(name = "Name")
	@Field("name_t")
	private String Name;

	@Column
	@XmlAttribute(name = "Date")
	@Field
	private String Date;

	public Badge() {

	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	@Field("id")
	public String getSolrId() {
		return siteName + ":" + Id;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public static String getSiteName() {
		return siteName;
	}

	public static void setSiteName(String siteName) {
		Badge.siteName = siteName;
	}

}
