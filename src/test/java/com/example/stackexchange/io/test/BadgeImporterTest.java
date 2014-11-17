package com.example.stackexchange.io.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.stackexchange.entity.Badge;
import com.example.stackexchange.io.BadgeImporter;
import com.example.stackexchange.repo.BadgeRepository;

public class BadgeImporterTest {

	private static final Logger LOG = LoggerFactory.getLogger(BadgeImporterTest.class);

	@Mock
	private BadgeRepository badgeRepository;

	@InjectMocks
	private BadgeImporter badgeImporter;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		Badge.setSiteName("MySite");
	}

	@Test
	public void importBadgeFileTest() throws Exception {
		LOG.info("Running importBadgeFileTest");

		badgeImporter.importBadgeFile("src/test/resources/sample/Badges.xml");

		ArgumentCaptor<Badge> argument = ArgumentCaptor.forClass(Badge.class);

		when(badgeRepository.save(argument.capture())).thenReturn(null);
		verify(badgeRepository, times(18)).save(argument.capture());
		List<Badge> badges = argument.getAllValues();
		assertEquals(18, badges.size());

	}
}
