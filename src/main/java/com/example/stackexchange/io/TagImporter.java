package com.example.stackexchange.io;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.Post;
import com.example.stackexchange.entity.Tag;
import com.example.stackexchange.repo.TagRepository;
import com.google.common.cache.LoadingCache;

@Component
public class TagImporter extends AbstractImporter<Tag, TagRepository> {

	private static Logger LOG = LoggerFactory.getLogger(TagImporter.class);

	@Autowired
	private TagRepository repo;

	@Autowired
	private LoadingCache<Long, Post> postCache;

	@Override
	public Logger getLogger() {
		return LOG;
	}

	@Override
	public String getFileName() {
		return "Tags.xml";
	}

	@Override
	public TagRepository getRepo() {
		return repo;
	}

	@Override
	public Class<Tag> getC() {
		return Tag.class;
	}

	@Override
	public void lookupForeignDependencies(Tag t) throws ExecutionException {

		Long excerptPostId = t.getExcerptPostId();
		if (excerptPostId != null) {
			Post post = postCache.get(excerptPostId);
			t.setExcerptPost(post);
		}

		Long wikiPostId = t.getWikiPostId();
		if (wikiPostId != null) {
			Post post = postCache.get(wikiPostId);
			t.setWikiPost(post);
		}
	}

}
