package com.example.stackexchange.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class BaseHandler extends DefaultHandler {

	protected static Logger LOG = Logger.getLogger(BaseHandler.class);

	protected SolrServer solrServer;

	protected SessionFactory sessionFactory;

	private List<RowEventListener> listeners;

	private List<SolrInputDocument> documents;

	private Session session;

	private Transaction transaction;

	protected String siteName;

	protected String fileName;

	private int indexCount;

	public BaseHandler() {
		indexCount = 0;
		listeners = new ArrayList<>();
		documents = new ArrayList<>();
	}

	public void addRowEventListener(RowEventListener l) {
		listeners.add(l);
	}

	public void removeRowEventListener(RowEventListener l) {
		listeners.remove(l);
	}

	public void setSolrServer(SolrServer server) {
		this.solrServer = server;
	}

	public void setSessionFactory(SessionFactory factory) {
		this.sessionFactory = factory;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void startDocument() throws SAXException {
		if (sessionFactory != null) {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
		}
	}

	@Override
	public void endDocument() throws SAXException {
		commitIndex();
		commitTransaction();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("row")) {
			handleRow(attributes);
		}
	}

	public abstract void handleRow(Attributes attributes);

	protected Integer getId(Attributes a) {
		return getInt(a, "Id");
	}

	protected Integer getUserId(Attributes a) {
		return getInt(a, "UserId");
	}

	protected Integer getPostId(Attributes a) {
		return getInt(a, "PostId");
	}

	protected String getName(Attributes a) {
		return getString(a, "Name");
	}

	protected String getCreationDate(Attributes a) {
		return getDate(a, "CreationDate");
	}

	protected String getText(Attributes a) {
		return getString(a, "Text");
	}

	protected String getUserDisplayName(Attributes a) {
		return getString(a, "UserDisplayName");
	}

	protected String getCommment(Attributes a) {
		return getString(a, "Comment");
	}

	protected String getString(Attributes a, String name) {
		return a.getValue(name);
	}

	protected String getDate(Attributes a, String string) {
		String date = getString(a, string);
		if (date != null) {
			date = date.concat("Z");
		}
		return date;
	}

	protected Integer getInt(Attributes attributes, String name) {
		String value = attributes.getValue(name);
		Integer result = null;
		if (value != null) {
			result = Integer.valueOf(value);
		}
		return result;
	}

	protected String buildId(Integer id) {
		StringBuilder builder = new StringBuilder(siteName);

		builder = builder.append(":").append(fileName).append(":").append(id);

		return builder.toString();
	}

	protected void indexRow(SolrInputDocument doc) {
		if (solrServer != null) {
			
			doc.setField("siteName_s", siteName);

			documents.add(doc);
			indexCount++;

//			if (indexCount % 1000 == 0) {
				commitIndex();
//			}
		}
	}

	protected void persistRow(Object o) {
		if (session != null) {
			session.persist(o);
			commitTransaction();

		}
	}

	protected void fireOnRowEvent(Object o) {
		RowEvent event = new RowEvent();
		event.Row = o;
		for (RowEventListener l : listeners) {
			l.onRowEvent(event);
		}
	}

	private void commitTransaction() {
		if (transaction != null) {
			transaction.commit();
			transaction = session.beginTransaction();
		}
	}

	private void commitIndex() {
		if (documents.size() != 0) {
			try {
				solrServer.add(documents);
				solrServer.commit();
			} catch (SolrServerException | IOException e) {
				LOG.error("Problem indexing documents", e);
			}

			LOG.info("Number of documents indexed : " + indexCount);

			documents.clear();
		}
	}
}
