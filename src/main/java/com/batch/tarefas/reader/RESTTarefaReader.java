package com.batch.tarefas.reader;

import com.batch.tarefas.dominio.TarefaDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RESTTarefaReader implements ItemReader<TarefaDTO> {

    private final String url;
    private final RestTemplate restTemplate;
    private int proximaTarefaIndex;
    private List<TarefaDTO> dadosTarefas;

    public RESTTarefaReader(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
        this.proximaTarefaIndex = 0;
    }

    @Override
    public TarefaDTO read() throws Exception {
        if (tarefaNaoInicializada()) {
            dadosTarefas = obterDadosAPI();
        }

        TarefaDTO proximaTarefa = null;

        if(proximaTarefaIndex < dadosTarefas.size()) {
            proximaTarefa = dadosTarefas.get(proximaTarefaIndex);
            proximaTarefaIndex++;
        } else {
            proximaTarefaIndex = 0;
            dadosTarefas = null;
        }
        return proximaTarefa;
    }

    private boolean tarefaNaoInicializada() {
        return this.dadosTarefas == null;
    }

    private List<TarefaDTO> obterDadosAPI() {
        ResponseEntity<TarefaDTO[]> response = restTemplate.getForEntity(url, TarefaDTO[].class);
        TarefaDTO[] dadosTarefas = response.getBody();
        return Arrays.asList(dadosTarefas);
    }

}
