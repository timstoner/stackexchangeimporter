package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.PostType;

public interface PostTypeRepository extends JpaRepository<PostType, Long> {

}
