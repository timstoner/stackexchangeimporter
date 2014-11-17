package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
