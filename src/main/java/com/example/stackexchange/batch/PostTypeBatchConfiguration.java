package com.example.stackexchange.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.stackexchange.entity.PostType;

@Configuration
public class PostTypeBatchConfiguration {

	@Bean
	public ItemReader<PostType> postTypeReader() {
		FlatFileItemReader<PostType> reader = new FlatFileItemReader<PostType>();
		reader.setResource(new ClassPathResource("PostTypes.csv"));
		reader.setLineMapper(new DefaultLineMapper<PostType>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "id", "name" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<PostType>() {
					{
						setTargetType(PostType.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public ItemWriter<PostType> postTypeWriter(DataSource dataSource) {
		JdbcBatchItemWriter<PostType> writer = new JdbcBatchItemWriter<PostType>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<PostType>());
		writer.setSql("INSERT INTO posttypes (id, name) VALUES (:id, :name)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public Job importPostTypes(JobBuilderFactory jobs, Step s1) {
		return jobs.get("importPostTypes").incrementer(new RunIdIncrementer()).flow(s1).end().build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<PostType> reader, ItemWriter<PostType> writer) {
		return stepBuilderFactory.get("step1").<PostType, PostType> chunk(10).reader(reader).writer(writer).build();
	}

}
