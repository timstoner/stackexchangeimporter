package com.example.stackexchange.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

	@Transient
	@XmlAttribute(name = "PostTypeId")
	private Long postTypeId;

	@ManyToOne
	private PostType postType;

	@Transient
	@XmlAttribute(name = "ParentId")
	private Long parentId;

	@OneToOne
	@JoinColumn(name = "ParentPostId")
	private Post parentPost;

	@Transient
	@XmlAttribute(name = "AcceptedAnswerId")
	private Long acceptedAnswerId;

	@XmlTransient
	@OneToOne
	private Post acceptedAnswer;

	@Column
	@XmlAttribute(name = "CreationDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column
	@XmlAttribute(name = "Score")
	private Integer score;

	@Column
	@XmlAttribute(name = "ViewCount")
	private Integer viewCount;

	@Column(columnDefinition = "TEXT")
	@XmlAttribute(name = "Body")
	private String body;

	@Transient
	@XmlAttribute(name = "Id")
	private Long ownerUserId;
	@XmlTransient
	@ManyToOne
	private User ownerUser;

	@Column
	@XmlAttribute(name = "OwnerDisplayName")
	private String ownerDisplayName;

	@Transient
	@XmlAttribute(name = "LastEditorUserId")
	private Long lastEditorUserId;
	@XmlTransient
	@ManyToOne
	private User lastEditorUser;

	@Column
	@XmlAttribute(name = "LastEditorDisplayName")
	private String lastEditorDisplayName;

	@Column
	@XmlAttribute(name = "LastEditDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastEditDate;

	@Column
	@XmlAttribute(name = "LastActivityDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastActivityDate;

	@Column(columnDefinition = "TEXT")
	@XmlAttribute(name = "Title")
	private String title;

	@Column(columnDefinition = "TEXT")
	@XmlAttribute(name = "Tags")
	private String tags;

	@Column
	@XmlAttribute(name = "AnswerCount")
	private Integer answerCount;

	@Column
	@XmlAttribute(name = "CommentCount")
	private Integer commentCount;

	@Column
	@XmlAttribute(name = "FavoriteCount")
	private Integer favoriteCount;

	@Column
	@XmlAttribute(name = "ClosedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date closedDate;

	@Column
	@XmlAttribute(name = "CommunityOwnedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date communityOwnedDate;

	public Long getPostTypeId() {
		return postTypeId;
	}

	public void setPostTypeId(Long postTypeId) {
		this.postTypeId = postTypeId;
	}

	public PostType getPostType() {
		return postType;
	}

	public void setPostType(PostType postType) {
		this.postType = postType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Post getParentPost() {
		return parentPost;
	}

	public void setParentPost(Post parentPost) {
		this.parentPost = parentPost;
	}

	public Long getAcceptedAnswerId() {
		return acceptedAnswerId;
	}

	public void setAcceptedAnswerId(Long acceptedAnswerId) {
		this.acceptedAnswerId = acceptedAnswerId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Long getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(Long ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public User getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}

	public String getOwnerDisplayName() {
		return ownerDisplayName;
	}

	public void setOwnerDisplayName(String ownerDisplayName) {
		this.ownerDisplayName = ownerDisplayName;
	}

	public Long getLastEditorUserId() {
		return lastEditorUserId;
	}

	public void setLastEditorUserId(Long lastEditorUserId) {
		this.lastEditorUserId = lastEditorUserId;
	}

	public User getLastEditorUser() {
		return lastEditorUser;
	}

	public void setLastEditorUser(User lastEditorUser) {
		this.lastEditorUser = lastEditorUser;
	}

	public String getLastEditorDisplayName() {
		return lastEditorDisplayName;
	}

	public void setLastEditorDisplayName(String lastEditorDisplayName) {
		this.lastEditorDisplayName = lastEditorDisplayName;
	}

	public Date getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(Date lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

	public Date getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(Integer favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public Date getCommunityOwnedDate() {
		return communityOwnedDate;
	}

	public void setCommunityOwnedDate(Date communityOwnedDate) {
		this.communityOwnedDate = communityOwnedDate;
	}

	public Post getAcceptedAnswer() {
		return acceptedAnswer;
	}

	public void setAcceptedAnswer(Post acceptedAnswer) {
		this.acceptedAnswer = acceptedAnswer;
	}

}
