package com.example.stackexchange.io;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.Post;
import com.example.stackexchange.entity.PostHistory;
import com.example.stackexchange.entity.PostHistoryType;
import com.example.stackexchange.entity.User;
import com.example.stackexchange.repo.PostHistoryRepository;
import com.google.common.cache.LoadingCache;

@Component
public class PostHistoryImporter extends AbstractImporter<PostHistory, PostHistoryRepository> {

	private static Logger LOG = LoggerFactory.getLogger(PostHistoryImporter.class);

	@Autowired
	private PostHistoryRepository repo;

	@Autowired
	private LoadingCache<Long, PostHistoryType> postHistoryTypeCache;

	@Autowired
	private LoadingCache<Long, User> userCache;

	@Autowired
	private LoadingCache<Long, Post> postCache;

	@Override
	public Logger getLogger() {
		return LOG;
	}

	@Override
	public String getFileName() {
		return "PostHistory.xml";
	}

	@Override
	public PostHistoryRepository getRepo() {
		return repo;
	}

	@Override
	public Class<PostHistory> getC() {
		return PostHistory.class;
	}

	@Override
	public void lookupForeignDependencies(PostHistory t) {
		Long id = t.getPostHistoryTypeId();
		if (id != null) {
			PostHistoryType type;
			try {
				type = postHistoryTypeCache.get(id);
				t.setPostHistoryType(type);
			} catch (ExecutionException e) {
				LOG.debug("Could not get Post History Type", e);
			}
		}

		id = t.getUserId();
		if (id != null) {
			User user;
			try {
				user = userCache.get(id);
				t.setUser(user);
			} catch (ExecutionException e) {
				LOG.debug("Could not get User", e);
			}
		}

		id = t.getPostId();
		if (id != null) {
			Post post;
			try {
				post = postCache.get(id);
				t.setPost(post);
			} catch (ExecutionException e) {
				LOG.debug("Could not get Post", e);
			}
		}
	}
}
