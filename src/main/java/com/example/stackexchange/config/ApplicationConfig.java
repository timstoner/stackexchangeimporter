package com.example.stackexchange.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.example.stackexchange")
@EnableTransactionManagement
public class ApplicationConfig {

	private static final Logger LOG = LoggerFactory
			.getLogger(ApplicationConfig.class);

	@Value("#{'${jdbc.driverClassName}'}")
	private String driverClassName;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Value("${hibernate.database}")
	private String database;

	@Value("classpath:hibernate.properties")
	private Resource hibernateProperties;

	@Bean
	public DataSource dataSource() {
		LOG.info("Building Data Source {} {} {}", driverClassName, url,
				username);
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setValidationQuery("SELECT 1");

		return ds;
	}

	@Bean
	public HibernateJpaVendorAdapter hibernateVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform(database);
		adapter.setDatabase(Database.H2);
		adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");

		return adapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan("com.example.stackexchange.entity");
		bean.setJpaVendorAdapter(hibernateVendorAdapter());
		bean.setJpaProperties(jpaProperties());

		return bean;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;
	}

	@Bean
	public Properties jpaProperties() {
		String file = "/hibernate.properties";
		LOG.info("Loading JPA Properties from File '{}'", file);
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(hibernateProperties);
		Properties properties = null;
		try {
			propertiesFactoryBean.afterPropertiesSet();
			properties = propertiesFactoryBean.getObject();

		} catch (IOException e) {
			LOG.warn("Cannot load properties file '{}'", file);
		}
		return properties;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
