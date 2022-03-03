package com.batch.tarefas.processor;

import com.batch.tarefas.dominio.TarefaDTO;
import com.batch.tarefas.writer.LoggingItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class TarefaProcessor implements ItemProcessor<TarefaDTO, TarefaDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingItemWriter.class);

    @Override
    public TarefaDTO process(TarefaDTO tarefaDTO) throws Exception {
        LOGGER.info("Processando tarefa ("+tarefaDTO+")");
        return tarefaDTO;
    }
}
