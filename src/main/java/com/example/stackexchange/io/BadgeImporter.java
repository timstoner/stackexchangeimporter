package com.example.stackexchange.io;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.Badge;
import com.example.stackexchange.entity.User;
import com.example.stackexchange.repo.BadgeRepository;
import com.google.common.cache.LoadingCache;

@Component
public class BadgeImporter extends AbstractImporter<Badge, BadgeRepository> {

	private static Logger LOG = LoggerFactory.getLogger(BadgeImporter.class);

	@Autowired
	private BadgeRepository repo;

	@Autowired
	private LoadingCache<Long, User> userCache;

	@Override
	public Logger getLogger() {
		return LOG;
	}

	@Override
	public String getFileName() {
		return "Badges.xml";
	}

	@Override
	public BadgeRepository getRepo() {
		return repo;
	}

	@Override
	public Class<Badge> getC() {
		return Badge.class;
	}

	@Override
	public void lookupForeignDependencies(Badge badge) throws ExecutionException {
		Long userId = badge.getUserId();

		if (userId != null) {
			User user;

			user = userCache.get(userId);
			badge.setUser(user);

		}
	}
}
