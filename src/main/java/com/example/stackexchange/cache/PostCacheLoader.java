package com.example.stackexchange.cache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.Post;
import com.example.stackexchange.repo.PostRepository;
import com.example.stackexchange.util.SiteUtils;
import com.google.common.cache.CacheLoader;

@Component
public class PostCacheLoader extends CacheLoader<Long, Post> {

	@Value("${dir}")
	private String dir;

	private String siteName;

	@Autowired
	private PostRepository repo;

	@PostConstruct
	public void initialize() {
		siteName = SiteUtils.getSiteName(dir);
	}

	@Override
	public Post load(Long key) throws Exception {
		return repo.findByPostIdAndSite(key, siteName);
	}

}
