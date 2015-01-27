package com.example.stackexchange.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.LinkType;
import com.example.stackexchange.repo.LinkTypeRepository;
import com.google.common.cache.CacheLoader;

@Component
public class LinkTypeCacheLoader extends CacheLoader<Long, LinkType> {

	@Autowired
	private LinkTypeRepository repo;

	@Override
	public LinkType load(Long key) throws Exception {
		return repo.findOne(key);
	}

}
