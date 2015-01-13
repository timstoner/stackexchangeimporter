package com.example.stackexchange.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.User;
import com.example.stackexchange.repo.UserRepository;

@Component
public class UserImporter extends AbstractImporter<User, UserRepository> {

	private static Logger LOG = LoggerFactory.getLogger(UserImporter.class);

	@Autowired
	private UserRepository repo;

	@Override
	public Logger getLogger() {
		return LOG;
	}

	@Override
	public String getFileName() {
		return "Users.xml";
	}

	@Override
	public UserRepository getRepo() {
		return repo;
	}

	@Override
	public Class<User> getC() {
		return User.class;
	}

	@Override
	public void lookupForeignDependencies(User t) {

	}
}
