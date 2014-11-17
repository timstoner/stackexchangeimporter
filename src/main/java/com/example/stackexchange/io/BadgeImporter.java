package com.example.stackexchange.io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.Badge;
import com.example.stackexchange.repo.BadgeRepository;
import com.example.stackexchange.util.UnmarshallerPooledObjectFactory;

@Component
public class BadgeImporter {

	private static Logger LOG = LoggerFactory.getLogger(BadgeImporter.class);

	@Autowired
	private BadgeRepository repo;

	private PooledObjectFactory<Unmarshaller> unmarshallerFactory;

	public BadgeImporter() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Badge.class);
		unmarshallerFactory = new UnmarshallerPooledObjectFactory(jc);
	}

	public void importBadgeFile(String path) throws Exception {
		LOG.info("Importing Badge File {}", path);
		XMLInputFactory xif = XMLInputFactory.newFactory();
		StreamSource xml = new StreamSource(path);
		XMLStreamReader xsr = xif.createXMLStreamReader(xml);

		xsr.nextTag();
		while (!xsr.getLocalName().equals("row")) {
			xsr.nextTag();
		}

		while (xsr.getLocalName().equals("row")) {
			PooledObject<Unmarshaller> pooledObject = unmarshallerFactory.makeObject();

			Unmarshaller unmarshaller = pooledObject.getObject();
			JAXBElement<Badge> jb = unmarshaller.unmarshal(xsr, Badge.class);

			Badge badge = jb.getValue();
			LOG.debug("Badge: {}", badge);

			unmarshallerFactory.destroyObject(pooledObject);
			repo.save(badge);

			xsr.nextTag();
		}

		xsr.close();
	}
}
