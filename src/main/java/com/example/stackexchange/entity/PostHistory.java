package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "posthistory")
public class PostHistory {

	@Id
	@Column
	private Integer Id;

	@Column
	private Integer postHistoryTypeId;

	@Column
	private Integer PostId;

	@Column
	private String RevisionGUID;

	@Column
	private String CreationDate;

	@Column
	private Integer UserId;

	@Column
	private String UserDisplayName;

	@Column(columnDefinition = "TEXT")
	private String Comment;

	@Column(columnDefinition = "TEXT")
	private String Text;

	@Column
	private Integer CloseReasonId;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getPostHistoryTypeId() {
		return postHistoryTypeId;
	}

	public void setPostHistoryTypeId(Integer postHistoryTypeId) {
		this.postHistoryTypeId = postHistoryTypeId;
	}

	public Integer getPostId() {
		return PostId;
	}

	public void setPostId(Integer postId) {
		PostId = postId;
	}

	public String getRevisionGUID() {
		return RevisionGUID;
	}

	public void setRevisionGUID(String revisionGUID) {
		RevisionGUID = revisionGUID;
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

	public String getUserDisplayName() {
		return UserDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		UserDisplayName = userDisplayName;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public Integer getCloseReasonId() {
		return CloseReasonId;
	}

	public void setCloseReasonId(Integer closeReasonId) {
		CloseReasonId = closeReasonId;
	}

}
