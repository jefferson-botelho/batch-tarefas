package com.batch.tarefas.config;

import com.batch.tarefas.dominio.TarefaDTO;
import com.batch.tarefas.reader.RESTTarefaReader;
import com.batch.tarefas.writer.LoggingItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class JobConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<TarefaDTO> itemReader(Environment environment, RestTemplate restTemplate) {
        return new RESTTarefaReader(environment.getRequiredProperty("api.url"), restTemplate);
    }

    @Bean
    public ItemWriter<TarefaDTO> itemWriter() {
        return new LoggingItemWriter();
    }

    @Bean
    public Step tarefaStep(ItemReader<TarefaDTO> reader, ItemWriter<TarefaDTO> writer) {
        return stepBuilderFactory.get("tarefaStep")
                .<TarefaDTO, TarefaDTO>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Job tarefaJob(Step tarefaStep) {
        return jobBuilderFactory.get("tarefaJob")
                .incrementer(new RunIdIncrementer())
                .flow(tarefaStep)
                .end()
                .build();
    }

}
