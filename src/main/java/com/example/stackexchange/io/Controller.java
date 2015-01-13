package com.example.stackexchange.io;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Controller {
	private static Logger LOG = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private UserImporter userImporter;

	@Autowired
	private PostImporter postImporter;

	@Autowired
	private BadgeImporter badgeImporter;

	@Autowired
	private CommentImporter commentImporter;

	@PostConstruct
	public void execute() throws IOException {
		LOG.info("Starting import");

		LOG.info("Importing Users");
		userImporter.execute();

		LOG.info("Importing Badges");
		badgeImporter.execute();

		LOG.info("Importing Posts");
		postImporter.execute();

		LOG.info("Importing Comments");
		commentImporter.execute();

		LOG.info("Blocking");
		System.in.read();
	}
}
