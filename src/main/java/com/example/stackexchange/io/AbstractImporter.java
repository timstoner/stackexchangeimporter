package com.example.stackexchange.io;

import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.pool2.PooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stackexchange.entity.BaseEntity;
import com.example.stackexchange.util.UnmarshallerPooledObjectFactory;

public abstract class AbstractImporter<T extends BaseEntity, R extends JpaRepository<T, Long>> {

	private static Logger LOG = LoggerFactory.getLogger(AbstractImporter.class);

	@Value("${dir}")
	private String dir;

	private UnmarshallerPooledObjectFactory factory;

	public AbstractImporter() {
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(getC());
			factory = new UnmarshallerPooledObjectFactory(jc);
		} catch (JAXBException e) {
			getLogger().warn("Problem creating JAXB context", e);
		}
	}

	public void execute() {
		LOG.info("Importing {} File {}", getFileName(), dir);
		Path path = Paths.get(dir, getFileName());

		String siteName = getSiteName(path);

		if (Files.exists(path)) {
			// woah Java 8 Streams API
			try (Stream<String> lines = Files.lines(path)) {
				lines.filter(s -> s.startsWith("  <row")).forEach(s -> parseRow(s, siteName));
			} catch (Exception e) {
				LOG.warn("Problem reading file " + dir, e);
			}
		} else {
			LOG.warn("No {} File found", getFileName());
		}
	}

	public void parseRow(String line, String siteName) {
		try {
			LOG.debug(line);
			PooledObject<Unmarshaller> pooledObject = factory.makeObject();
			Unmarshaller unmarshaller = pooledObject.getObject();
			StreamSource ss = new StreamSource(new StringReader(line));
			JAXBElement<T> je = unmarshaller.unmarshal(ss, getC());

			T value = je.getValue();
			value.setSite(siteName);

			lookupForeignDependencies(value);

			LOG.debug(value.toString());
			getRepo().save(value);

			factory.destroyObject(pooledObject);
		} catch (Exception e) {
			LOG.warn("Problem parsing and persisting object", e);
		}
	}

	public abstract Logger getLogger();

	public abstract String getFileName();

	public abstract R getRepo();

	public abstract Class<T> getC();

	public abstract void lookupForeignDependencies(T t);

	protected String getSiteName(Path path) {
		Path parent = path.getParent();

		Path name = parent.getName(parent.getNameCount() - 1);
		return name.toString();
	}
}
