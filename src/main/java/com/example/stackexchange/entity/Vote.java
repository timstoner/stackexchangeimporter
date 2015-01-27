package com.example.stackexchange.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
public class Vote extends AbstractEntity {

	@XmlAttribute(name = "PostId")
	@Transient
	private Long postId;

	@ManyToOne
	@JoinColumn(name = "PostId")
	private Post post;

	@XmlAttribute(name = "VoteTypeId")
	@Transient
	private Long voteTypeId;

	@ManyToOne
	@JoinColumn(name = "VoteTypeId")
	private VoteType voteType;

	@Column
	@XmlAttribute(name = "CreationDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@XmlAttribute(name = "UserId")
	@Transient
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;

	@Column
	@XmlAttribute(name = "BountyAmount")
	private Integer bountyAmount;

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

	public Long getVoteTypeId() {
		return voteTypeId;
	}

	public void setVoteTypeId(Long voteTypeId) {
		this.voteTypeId = voteTypeId;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
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

	public Integer getBountyAmount() {
		return bountyAmount;
	}

	public void setBountyAmount(Integer bountyAmount) {
		this.bountyAmount = bountyAmount;
	}

}
