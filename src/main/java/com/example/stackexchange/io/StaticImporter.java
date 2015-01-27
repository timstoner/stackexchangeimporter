package com.example.stackexchange.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.LinkType;
import com.example.stackexchange.entity.PostHistoryType;
import com.example.stackexchange.entity.PostType;
import com.example.stackexchange.entity.VoteType;
import com.example.stackexchange.repo.LinkTypeRepository;
import com.example.stackexchange.repo.PostHistoryTypeRepository;
import com.example.stackexchange.repo.PostTypeRepository;
import com.example.stackexchange.repo.VoteTypeRepository;

@Component
public class StaticImporter {
	private static Logger LOG = LoggerFactory.getLogger(StaticImporter.class);

	@Autowired
	private PostTypeRepository postTypeRepository;

	@Autowired
	private PostHistoryTypeRepository postHistoryTypeRepository;

	@Autowired
	private VoteTypeRepository voteTypeRepository;

	@Autowired
	private LinkTypeRepository linkTypeRepository;

	public void execute() {
		LOG.info("Checking static repository");

		persistStaticFile("PostTypes.csv", this::persistPostType);
		persistStaticFile("PostHistoryTypes.csv", this::persistPostHistoryType);
		persistStaticFile("VoteTypes.csv", this::persistVoteType);
		persistStaticFile("LinkTypes.csv", this::persistLinkType);
	}

	public void persistStaticFile(String filename, Function<String, Void> function) {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
		List<String> lines = Collections.emptyList();

		try {
			lines = IOUtils.readLines(in);
		} catch (IOException e) {
			LOG.warn("Problem reading PostTypes.csv from classpath", e);
		}

		lines.stream().forEach(s -> function.apply(s));
	}

	public Void persistPostType(String line) {
		LOG.debug(line);
		String[] fields = line.split(",");
		long id = Long.parseLong(fields[0]);
		String name = fields[1];

		PostType pt = postTypeRepository.findOne(id);
		if (pt != null) {
			if (!pt.getName().equals(name)) {
				pt.setName(name);
				postTypeRepository.flush();
			}
		} else {
			pt = new PostType();
			pt.setId(id);
			pt.setName(name);
			postTypeRepository.save(pt);
		}

		return null;
	}

	public Void persistVoteType(String line) {
		LOG.debug(line);
		String[] fields = line.split(",");
		long id = Long.parseLong(fields[0]);
		String name = fields[1];

		VoteType pt = voteTypeRepository.findOne(id);
		if (pt != null) {
			if (!pt.getName().equals(name)) {
				pt.setName(name);
				voteTypeRepository.flush();
			}
		} else {
			pt = new VoteType();
			pt.setId(id);
			pt.setName(name);
			voteTypeRepository.save(pt);
		}

		return null;
	}

	public Void persistPostHistoryType(String line) {
		LOG.debug(line);
		String[] fields = line.split(",");
		long id = Long.parseLong(fields[0]);
		String name = fields[1];

		PostHistoryType pt = postHistoryTypeRepository.findOne(id);
		if (pt != null) {
			if (!pt.getName().equals(name)) {
				pt.setName(name);
				voteTypeRepository.flush();
			}
		} else {
			pt = new PostHistoryType();
			pt.setId(id);
			pt.setName(name);
			postHistoryTypeRepository.save(pt);
		}

		return null;
	}

	public Void persistLinkType(String line) {
		LOG.debug(line);
		String[] fields = line.split(",");
		long id = Long.parseLong(fields[0]);
		String name = fields[1];

		LinkType entity = linkTypeRepository.findOne(id);
		if (entity != null) {
			if (!entity.getName().equals(name)) {
				entity.setName(name);
				voteTypeRepository.flush();
			}
		} else {
			entity = new LinkType();
			entity.setId(id);
			entity.setName(name);
			linkTypeRepository.save(entity);
		}

		return null;
	}

}
