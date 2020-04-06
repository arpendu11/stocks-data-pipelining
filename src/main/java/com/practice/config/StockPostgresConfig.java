package com.practice.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableJpaRepositories(
		basePackages = "com.practice.second.repositories",
		entityManagerFactoryRef = "secondEntityManagerFactory",
		transactionManagerRef = "secondTransactionManager"
)
public class StockPostgresConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory() {
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(secondDataSource());
		em.setPackagesToScan(new String[] { "com.practice.model" });
		em.setPersistenceUnitName("model");
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.afterPropertiesSet();
		
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.generate-ddl", environment.getProperty("hibernate.generate-ddl"));
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.put("hibernate.dialect", environment.getProperty("postgres.jdbc.dialect"));
		em.setJpaPropertyMap(properties);
		
		return em;
	}


	@Bean
	public DataSource secondDataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("postgres.jdbc.driverClassName"));
		dataSource.setUrl(environment.getProperty("postgres.jdbc.url"));
		dataSource.setUsername(environment.getProperty("postgres.jdbc.user"));
		dataSource.setPassword(environment.getProperty("postgres.jdbc.pass"));
		
		return dataSource;
	}
	
	
	@Bean
	public PlatformTransactionManager secondTransactionManager(EntityManagerFactory entityManagerFactory) {
		
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(secondEntityManagerFactory().getObject());
		
		return transactionManager;
	}
}
