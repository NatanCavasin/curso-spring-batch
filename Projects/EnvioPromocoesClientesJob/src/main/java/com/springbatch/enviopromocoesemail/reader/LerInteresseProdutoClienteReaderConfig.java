package com.springbatch.enviopromocoesemail.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.springbatch.enviopromocoesemail.dominio.Cliente;
import com.springbatch.enviopromocoesemail.dominio.InteresseProdutoCliente;
import com.springbatch.enviopromocoesemail.dominio.Produto;

@Configuration
public class LerInteresseProdutoClienteReaderConfig {

	@Bean
	public JdbcCursorItemReader<InteresseProdutoCliente> lerInteresseProdutoClienteReader(
			@Qualifier("appDataSource") DataSource appDataSource
			){
		return new JdbcCursorItemReaderBuilder<InteresseProdutoCliente>()
				.name("lerInteresseProdutoClienteReader")
				.dataSource(appDataSource)
				.sql(this.getQuery())
				.rowMapper(this.rowMapper())
				.build();
	}
	
	private String getQuery() {
		StringBuilder query = new StringBuilder();
		query.append("select * from interesse_produto_cliente ");
		query.append("join cliente on (cliente = cliente.id) ");
		query.append("join produto on (produto = produto.id)");
		return query.toString();
	}
	
	private RowMapper<InteresseProdutoCliente> rowMapper() {
		return new RowMapper<InteresseProdutoCliente>() {
			
			@Override
			public InteresseProdutoCliente mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				
				Produto produto = new Produto();
				produto.setId(rs.getInt(6));
				produto.setNome(rs.getString(7));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				
				InteresseProdutoCliente interesseProdutoCliente = new InteresseProdutoCliente();
				interesseProdutoCliente.setCliente(cliente);
				interesseProdutoCliente.setProduto(produto);
				System.out.println(interesseProdutoCliente);
				return interesseProdutoCliente;
				
			}
		};
	}
	
}
