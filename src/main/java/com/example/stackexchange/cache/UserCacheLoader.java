package com.example.stackexchange.cache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.User;
import com.example.stackexchange.repo.UserRepository;
import com.example.stackexchange.util.SiteUtils;
import com.google.common.cache.CacheLoader;

@Component
public class UserCacheLoader extends CacheLoader<Long, User> {

	@Value("${dir}")
	private String dir;

	private String siteName;

	@Autowired
	private UserRepository repo;

	@PostConstruct
	public void initialize() {
		siteName = SiteUtils.getSiteName(dir);
	}

	@Override
	public User load(Long key) throws Exception {
		return repo.findByUserIdAndSite(key, siteName);
	}
}
