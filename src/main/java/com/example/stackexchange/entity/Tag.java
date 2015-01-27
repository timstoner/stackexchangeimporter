package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(AbstractEntity.class)
public class Tag extends AbstractEntity {

	@Column
	@XmlAttribute(name = "Id")
	private Long tagId;

	@Column
	@XmlAttribute(name = "TagName")
	private String tagName;

	@Column
	@XmlAttribute(name = "Count")
	private Integer count;

	@Transient
	@XmlAttribute(name = "ExcerptPostId")
	private Long excerptPostId;

	@ManyToOne
	@JoinColumn(name = "excerptPostId")
	@XmlTransient
	private Post excerptPost;

	@Transient
	@XmlAttribute(name = "WikiPostId")
	private Long wikiPostId;

	@ManyToOne
	@JoinColumn(name = "wikiPostId")
	@XmlTransient
	private Post wikiPost;

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getExcerptPostId() {
		return excerptPostId;
	}

	public void setExcerptPostId(Long excerptPostId) {
		this.excerptPostId = excerptPostId;
	}

	public Post getExcerptPost() {
		return excerptPost;
	}

	public void setExcerptPost(Post excerptPost) {
		this.excerptPost = excerptPost;
	}

	public Long getWikiPostId() {
		return wikiPostId;
	}

	public void setWikiPostId(Long wikiPostId) {
		this.wikiPostId = wikiPostId;
	}

	public Post getWikiPost() {
		return wikiPost;
	}

	public void setWikiPost(Post wikiPost) {
		this.wikiPost = wikiPost;
	}

}
