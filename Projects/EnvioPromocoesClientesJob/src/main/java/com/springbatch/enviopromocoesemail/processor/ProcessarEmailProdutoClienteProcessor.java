package com.springbatch.enviopromocoesemail.processor;

import java.text.NumberFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.jdbc.support.xml.SqlXmlFeatureNotImplementedException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.springbatch.enviopromocoesemail.dominio.InteresseProdutoCliente;

@Component
public class ProcessarEmailProdutoClienteProcessor implements ItemProcessor<InteresseProdutoCliente, SimpleMailMessage> {

	@Override
	public SimpleMailMessage process(InteresseProdutoCliente interesseProdutoCliente) throws Exception {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("xpto@no-reply.com");
		email.setTo(interesseProdutoCliente.getCliente().getEmail());
		email.setSubject("Promoção Imperdível!!!");
		email.setText(buildText(interesseProdutoCliente));
		return email;
	}

	private String buildText(InteresseProdutoCliente interesseProdutoCliente) {
		StringBuilder texto = new StringBuilder();
		texto.append(String.format("Olá, %s!\n\n", interesseProdutoCliente.getCliente().getNome()));
		texto.append("Essa promoção pode ser do teu interesse:\n\n");
		texto.append(String.format("%s - %s\n\n", interesseProdutoCliente.getProduto().getNome(), interesseProdutoCliente.getProduto().getDescricao()));
		texto.append(String.format("Por apenas: %s!", NumberFormat.getCurrencyInstance().format(interesseProdutoCliente.getProduto().getPreco())));
		return texto.toString();
		
	}

	
}
