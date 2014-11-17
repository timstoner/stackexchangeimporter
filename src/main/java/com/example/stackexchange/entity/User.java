package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column
	private Integer Id;

	@Column
	private Integer Reputation;

	@Column
	private String CreationDate;

	@Column
	private String DisplayName;

	@Column
	private String WebsiteUrl;

	@Column(columnDefinition = "TEXT")
	private String Location;

	@Column(columnDefinition = "TEXT")
	private String AboutMe;

	@Column
	private Integer Views;

	@Column
	private Integer UpVotes;

	@Column
	private Integer DownVotes;

	@Column(columnDefinition = "TEXT")
	private String ProfileImageUrl;

	@Column(columnDefinition = "TEXT")
	private String EmailHash;

	@Column
	private Integer Age;

	@Column
	private Integer AccountId;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getReputation() {
		return Reputation;
	}

	public void setReputation(Integer reputation) {
		Reputation = reputation;
	}

	public String getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}

	public String getDisplayName() {
		return DisplayName;
	}

	public void setDisplayName(String displayName) {
		DisplayName = displayName;
	}

	public String getWebsiteUrl() {
		return WebsiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		WebsiteUrl = websiteUrl;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getAboutMe() {
		return AboutMe;
	}

	public void setAboutMe(String aboutMe) {
		AboutMe = aboutMe;
	}

	public Integer getViews() {
		return Views;
	}

	public void setViews(Integer views) {
		Views = views;
	}

	public Integer getUpVotes() {
		return UpVotes;
	}

	public void setUpVotes(Integer upVotes) {
		UpVotes = upVotes;
	}

	public Integer getDownVotes() {
		return DownVotes;
	}

	public void setDownVotes(Integer downVotes) {
		DownVotes = downVotes;
	}

	public String getProfileImageUrl() {
		return ProfileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		ProfileImageUrl = profileImageUrl;
	}

	public String getEmailHash() {
		return EmailHash;
	}

	public void setEmailHash(String emailHash) {
		EmailHash = emailHash;
	}

	public Integer getAge() {
		return Age;
	}

	public void setAge(Integer age) {
		Age = age;
	}

	public Integer getAccountId() {
		return AccountId;
	}

	public void setAccountId(Integer accountId) {
		AccountId = accountId;
	}

}
