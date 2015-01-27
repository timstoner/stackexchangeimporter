package com.example.stackexchange.io;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.Post;
import com.example.stackexchange.entity.Vote;
import com.example.stackexchange.entity.VoteType;
import com.example.stackexchange.repo.VoteRepository;
import com.example.stackexchange.repo.VoteTypeRepository;
import com.google.common.cache.LoadingCache;

@Component
public class VoteImporter extends AbstractImporter<Vote, VoteRepository> {
	private static Logger LOG = LoggerFactory.getLogger(VoteImporter.class);

	@Autowired
	private VoteRepository repo;

	@Autowired
	private VoteTypeRepository voteTypeRepository;

	@Autowired
	private LoadingCache<Long, Post> postCache;

	@Autowired
	private LoadingCache<Long, VoteType> voteTypeCache;

	@Override
	public Logger getLogger() {
		return LOG;
	}

	@Override
	public String getFileName() {
		return "Votes.xml";
	}

	@Override
	public VoteRepository getRepo() {
		return repo;
	}

	@Override
	public Class<Vote> getC() {
		return Vote.class;
	}

	@Override
	public void lookupForeignDependencies(Vote t) throws ExecutionException {
		Long postId = t.getPostId();
		if (postId != null) {
			Post post = postCache.get(postId);
			t.setPost(post);
		}

		Long voteTypeId = t.getVoteTypeId();
		if (voteTypeId != null) {
			VoteType voteType = voteTypeCache.get(voteTypeId);
			t.setVoteType(voteType);
		}
	}
}
