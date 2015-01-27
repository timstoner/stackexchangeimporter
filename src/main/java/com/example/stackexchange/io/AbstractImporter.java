package com.example.stackexchange.io;

import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.pool2.PooledObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.AbstractEntity;
import com.example.stackexchange.repo.SiteRepository;
import com.example.stackexchange.util.SiteUtils;
import com.example.stackexchange.util.UnmarshallerPooledObjectFactory;

public abstract class AbstractImporter<T extends AbstractEntity, R extends JpaRepository<T, String>> {

	private static Logger LOG;

	@Value("${dir}")
	private String dir;

	private UnmarshallerPooledObjectFactory factory;

	@Autowired
	private SiteRepository siteRepository;

	protected String siteName;

	private long counter = 0;

	// private Site site;

	public void execute() {
		LOG = getLogger();

		JAXBContext jc;
		Class<T> cl = getC();
		try {
			jc = JAXBContext.newInstance(cl);
			factory = new UnmarshallerPooledObjectFactory(jc);
		} catch (JAXBException e) {
			getLogger().warn("Problem creating JAXB context", e);
		}

		LOG.info("Importing {}, Folder {}", getFileName(), dir);
		Path path = Paths.get(dir, getFileName());

		siteName = SiteUtils.getSiteName(dir);
		// cacheSite(siteName);

		if (Files.exists(path)) {
			// woah Java 8 Streams API, with lambdas!
			try (Stream<String> lines = Files.lines(path)) {
				lines.filter(s -> s.startsWith("  <row")).forEach(s -> parseRow(s));
			} catch (Exception e) {
				LOG.warn("Problem reading file " + getFileName(), e);
			}
		} else {
			LOG.warn("No {} File found", getFileName());
		}
	}

	public void parseRow(String line) {
		try {
			LOG.debug(line);
			PooledObject<Unmarshaller> pooledObject = factory.makeObject();
			Unmarshaller unmarshaller = pooledObject.getObject();
			StreamSource ss = new StreamSource(new StringReader(line));
			JAXBElement<T> je = unmarshaller.unmarshal(ss, getC());

			T value = je.getValue();
			value.setSite(siteName);

			lookupForeignDependencies(value);

			counter++;
			getRepo().save(value);

			if (counter % 1000 == 0) {
				LOG.info("Counter: {}", counter);
			}

			factory.destroyObject(pooledObject);
		} catch (Exception e) {
			LOG.warn("Problem parsing and persisting object", e);
		}
	}

	public abstract Logger getLogger();

	public abstract String getFileName();

	public abstract R getRepo();

	public abstract Class<T> getC();

	public abstract void lookupForeignDependencies(T t) throws ExecutionException;

	// private void cacheSite(String siteName) {
	// Site temp = siteRepository.findByName(siteName);
	// if (temp == null) {
	// temp = new Site();
	// temp.setName(siteName);
	//
	// temp = siteRepository.saveAndFlush(temp);
	// }
	// site = temp;
	// }
}
