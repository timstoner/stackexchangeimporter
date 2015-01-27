package com.example.stackexchange.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.stackexchange.entity.LinkType;
import com.example.stackexchange.entity.Post;
import com.example.stackexchange.entity.PostHistoryType;
import com.example.stackexchange.entity.PostType;
import com.example.stackexchange.entity.User;
import com.example.stackexchange.entity.VoteType;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

@Configuration
public class CacheConfig {

	private String cacheSpec = "maximumSize=1000";

	@Bean
	public LoadingCache<Long, User> userCache(UserCacheLoader loader) {
		return CacheBuilder.from(cacheSpec).build(loader);
	}

	@Bean
	public LoadingCache<Long, Post> postCache(PostCacheLoader loader) {
		return CacheBuilder.from(cacheSpec).build(loader);
	}

	@Bean
	public LoadingCache<Long, PostType> postTypeCache(PostTypeCacheLoader loader) {
		return CacheBuilder.from(cacheSpec).build(loader);
	}

	@Bean
	public LoadingCache<Long, PostHistoryType> postHistoryTypeCache(PostHistoryTypeCacheLoader loader) {
		return CacheBuilder.from(cacheSpec).build(loader);
	}

	@Bean
	public LoadingCache<Long, LinkType> linkTypeCache(LinkTypeCacheLoader loader) {
		return CacheBuilder.from(cacheSpec).build(loader);
	}

	@Bean
	public LoadingCache<Long, VoteType> voteTypeCache(VoteTypeCacheLoader loader) {
		return CacheBuilder.from(cacheSpec).build(loader);
	}
}
