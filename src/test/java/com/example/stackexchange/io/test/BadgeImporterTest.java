package com.example.stackexchange.io.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.stackexchange.Application;
import com.example.stackexchange.entity.Badge;
import com.example.stackexchange.io.BadgeImporter;
import com.example.stackexchange.repo.BadgeRepository;
import com.example.stackexchange.repo.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest({ "dir=classpath:/src/test/resources/sample" })
public class BadgeImporterTest {

	private static final Logger LOG = LoggerFactory.getLogger(BadgeImporterTest.class);

	@Value("${dir}")
	private String dir;

	@Mock
	private BadgeRepository badgeRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private BadgeImporter badgeImporter;

	@Test
	public void importBadgeFileTest() throws Exception {
		LOG.info("Running importBadgeFileTest");

		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(badgeImporter, "dir", "src/test/resources/sample");

		badgeImporter.execute();

		ArgumentCaptor<Badge> argument = ArgumentCaptor.forClass(Badge.class);

		when(badgeRepository.save(argument.capture())).thenReturn(null);
		verify(badgeRepository, times(18)).save(argument.capture());
		List<Badge> badges = argument.getAllValues();
		assertEquals(18, badges.size());

	}
}
