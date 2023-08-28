package com.springbatch.processadorclassifier.processor;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.processadorclassifier.dominio.Transacao;

public class TransacaoProcessor implements ItemProcessor<Transacao, Transacao>{

	@Override
	public Transacao process(Transacao item) throws Exception {
		//Regras de negócio
		System.out.println("Aplicando regras de negócio na Transacao: " + item.getId());
		return item;
	}

}
