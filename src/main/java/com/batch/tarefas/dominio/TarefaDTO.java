package com.batch.tarefas.dominio;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "tarefa")
public class TarefaDTO {

    private int id;
    private String descricao;
    private String status;
    private LocalDate data;
    private int categoriaId;
    private int usuarioId;
    private List<Link> links;

    public TarefaDTO() { }

    public TarefaDTO(int id, String descricao, String status, LocalDate data, int categoriaId, int usuarioId, List<Link>  links) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.data = data;
        this.categoriaId = categoriaId;
        this.usuarioId = usuarioId;
        this.links = links;
    }
}
