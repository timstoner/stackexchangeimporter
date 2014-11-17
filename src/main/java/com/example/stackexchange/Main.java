package com.example.stackexchange;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	private static Logger LOG = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		LOG.info("Stack Exchange Importer");

		Option dirOption = new Option("d", "dir", true,
				"folder location of stack exchange data");
		Option persistOption = new Option("p", "persist", false,
				"indicates whether to persist data to database");
		Option solrOption = new Option("i", "index", false,
				"indicates whether to index data");

		Options options = new Options();
		options.addOption(dirOption);
		options.addOption(persistOption);
		options.addOption(solrOption);

		CommandLineParser parser = new BasicParser();
		CommandLine cmd = null;

		try {
			LOG.info("Parsing Command Line Options");
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			LOG.error("Error parsing options", e);
			System.exit(-1);
		}

		LOG.info("Loading Application Context");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		StackExchangeImporter importer = context.getBean("importer",
				StackExchangeImporter.class);

		boolean index = cmd.hasOption("i");
		LOG.info("Index: " + index);
		importer.setIndex(index);

		boolean persist = cmd.hasOption("p");
		LOG.info("Persist: " + persist);
		importer.setPersist(persist);

		DateTime start = new DateTime();

		boolean directory = cmd.hasOption("d");

		if (directory) {
			String dir = cmd.getOptionValue("d");
			LOG.info("Stack Exchange Directory: " + dir);
			importer.importDirectory(dir);
		}

		DateTime end = new DateTime();

		Interval interval = new Interval(start, end);
		LOG.info("Total Time: " + interval.toDuration().getStandardSeconds());

		LOG.info("All done.");
	}
}
