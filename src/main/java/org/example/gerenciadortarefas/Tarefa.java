package org.example.gerenciadortarefas;

import javafx.beans.property.*;

public class Tarefa {

    private StringProperty nome =
            new SimpleStringProperty();

    private StringProperty descricao =
            new SimpleStringProperty();

    private StringProperty prioridade =
            new SimpleStringProperty();

    private StringProperty status =
            new SimpleStringProperty();

    private BooleanProperty concluida =
            new SimpleBooleanProperty(false);

    public Tarefa(String nome,
                  String descricao,
                  String prioridade,
                  String status) {

        this.nome.set(nome);
        this.descricao.set(descricao);
        this.prioridade.set(prioridade);
        this.status.set(status);

        concluida.addListener((obs, oldVal, newVal) -> {

            if(newVal){
                this.status.set("Concluído");
            }else{
                this.status.set("Pendente");
            }
        });
    }

    public String getNome() {
        return nome.get();
    }

    public String getDescricao() {
        return descricao.get();
    }

    public String getPrioridade() {
        return prioridade.get();
    }

    public String getStatus() {
        return status.get();
    }

    public BooleanProperty concluidaProperty() {
        return concluida;
    }

    public StringProperty statusProperty() {
        return status;
    }
}