package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "postfeedback")
public class PostFeedback {

	@Id
	@Column
	private Integer Id;

	@Column
	private Integer PostId;

	@Column
	private boolean IsAnonymous;

	@Column
	private Integer VoteTypeId;

	@Column
	private String CreationDate;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getPostId() {
		return PostId;
	}

	public void setPostId(Integer postId) {
		PostId = postId;
	}

	public boolean isIsAnonymous() {
		return IsAnonymous;
	}

	public void setIsAnonymous(boolean isAnonymous) {
		IsAnonymous = isAnonymous;
	}

	public Integer getVoteTypeId() {
		return VoteTypeId;
	}

	public void setVoteTypeId(Integer voteTypeId) {
		VoteTypeId = voteTypeId;
	}

	public String getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}

}
