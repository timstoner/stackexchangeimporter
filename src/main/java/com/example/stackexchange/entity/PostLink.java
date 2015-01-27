package com.example.stackexchange.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@Entity
// @Table(name = "postlinks")
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(AbstractEntity.class)
public class PostLink extends AbstractEntity {

	@Column
	@XmlAttribute(name = "CreationDate")
	private Date creationDate;

	@Transient
	@XmlAttribute(name = "PostId")
	private Integer PostId;

	@ManyToOne
	private Post post;

	@Transient
	@XmlAttribute(name = "RelatedPostId")
	private Integer RelatedPostId;

	@ManyToOne
	private Post relatedPost;

	@Column
	private Integer LinkType;

}
