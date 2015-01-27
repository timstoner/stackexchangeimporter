package com.example.stackexchange.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.Post;
import com.example.stackexchange.entity.PostHistory;
import com.example.stackexchange.entity.PostHistoryType;
import com.example.stackexchange.entity.User;
import com.example.stackexchange.repo.PostHistoryTypeRepository;
import com.example.stackexchange.repo.PostRepository;
import com.example.stackexchange.repo.UserRepository;

@Component
public class PostHistoryImporter extends AbstractImporter<PostHistory, PostHistoryRepository> {

	private static Logger LOG = LoggerFactory.getLogger(PostHistoryImporter.class);

	@Autowired
	private PostHistoryRepository repo;

	@Autowired
	private PostHistoryTypeRepository postHistoryTypeRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PostRepository postRepo;

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
		Long id = t.getPostHistoryId();
		if (id != null) {
			PostHistoryType type = postHistoryTypeRepo.findOne(id);
			t.setPostHistoryType(type);
		}

		id = t.getUserId();
		if (id != null) {
			User user = userRepo.findByUserIdAndSite(id, siteName);
			t.setUser(user);
		}

		id = t.getPostId();
		if (id != null) {
			Post post = postRepo.findByPostIdAndSite(id, siteName);
			t.setPost(post);
		}
	}

}
