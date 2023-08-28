package com.springbatch.processadorclassifier.processor;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.processadorclassifier.dominio.Cliente;

public class ClienteProcessor implements ItemProcessor<Cliente, Cliente>{

	@Override
	public Cliente process(Cliente item) throws Exception {
		//Regras de negócio
		System.out.println("Aplicando regras de negócio no cliente: " + item.getEmail());
		return item;
	}

}
