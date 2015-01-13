package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
