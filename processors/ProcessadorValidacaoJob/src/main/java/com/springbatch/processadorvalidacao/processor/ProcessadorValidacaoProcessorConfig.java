package com.springbatch.processadorvalidacao.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.processadorvalidacao.dominio.Cliente;

@Configuration
public class ProcessadorValidacaoProcessorConfig {
	@Bean
	public ItemProcessor<Cliente, Cliente> procesadorValidacaoProcessor() {
		// TODO: Implementar a lógica aqui...
		return cliente -> cliente;
	}
}
