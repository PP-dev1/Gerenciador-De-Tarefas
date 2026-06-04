package org.example.gerenciadortarefas;

public class Tarefa {

    private String nome;
    private String descricao;
    private String prioridade;

    public Tarefa(String nome, String descricao, String prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }
}