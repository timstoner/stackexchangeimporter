package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Integer> {

}
