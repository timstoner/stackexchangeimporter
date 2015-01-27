package com.example.stackexchange.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class PostLink extends AbstractEntity {

	@Column
	@XmlAttribute(name = "Id")
	private Long postLinkId;

	@Column
	@XmlAttribute(name = "CreationDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Transient
	@XmlAttribute(name = "PostId")
	private Long postId;

	@ManyToOne
	@XmlTransient
	private Post post;

	@Transient
	@XmlAttribute(name = "RelatedPostId")
	private Long relatedPostId;

	@ManyToOne
	@XmlTransient
	private Post relatedPost;

	@Transient
	@XmlAttribute(name = "LinkTypeId")
	private Long linkTypeId;

	@ManyToOne
	@XmlTransient
	private LinkType linkType;

	public Long getPostLinkId() {
		return postLinkId;
	}

	public void setPostLinkId(Long postLinkId) {
		this.postLinkId = postLinkId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Long getRelatedPostId() {
		return relatedPostId;
	}

	public void setRelatedPostId(Long relatedPostId) {
		this.relatedPostId = relatedPostId;
	}

	public Post getRelatedPost() {
		return relatedPost;
	}

	public void setRelatedPost(Post relatedPost) {
		this.relatedPost = relatedPost;
	}

	public LinkType getLinkType() {
		return linkType;
	}

	public void setLinkType(LinkType linkType) {
		this.linkType = linkType;
	}

	public Long getLinkTypeId() {
		return linkTypeId;
	}

	public void setLinkTypeId(Long linkTypeId) {
		this.linkTypeId = linkTypeId;
	}

}
