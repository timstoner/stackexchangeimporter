package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.PostHistory;

public interface PostHistoryRepository extends JpaRepository<PostHistory, String> {

}
