package com.example.stackexchange.io;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.Post;
import com.example.stackexchange.entity.PostType;
import com.example.stackexchange.entity.User;
import com.example.stackexchange.repo.PostRepository;
import com.google.common.cache.LoadingCache;

@Component
public class PostImporter extends AbstractImporter<Post, PostRepository> {

	private static Logger LOG = LoggerFactory.getLogger(PostImporter.class);

	@Autowired
	private PostRepository repo;

	@Autowired
	private LoadingCache<Long, User> userCache;

	@Autowired
	private LoadingCache<Long, PostType> postTypeCache;

	@Override
	public Logger getLogger() {
		return LOG;
	}

	@Override
	public String getFileName() {
		return "Posts.xml";
	}

	@Override
	public PostRepository getRepo() {
		return repo;
	}

	@Override
	public Class<Post> getC() {
		return Post.class;
	}

	@Override
	public void lookupForeignDependencies(Post t) throws ExecutionException {
		// Long parentId = t.getParentId();
		// if (parentId != null) {
		// Post parentPost = repo.findByParentIdAndSite(parentId, siteName);
		// t.setParentPost(parentPost);
		// }

		Long lastEditorUserId = t.getLastEditorUserId();
		if (lastEditorUserId != null) {
			User u = userCache.get(lastEditorUserId);
			t.setLastEditorUser(u);
		}

		// Long acceptedAnswerId = t.getAcceptedAnswerId();
		// if (acceptedAnswerId != null) {
		// Post p = repo.findByPostIdAndSite(acceptedAnswerId, siteName);
		// t.setAcceptedAnswer(p);
		// }

		Long ownerUserId = t.getOwnerUserId();
		if (ownerUserId != null) {
			User u = userCache.get(ownerUserId);
			t.setOwnerUser(u);
		}

		Long postTypeId = t.getPostTypeId();
		if (postTypeId != null) {
			PostType p = postTypeCache.get(postTypeId);
			t.setPostType(p);
		}
	}
}
