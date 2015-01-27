package com.example.stackexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// @Table(name = "sites")
public class Site {

	@Column(name = "Name")
	private String name;

	@Id
	@Column(name = "Id")
	private Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
