package com.example.stackexchange.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.PostType;
import com.example.stackexchange.repo.PostTypeRepository;
import com.google.common.cache.CacheLoader;

@Component
public class PostTypeCacheLoader extends CacheLoader<Long, PostType> {

	@Autowired
	private PostTypeRepository repo;

	@Override
	public PostType load(Long key) throws Exception {
		return repo.findOne(key);
	}

}
