package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, String> {

}
