package com.example.stackexchange.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stackexchange.entity.PostHistoryType;
import com.example.stackexchange.repo.PostHistoryTypeRepository;
import com.google.common.cache.CacheLoader;

@Component
public class PostHistoryTypeCacheLoader extends CacheLoader<Long, PostHistoryType> {

	@Autowired
	private PostHistoryTypeRepository repo;

	@Override
	public PostHistoryType load(Long key) throws Exception {
		return repo.findOne(key);
	}
}
