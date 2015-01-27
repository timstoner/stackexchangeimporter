package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@Entity
// @Table(name = "tags")
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(AbstractEntity.class)
public class Tag extends AbstractEntity {
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
