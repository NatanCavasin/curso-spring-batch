package com.springbatch.enviopromocoesemail.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import com.springbatch.enviopromocoesemail.dominio.InteresseProdutoCliente;

@Configuration
public class EnvioemailClientesStepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step envioemailClientesStep(
			ItemStreamReader<InteresseProdutoCliente> lerInteresseProdutoClienteReader,
			ItemProcessor<InteresseProdutoCliente, SimpleMailMessage> processarEmailProdutoClienteProcessor,
			ItemWriter<SimpleMailMessage> enviarEmailProdutoClienteWriter
			) {
		return stepBuilderFactory
				.get("envioemailClientesStep")
				.<InteresseProdutoCliente, SimpleMailMessage>chunk(1)
				.reader(lerInteresseProdutoClienteReader)
				.processor(processarEmailProdutoClienteProcessor)
				.writer(enviarEmailProdutoClienteWriter)
				.stream(lerInteresseProdutoClienteReader)
				.build();
	}

}
