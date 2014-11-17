package com.example.stackexchange.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class UnmarshallerPooledObjectFactory extends BasePooledObjectFactory<Unmarshaller> {

	private JAXBContext jc;

	public UnmarshallerPooledObjectFactory(JAXBContext jc) {
		this.jc = jc;
	}

	@Override
	public Unmarshaller create() throws Exception {
		return jc.createUnmarshaller();
	}

	@Override
	public PooledObject<Unmarshaller> wrap(Unmarshaller obj) {
		return new DefaultPooledObject<Unmarshaller>(obj);
	}

}
