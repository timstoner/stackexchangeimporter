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

@Entity
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(AbstractEntity.class)
public class Comment extends AbstractEntity {

	@Column(name = "CommentId")
	@XmlAttribute(name = "Id")
	private Long commentId;

	@XmlAttribute(name = "PostId")
	@Transient
	private Long postId;

	@ManyToOne
	private Post post;

	@Column
	@XmlAttribute(name = "Score")
	private Integer score;

	@Column(columnDefinition = "TEXT")
	@XmlAttribute(name = "Text")
	private String text;

	@Column
	@XmlAttribute(name = "CreationDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@XmlAttribute(name = "UserId")
	@Transient
	private Long userId;

	@ManyToOne
	private User user;

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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
