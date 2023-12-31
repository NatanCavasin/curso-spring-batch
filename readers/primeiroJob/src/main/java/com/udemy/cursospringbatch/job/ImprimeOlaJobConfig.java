package com.udemy.cursospringbatch.job;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing //Permite que seja oinjetado os componentes do spring Batch
@Configuration
public class ImprimeOlaJobConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	
	
	@Bean
	public Job imprimeOlaJob(Step imprimeOlaStep) {
		return jobBuilderFactory
				.get("imprimeOlaJob") //Nome do Job
				.start(imprimeOlaStep) //steps
				.incrementer(new RunIdIncrementer()) // Adiciona um run ID a acda execução, deste modo o Job pode ser executado mais de uma vez.
				.build();
		// O incrementador somente pode ser utilizado se o Job não precisa ser reinicializado
	}
	
}
