package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@Column
	private Long Id;

	@Column
	private Integer PostTypeId;

	@Column
	private Integer ParentId;

	@Column
	private Integer AcceptedAnswerId;

	@Column
	private String CreationDate;

	@Column
	private Integer Score;

	@Column
	private Integer ViewCount;

	@Column(columnDefinition = "TEXT")
	private String Body;

	@Column
	private Integer OwnerUserId;

	@Column
	private String OwnerDisplayName;

	@Column
	private Integer LastEditorUserId;

	@Column
	private String LastEditorDisplayName;

	@Column
	private String LastEditDate;

	@Column
	private String LastActivityDate;

	@Column(columnDefinition = "TEXT")
	private String Title;

	@Column(columnDefinition = "TEXT")
	private String Tags;

	@Column
	private Integer AnswerCount;

	@Column
	private Integer CommentCount;

	@Column
	private Integer FavoriteCount;

	@Column
	private String ClosedDate;

	@Column
	private String CommunityOwnedDate;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Integer getPostTypeId() {
		return PostTypeId;
	}

	public void setPostTypeId(Integer postType) {
		PostTypeId = postType;
	}

	public Integer getAcceptedAnswerId() {
		return AcceptedAnswerId;
	}

	public void setAcceptedAnswerId(Integer acceptedAnswerId) {
		AcceptedAnswerId = acceptedAnswerId;
	}

	public String getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}

	public Integer getScore() {
		return Score;
	}

	public void setScore(Integer score) {
		Score = score;
	}

	public Integer getViewCount() {
		return ViewCount;
	}

	public void setViewCount(Integer viewCount) {
		ViewCount = viewCount;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

	public Integer getOwnerUserId() {
		return OwnerUserId;
	}

	public void setOwnerUserId(Integer ownerUserId) {
		OwnerUserId = ownerUserId;
	}

	public String getOwnerDisplayName() {
		return OwnerDisplayName;
	}

	public void setOwnerDisplayName(String ownerDisplayName) {
		OwnerDisplayName = ownerDisplayName;
	}

	public Integer getLastEditorUserId() {
		return LastEditorUserId;
	}

	public void setLastEditorUserId(Integer lastEditorUserId) {
		LastEditorUserId = lastEditorUserId;
	}

	public String getLastEditorDisplayName() {
		return LastEditorDisplayName;
	}

	public void setLastEditorDisplayName(String lastEditorDisplayName) {
		LastEditorDisplayName = lastEditorDisplayName;
	}

	public String getLastEditDate() {
		return LastEditDate;
	}

	public void setLastEditDate(String lastEditDate) {
		LastEditDate = lastEditDate;
	}

	public String getLastActivityDate() {
		return LastActivityDate;
	}

	public void setLastActivityDate(String lastActivityDate) {
		LastActivityDate = lastActivityDate;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getTags() {
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
	}

	public Integer getAnswerCount() {
		return AnswerCount;
	}

	public void setAnswerCount(Integer answerCount) {
		AnswerCount = answerCount;
	}

	public Integer getCommentCount() {
		return CommentCount;
	}

	public void setCommentCount(Integer commentCount) {
		CommentCount = commentCount;
	}

	public Integer getFavoriteCount() {
		return FavoriteCount;
	}

	public void setFavoriteCount(Integer favoriteCount) {
		FavoriteCount = favoriteCount;
	}

	public String getClosedDate() {
		return ClosedDate;
	}

	public void setClosedDate(String closedDate) {
		ClosedDate = closedDate;
	}

	public String getCommunityOwnedDate() {
		return CommunityOwnedDate;
	}

	public void setCommunityOwnedDate(String communityOwnedDate) {
		CommunityOwnedDate = communityOwnedDate;
	}

	public Integer getParentId() {
		return ParentId;
	}

	public void setParentId(Integer parentId) {
		ParentId = parentId;
	}

}
