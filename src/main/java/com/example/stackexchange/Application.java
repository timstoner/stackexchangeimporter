package com.example.stackexchange;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.stackexchange.io.BadgeImporter;
import com.example.stackexchange.io.CommentImporter;
import com.example.stackexchange.io.PostHistoryImporter;
import com.example.stackexchange.io.PostImporter;
import com.example.stackexchange.io.PostLinkImporter;
import com.example.stackexchange.io.StaticImporter;
import com.example.stackexchange.io.TagImporter;
import com.example.stackexchange.io.UserImporter;
import com.example.stackexchange.io.VoteImporter;

@SpringBootApplication
public class Application {
	private static Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOG.info("Stack Exchange Importer");

		DateTime start = new DateTime();

		SpringApplication app = new SpringApplication(Application.class);
		app.setWebEnvironment(false);
		ConfigurableApplicationContext ctx = app.run(args);

		StaticImporter staticImporter = ctx.getBean(StaticImporter.class);
		LOG.info("Running static importer");
		staticImporter.execute();

		UserImporter userImporter = ctx.getBean(UserImporter.class);
		LOG.info("Running Users Importer");
		userImporter.execute();

		BadgeImporter badgeImporter = ctx.getBean(BadgeImporter.class);
		LOG.info("Running Badges Importer");
		badgeImporter.execute();

		PostImporter postImporter = ctx.getBean(PostImporter.class);
		LOG.info("Running Posts Importer");
		postImporter.execute();

		TagImporter tagImporter = ctx.getBean(TagImporter.class);
		LOG.info("Running Tags Importer");
		tagImporter.execute();

		PostHistoryImporter postHistoryImporter = ctx.getBean(PostHistoryImporter.class);
		LOG.info("Running PostHistorys Importer");
		postHistoryImporter.execute();

		CommentImporter commentImporter = ctx.getBean(CommentImporter.class);
		LOG.info("Running Comments Importer");
		commentImporter.execute();

		VoteImporter voteImporter = ctx.getBean(VoteImporter.class);
		LOG.info("Running Votes Importer");
		voteImporter.execute();

		PostLinkImporter postLinkImporter = ctx.getBean(PostLinkImporter.class);
		LOG.info("Running PostLinks Importer");
		postLinkImporter.execute();

		DateTime end = new DateTime();

		Interval interval = new Interval(start, end);
		LOG.info("Total Time: {} seconds.", interval.toDuration().getStandardSeconds());

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		LOG.info("All done.");
	}
}
