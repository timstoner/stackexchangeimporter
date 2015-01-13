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

import com.example.stackexchange.entity.PostHistoryType;

@Configuration
public class PostHistoryTypeBatchConfiguration {
	@Bean
	public ItemReader<PostHistoryType> postHistoryTypeReader() {
		FlatFileItemReader<PostHistoryType> reader = new FlatFileItemReader<PostHistoryType>();
		reader.setResource(new ClassPathResource("PostHistoryTypes.csv"));
		reader.setLineMapper(new DefaultLineMapper<PostHistoryType>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "id", "name" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<PostHistoryType>() {
					{
						setTargetType(PostHistoryType.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public ItemWriter<PostHistoryType> postHistoryTypeWriter(DataSource dataSource) {
		JdbcBatchItemWriter<PostHistoryType> writer = new JdbcBatchItemWriter<PostHistoryType>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<PostHistoryType>());
		writer.setSql("INSERT INTO posthistorytypes (id, name) VALUES (:id, :name)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public Job importPostHistoryTypes(JobBuilderFactory jobs, Step step) {
		return jobs.get("importPostHistoryTypes").incrementer(new RunIdIncrementer()).flow(step).end().build();
	}

	@Bean
	public Step step2(StepBuilderFactory stepBuilderFactory, ItemReader<PostHistoryType> reader,
			ItemWriter<PostHistoryType> writer) {
		return stepBuilderFactory.get("step2").<PostHistoryType, PostHistoryType> chunk(10).reader(reader)
				.writer(writer).build();
	}

}
