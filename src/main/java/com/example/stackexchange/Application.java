package com.example.stackexchange;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableBatchProcessing
public class Application {
	private static Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOG.info("Stack Exchange Importer");

		DateTime start = new DateTime();

		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);

		DateTime end = new DateTime();

		Interval interval = new Interval(start, end);
		LOG.info("Total Time: " + interval.toDuration().getStandardSeconds());

		LOG.info("All done.");
	}
}
