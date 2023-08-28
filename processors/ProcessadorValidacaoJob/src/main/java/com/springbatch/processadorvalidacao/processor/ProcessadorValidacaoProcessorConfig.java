package com.springbatch.processadorvalidacao.processor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.processadorvalidacao.dominio.Cliente;

@Configuration
public class ProcessadorValidacaoProcessorConfig {
	
	private Set<String> emails = new HashSet<>();
	
	@Bean
	public ItemProcessor<Cliente, Cliente> procesadorValidacaoProcessor() throws Exception {
		return new CompositeItemProcessorBuilder<Cliente, Cliente>()
				.delegates(
					beanValidatingProcessor(), 
					emailValidatingProcessor()
				)
				.build();
		
	}
	
	/**
	 * Valida com base nos Validators definidos na classe Cliente 
	 * @return
	 * @throws Exception
	 */
	private BeanValidatingItemProcessor<Cliente> beanValidatingProcessor() throws Exception {
		BeanValidatingItemProcessor<Cliente> processor = new BeanValidatingItemProcessor<>();
		processor.setFilter(true); //não interrompe a execução do bach em caso de erro
		processor.afterPropertiesSet(); // Como esta em um processador composto é preciso setar as propriedades desse processador
		return processor;
	}
	
	private ValidatingItemProcessor<Cliente> emailValidatingProcessor() {
		ValidatingItemProcessor<Cliente> processor = new ValidatingItemProcessor<>();
		processor.setValidator(validator());
		processor.setFilter(true); //não interrompe a execução do bach
		return processor;
	}

	private Validator<? super Cliente> validator() {
		return new Validator<Cliente>() {

			@Override
			public void validate(Cliente cliente) throws ValidationException {
				if(emails.contains(cliente.getEmail())) {
					throw new ValidationException(String.format("O cliente %s já foi processado", cliente.getEmail()));
				}
				emails.add(cliente.getEmail());
			}
		};
	}
}
