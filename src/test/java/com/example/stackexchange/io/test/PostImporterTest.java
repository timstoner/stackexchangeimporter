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
import com.example.stackexchange.entity.Post;
import com.example.stackexchange.io.PostImporter;
import com.example.stackexchange.repo.PostRepository;
import com.example.stackexchange.repo.PostTypeRepository;
import com.example.stackexchange.repo.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest({ "dir=classpath:/src/test/resources/sample" })
public class PostImporterTest {

	private static final Logger LOG = LoggerFactory.getLogger(BadgeImporterTest.class);

	@Value("${dir}")
	private String dir;

	@Mock
	private PostRepository postRepository;

	@Mock
	private PostTypeRepository postTypeRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private PostImporter postImporter;

	@Test
	public void importPostFileTest() throws Exception {
		LOG.info("Running importPostFileTest");

		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(postImporter, "dir", "src/test/resources/sample");

		postImporter.execute();

		ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);

		when(postRepository.save(argument.capture())).thenReturn(null);
		verify(postRepository, times(22)).save(argument.capture());
		List<Post> posts = argument.getAllValues();
		assertEquals(22, posts.size());

	}

}
