package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, String> {

}
