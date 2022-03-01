package com.batch.tarefas.writer;

import com.batch.tarefas.dominio.TarefaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class LoggingItemWriter implements ItemWriter<TarefaDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingItemWriter.class);

    @Override
    public void write(List<? extends TarefaDTO> list) throws Exception {
        LOGGER.info("Escrevendo tarefa: {}", list);
    }
}
