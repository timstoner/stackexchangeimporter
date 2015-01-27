package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.Post;

public interface PostRepository extends JpaRepository<Post, String> {

	Post findByParentIdAndSite(Long parentId, String site);

	Post findByPostIdAndSite(Long postId, String siteName);

}
