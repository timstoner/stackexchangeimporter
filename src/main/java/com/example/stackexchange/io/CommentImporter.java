package com.example.stackexchange.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.Comment;
import com.example.stackexchange.entity.Post;
import com.example.stackexchange.entity.User;
import com.example.stackexchange.repo.CommentRepository;
import com.example.stackexchange.repo.PostRepository;
import com.example.stackexchange.repo.UserRepository;

@Component
public class CommentImporter extends AbstractImporter<Comment, CommentRepository> {

	private static Logger LOG = LoggerFactory.getLogger(CommentImporter.class);

	@Autowired
	private CommentRepository repo;

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Logger getLogger() {
		return LOG;
	}

	@Override
	public String getFileName() {
		return "Comments.xml";
	}

	@Override
	public CommentRepository getRepo() {
		return repo;
	}

	@Override
	public Class<Comment> getC() {
		return Comment.class;
	}

	@Override
	public void lookupForeignDependencies(Comment t) {
		Long postId = t.getPostId();
		if (postId != null) {
			Post p = postRepo.findOne(postId);
			t.setPost(p);
		}

		Long userId = t.getUserId();
		if (userId != null) {
			User u = userRepo.findOne(userId);
			t.setUser(u);
		}
	}
}
