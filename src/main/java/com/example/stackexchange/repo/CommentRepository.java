package com.example.stackexchange.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, String> {

	public Comment findByCommentIdAndSite(Long commentId, String site);

}
