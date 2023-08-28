package com.springbatch.jdbcpagingreader.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.springbatch.jdbcpagingreader.dominio.Cliente;

@Configuration
public class JdbcPagingReaderReaderConfig {
	@Bean
	public JdbcPagingItemReader<Cliente> jdbcPagingReader(
				@Qualifier("appDataSource") DataSource dataSource,
				PagingQueryProvider queryProvider
			) {
		return new JdbcPagingItemReaderBuilder<Cliente>()
				.name("jdbcPagingReader")
				.dataSource(dataSource)
				.queryProvider(queryProvider)
				.pageSize(1)   //Paginação da query
				.rowMapper(new BeanPropertyRowMapper<Cliente>(Cliente.class))   //Responsável por mapear o resultado da consulta em um objeto de dominio
				.build();
	}
	
	@Bean
	public SqlPagingQueryProviderFactoryBean queryProvider(@Qualifier("appDataSource") DataSource datasource) {
		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
		queryProvider.setDataSource(datasource);
		queryProvider.setSelectClause("select *");
		queryProvider.setFromClause("from cliente");
		queryProvider.setSortKey("email");
		
		return queryProvider;
		
	}
}
