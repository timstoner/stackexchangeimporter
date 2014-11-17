package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "votes")
public class Vote {

	@Id
	@Column
	private Integer Id;

	@Column
	private Integer PostId;

	@Column
	private Integer VoteTypeId;

	@Column
	private String CreationDate;

	@Column
	private Integer UserId;

	@Column
	private Integer BountyAmount;

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

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public Integer getBountyAmount() {
		return BountyAmount;
	}

	public void setBountyAmount(Integer bountyAmount) {
		BountyAmount = bountyAmount;
	}

}
