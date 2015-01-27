package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.VoteType;

public interface VoteTypeRepository extends JpaRepository<VoteType, Long> {

}
