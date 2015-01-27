package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {

	public Site findByName(String name);

}
