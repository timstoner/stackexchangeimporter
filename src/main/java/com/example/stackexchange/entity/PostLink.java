package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "postlinks")
public class PostLink {

	@Id
	@Column
	private Integer Id;

	@Column
	private String CreationDate;

	@Column
	private Integer PostId;

	@Column
	private Integer RelatedPostId;

	@Column
	private Integer LinkType;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}

	public Integer getPostId() {
		return PostId;
	}

	public void setPostId(Integer postId) {
		PostId = postId;
	}

	public Integer getRelatedPostId() {
		return RelatedPostId;
	}

	public void setRelatedPostId(Integer relatedPostId) {
		RelatedPostId = relatedPostId;
	}

	public Integer getLinkType() {
		return LinkType;
	}

	public void setLinkType(Integer linkType) {
		LinkType = linkType;
	}

}
