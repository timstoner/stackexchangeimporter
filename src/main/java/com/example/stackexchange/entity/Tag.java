package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tags")
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tag extends BaseEntity {
	@Column
	@XmlAttribute(name = "TagName")
	private String tagName;

	@Column
	@XmlAttribute(name = "Count")
	private Integer count;

	@Column
	@XmlAttribute(name = "ExcerptPostId")
	private Long excerptPostId;

	@Column
	@XmlAttribute(name = "WikiPostId")
	private Long wikiPostId;

}
