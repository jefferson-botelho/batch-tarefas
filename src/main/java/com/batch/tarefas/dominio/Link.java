package com.batch.tarefas.dominio;

import lombok.Data;

@Data
public class Link {
    private String rel;
    private String href;

    public Link(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }

    public Link() { }

}
