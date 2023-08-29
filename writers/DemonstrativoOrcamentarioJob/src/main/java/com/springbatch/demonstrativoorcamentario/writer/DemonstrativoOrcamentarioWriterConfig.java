package com.springbatch.demonstrativoorcamentario.writer;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.demonstrativoorcamentario.dominio.GrupoLancamento;
import com.springbatch.demonstrativoorcamentario.dominio.Lancamento;

@Configuration
public class DemonstrativoOrcamentarioWriterConfig {
	@Bean
	public ItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter() {
		return itens -> {
			System.out.println("\n");
			System.out.println(String.format("SISTEMA INTEGRADO: XPTO \t\t\t\t DATA: %s", new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
			System.out.println(String.format("MÓDULO: ORÇAMENTO \t\t\t\t\t HORA: %s", new SimpleDateFormat("HH:MM").format(new Date())));
			System.out.println(String.format("\t\t\tDEMONSTRATIVO ORCAMENTARIO"));
			System.out.println(String.format("----------------------------------------------------------------------------"));
			System.out.println(String.format("CODIGO NOME VALOR"));
			System.out.println(String.format("\t Data Descricao Valor"));
			System.out.println(String.format("----------------------------------------------------------------------------"));
			
			Double totalGeral = 0.0;
			for (GrupoLancamento grupo : itens) {
				System.out.println(String.format("[%d] %s - %s", grupo.getCodigoNaturezaDespesa(),
						grupo.getDescricaoNaturezaDespesa(),
						NumberFormat.getCurrencyInstance().format(grupo.getTotal())));
				totalGeral += grupo.getTotal();
				for (Lancamento lancamento : grupo.getLancamentos()) {
					System.out.println(String.format("\t [%s] %s - %s", new SimpleDateFormat("dd/MM/yyyy").format(lancamento.getData()), lancamento.getDescricao(),
							NumberFormat.getCurrencyInstance().format(lancamento.getValor())));
				}
			}
			System.out.println("\n");
			System.out.println(String.format("\t\t\t\t\t\t\t  Total: %s", NumberFormat.getCurrencyInstance().format(totalGeral)));
			System.out.println(String.format("\t\t\t\t\t\t\t  Código de Autenticação: %s", "fkyew6868fewjfhjjewf"));
		};
	}
}
