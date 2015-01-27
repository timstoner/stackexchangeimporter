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
public class PostHistory extends AbstractEntity {

	@Column
	@XmlAttribute(name = "Id")
	private Long postHistoryId;

	@Transient
	@XmlAttribute(name = "PostHistoryTypeId")
	private Long postHistoryTypeId;

	@ManyToOne
	@XmlTransient
	private PostHistoryType postHistoryType;

	@Transient
	@XmlAttribute(name = "PostId")
	private Long postId;

	@ManyToOne
	@XmlTransient
	private Post post;

	@Column
	@XmlAttribute(name = "RevisionGUID")
	private String revisionGUID;

	@Column
	@XmlAttribute(name = "CreationDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Transient
	@XmlAttribute(name = "UserId")
	private Long userId;

	@ManyToOne
	@XmlTransient
	private User user;

	@Column(columnDefinition = "TEXT")
	@XmlAttribute(name = "Comment")
	private String comment;

	@Column(columnDefinition = "TEXT")
	@XmlAttribute(name = "Text")
	private String text;

	public Long getPostHistoryId() {
		return postHistoryId;
	}

	public void setPostHistoryId(Long postHistoryId) {
		this.postHistoryId = postHistoryId;
	}

	public Long getPostHistoryTypeId() {
		return postHistoryTypeId;
	}

	public void setPostHistoryTypeId(Long postHistoryTypeId) {
		this.postHistoryTypeId = postHistoryTypeId;
	}

	public PostHistoryType getPostHistoryType() {
		return postHistoryType;
	}

	public void setPostHistoryType(PostHistoryType postHistoryType) {
		this.postHistoryType = postHistoryType;
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

	public String getRevisionGUID() {
		return revisionGUID;
	}

	public void setRevisionGUID(String revisionGUID) {
		this.revisionGUID = revisionGUID;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
