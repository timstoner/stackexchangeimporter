package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	// public User findByUserId(Long id);

	public User findByUserIdAndSite(Long userId, String siteName);
}
