package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.LinkType;

public interface LinkTypeRepository extends JpaRepository<LinkType, Long> {

}
