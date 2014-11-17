package com.example.stackexchange;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.example.stackexchange.handler.BadgeHandler;
import com.example.stackexchange.handler.BaseHandler;
import com.example.stackexchange.handler.CommentHandler;
import com.example.stackexchange.handler.PostHandler;
import com.example.stackexchange.handler.PostHistoryHandler;
import com.example.stackexchange.handler.PostLinksHandler;
import com.example.stackexchange.handler.RowEvent;
import com.example.stackexchange.handler.RowEventListener;
import com.example.stackexchange.handler.UserHandler;
import com.example.stackexchange.handler.VotesHandler;

public class StackExchangeImporter {
	private static Logger LOG = Logger.getLogger(StackExchangeImporter.class);
	
	

	private String siteName;

	private int count;

	private boolean index;

	private boolean persist;

	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public SessionFactory getSessionFactory() {
		return factory;
	}

	public void setSolrServer(SolrServer server) {
		this.solrServer = server;
	}

	public SolrServer getSolrServer() {
		return solrServer;
	}

	public void setIndex(boolean index) {
		this.index = index;
	}

	public void setPersist(boolean persist) {
		this.persist = persist;
	}

	public void importDirectory(String directory) {
		LOG.info("importing directory: " + directory);

		File folder = new File(directory);
		File fa = FileUtils.getFile(directory);

		String[] ext = { "xml" };
		siteName = folder.getName();

		LOG.info("Site Name: " + siteName);

		Collection<File> files = FileUtils.listFiles(fa, ext, false);

		for (File f : files) {
			importFile(f.getPath());
		}
	}

	public void importFile(String file) {
		LOG.info("Importing File: " + file);

		String fileName;

		File f = new File(file);
		String path = f.getPath();

		// chop off .xml from file name
		fileName = FilenameUtils.removeExtension(f.getName());

		switch (fileName) {
		case "Badges":
			readBadgeFile(path);
			break;
		case "Comments":
			readCommentsFile(path);
			break;
		case "PostHistory":
			readPostHistoryFile(path);
			break;
		case "PostLinks":
			readPostLinksFile(path);
			break;
		case "Posts":
			readPostFile(path);
			break;
		case "Users":
			readUsersFile(path);
			break;
		case "Votes":
			readVotesFile(path);
			break;
		}
	}

	public void readBadgeFile(String path) {
		BadgeHandler handler = new BadgeHandler();
		readFile(handler, path);
	}

	private void readCommentsFile(String path) {
		CommentHandler handler = new CommentHandler();
		readFile(handler, path);
	}

	private void readPostHistoryFile(String path) {
		PostHistoryHandler handler = new PostHistoryHandler();
		readFile(handler, path);
	}

	private void readPostLinksFile(String path) {
		PostLinksHandler handler = new PostLinksHandler();
		readFile(handler, path);
	}

	public void readPostFile(String path) {
		PostHandler handler = new PostHandler();
		readFile(handler, path);
	}

	private void readUsersFile(String path) {
		UserHandler handler = new UserHandler();
		readFile(handler, path);
	}

	private void readVotesFile(String path) {
		VotesHandler handler = new VotesHandler();
		readFile(handler, path);
	}

	private void readFile(BaseHandler handler, String path) {
		LOG.info("Reading file: " + path);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser;

		if (index) {
			handler.setSolrServer(solrServer);
		}

		if (persist) {
			handler.setSessionFactory(factory);
		}

		File f = new File(path);
		String fileName = FilenameUtils.removeExtension(f.getName());

		handler.setSiteName(siteName);
		handler.setFileName(fileName);

		try {
			saxParser = spf.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();

			xmlReader.setContentHandler(handler);
			xmlReader.parse(path);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LOG.error("Error building sax parser", e);
		}
	}

	private void persist(Object o) {
		count++;
		session.persist(o);

		if (count % 1000 == 0) {
			transaction.commit();
			transaction = session.beginTransaction();
		}

		if (count % 1000 == 0) {
			LOG.info("Number of rows imported " + count);
		}
	}

	private void index(Object o) {
		count++;

		try {
			solrServer.addBean(o);
			// if (count % 20 == 0) {
			LOG.info("Row Count: " + count);
			solrServer.commit();
			// }
		} catch (IOException | SolrServerException e) {
			LOG.error("Problem adding bean to solr", e);
		}
	}

	private class PersistRowEventListener implements RowEventListener {
		@Override
		public void onRowEvent(RowEvent event) {
			persist(event.Row);
		}
	}

	private class IndexRowEventListener implements RowEventListener {
		@Override
		public void onRowEvent(RowEvent event) {
			index(event.Row);
		}
	}

}
