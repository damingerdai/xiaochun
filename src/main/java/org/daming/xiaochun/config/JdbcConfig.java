package org.daming.xiaochun.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Desc: Jdbc配置
 *
 * @author daming
 * @version 2018-08-22 21:40
 */
@Configuration
@PropertySource(value = {"classpath:jdbc.properties"})
public class JdbcConfig  implements EnvironmentAware {

	private Environment environment;

	@Bean
	public DataSource dataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
		hikariDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		hikariDataSource.setUsername(environment.getProperty("jdbc.username"));
		hikariDataSource.setPassword(environment.getProperty("jdbc.password"));
		return hikariDataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
