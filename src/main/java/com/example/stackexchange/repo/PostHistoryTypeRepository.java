package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.PostHistoryType;

public interface PostHistoryTypeRepository extends JpaRepository<PostHistoryType, Long> {

}
