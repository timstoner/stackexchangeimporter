package com.example.stackexchange.io;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.LinkType;
import com.example.stackexchange.entity.Post;
import com.example.stackexchange.entity.PostLink;
import com.example.stackexchange.repo.PostLinkRepository;
import com.google.common.cache.LoadingCache;

@Component
public class PostLinkImporter extends AbstractImporter<PostLink, PostLinkRepository> {

	private static Logger LOG = LoggerFactory.getLogger(PostLinkImporter.class);

	@Autowired
	private PostLinkRepository repo;

	@Autowired
	private LoadingCache<Long, Post> postCache;

	@Autowired
	private LoadingCache<Long, LinkType> linkTypeCache;

	@Override
	public Logger getLogger() {
		return LOG;
	}

	@Override
	public String getFileName() {
		return "PostLinks.xml";
	}

	@Override
	public PostLinkRepository getRepo() {
		return repo;
	}

	@Override
	public Class<PostLink> getC() {
		return PostLink.class;
	}

	@Override
	public void lookupForeignDependencies(PostLink t) {
		Long postId = t.getPostId();
		if (postId != null) {
			Post post;
			try {
				post = postCache.get(postId);
				t.setPost(post);
			} catch (ExecutionException e) {
				LOG.warn("Could not get Post from Cache", e);
			}
		}

		Long relatedPostId = t.getRelatedPostId();
		if (relatedPostId != null) {
			Post relatedPost;
			try {
				relatedPost = postCache.get(relatedPostId);
				t.setRelatedPost(relatedPost);
			} catch (ExecutionException e) {
				LOG.warn("Could not get Post from Cache", e);
			}
		}

		Long linkTypeId = t.getLinkTypeId();
		if (linkTypeId != null) {
			LinkType linkType;
			try {
				linkType = linkTypeCache.get(linkTypeId);
				t.setLinkType(linkType);
			} catch (ExecutionException e) {
				LOG.warn("Could not get LinkType from Cache", e);
			}
		}
	}

}
