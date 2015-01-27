package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.PostLink;

public interface PostLinkRepository extends JpaRepository<PostLink, String> {

}
