package com.example.stackexchange;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.stackexchange.io.UserImporter;

@SpringBootApplication
public class Application {
	private static Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOG.info("Stack Exchange Importer");

		DateTime start = new DateTime();

		SpringApplication app = new SpringApplication(Application.class);
		app.setWebEnvironment(false);
		ConfigurableApplicationContext ctx = app.run(args);
		UserImporter userImporter = ctx.getBean(UserImporter.class);
		userImporter.execute();

		DateTime end = new DateTime();

		Interval interval = new Interval(start, end);
		LOG.info("Total Time: " + interval.toDuration().getStandardSeconds());

		LOG.info("All done.");
	}
}
