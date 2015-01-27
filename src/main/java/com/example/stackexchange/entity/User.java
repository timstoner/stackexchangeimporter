package com.example.stackexchange.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "users")
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(AbstractEntity.class)
public class User extends AbstractEntity {

	@Column(name = "UserId")
	@XmlAttribute(name = "Id")
	public Long userId;

	@Column(name = "Reputation")
	@XmlAttribute(name = "Reputation")
	private Integer reputation;

	@Column(name = "CreationDate")
	@XmlAttribute(name = "CreationDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "DisplayName")
	@XmlAttribute(name = "DisplayName")
	private String displayName;

	@Column(name = "LastAccessDate")
	@XmlAttribute(name = "LastAccessDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastAcccessDate;

	@Column(name = "WebsiteUrl")
	@XmlAttribute(name = "WebsiteUrl")
	private String websiteUrl;

	@Column(columnDefinition = "TEXT")
	@XmlAttribute(name = "Location")
	private String location;

	@Column(columnDefinition = "TEXT")
	@XmlAttribute(name = "AboutMe")
	private String aboutMe;

	@Column(name = "Views")
	@XmlAttribute(name = "Views")
	private Integer views;

	@Column(name = "UpVotes")
	@XmlAttribute(name = "UpVotes")
	private Integer upVotes;

	@Column(name = "DownVotes")
	@XmlAttribute(name = "DownVotes")
	private Integer downVotes;

	@Column(name = "Age")
	@XmlAttribute(name = "Age")
	private Integer age;

	@Column(name = "AccountId")
	@XmlAttribute(name = "AccountId")
	private Integer accountId;

	@XmlTransient
	@OneToMany(mappedBy = "user")
	private Set<Badge> badges;

	@XmlTransient
	@OneToMany(mappedBy = "user")
	private Set<Post> posts;

	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Date getLastAcccessDate() {
		return lastAcccessDate;
	}

	public void setLastAcccessDate(Date lastAcccessDate) {
		this.lastAcccessDate = lastAcccessDate;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getUpVotes() {
		return upVotes;
	}

	public void setUpVotes(Integer upVotes) {
		this.upVotes = upVotes;
	}

	public Integer getDownVotes() {
		return downVotes;
	}

	public void setDownVotes(Integer downVotes) {
		this.downVotes = downVotes;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Set<Badge> getBadges() {
		return badges;
	}

	public void setBadges(Set<Badge> badges) {
		this.badges = badges;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

}
