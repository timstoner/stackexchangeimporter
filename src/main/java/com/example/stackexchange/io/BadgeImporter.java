package com.example.stackexchange.io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.Badge;
import com.example.stackexchange.repo.BadgeRepository;

@Component
public class BadgeImporter {

	private static Logger LOG = LoggerFactory.getLogger(BadgeImporter.class);

	@Autowired
	private BadgeRepository repo;

	public void importBadgeFile(String path) throws Exception {
		LOG.info("Importing Badge File {}", path);
		XMLInputFactory xif = XMLInputFactory.newFactory();
		StreamSource xml = new StreamSource(path);
		XMLStreamReader xsr = xif.createXMLStreamReader(xml);

		xsr.nextTag();
//		LOG.debug(xsr.getLocalName());
		while (!xsr.getLocalName().equals("row")) {
			xsr.nextTag();
		}

		while (xsr.getLocalName().equals("row")) {
			LOG.debug(xsr.getLocalName());
			JAXBContext jc = JAXBContext.newInstance(Badge.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<Badge> jb = unmarshaller.unmarshal(xsr, Badge.class);

			Badge badge = jb.getValue();
			LOG.debug("Badge: {}", badge.getId());
		}

		LOG.debug(xsr.getLocalName());
	}

}
