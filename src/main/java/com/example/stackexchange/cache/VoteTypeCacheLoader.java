package com.example.stackexchange.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.VoteType;
import com.example.stackexchange.repo.VoteTypeRepository;
import com.google.common.cache.CacheLoader;

@Component
public class VoteTypeCacheLoader extends CacheLoader<Long, VoteType> {

	@Autowired
	private VoteTypeRepository repo;

	@Override
	public VoteType load(Long key) throws Exception {
		return repo.findOne(key);
	}

}
