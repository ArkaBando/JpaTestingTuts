package com.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.model", "com.dao", "com.service" })
public class AppConfig {
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setJpaProperties(additionalProperties());
		emf.setPackagesToScan("com.model");
		return emf;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("#pass1234");
		return dataSource;
	}

	@Bean
	public JpaTransactionManager getJpaTransactionManager(
			EntityManagerFactory emf) {
		JpaTransactionManager jpaTx = new JpaTransactionManager();
		jpaTx.setEntityManagerFactory(emf);
		return jpaTx;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect",
				"org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql",
				"true");
		properties.setProperty("hibernate.format_sql",
				"true");
		return properties;
	}
	
	
}
