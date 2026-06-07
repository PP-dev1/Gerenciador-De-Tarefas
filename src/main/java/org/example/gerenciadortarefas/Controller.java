package org.example.gerenciadortarefas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<Tarefa> tabela;

    @FXML
    private TableColumn<Tarefa, String> task;

    @FXML
    private TableColumn<Tarefa, String> descricao;

    @FXML
    private TableColumn<Tarefa, String> colPrioridade;

    @FXML
    private TableColumn<Tarefa, String> status;

    @FXML
    private ComboBox<String> prioridade;

    @FXML
    private TextField campoNome;

    @FXML
    private TextArea campoDescricao;

    @FXML
    private TextField barraPesquisa;


    private ObservableList<Tarefa> lista =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        prioridade.setItems(
                FXCollections.observableArrayList(
                        "Alta",
                        "Média",
                        "Baixa"
                )
        );

        task.setCellValueFactory(new PropertyValueFactory<>("nome"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));


        colPrioridade.setCellValueFactory(new PropertyValueFactory<>("prioridade"));
        colPrioridade.setCellFactory(column -> new TableCell<Tarefa, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    switch (item) {
                        case "Alta"  -> setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: black; -fx-font-weight: bold;");
                        case "Média" -> setStyle("-fx-background-color: #f4ff00; -fx-text-fill: black; -fx-font-weight: bold;");
                        case "Baixa" -> setStyle("-fx-background-color: #2196f3; -fx-text-fill: black; -fx-font-weight: bold;");
                        default      -> setStyle("");
                    }
                }
            }
        });

        tabela.setItems(lista);
    }

    @FXML
    public void criarTarefa() {

        String nome = campoNome.getText();
        String desc = campoDescricao.getText();
        String prio = prioridade.getValue();

        if(nome.isEmpty() || prio == null){
            return;
        }

        Tarefa nova = new Tarefa(nome, desc, prio);
        lista.add(nova);

        campoNome.clear();
        campoDescricao.clear();
        prioridade.setValue(null);
        tabela.setItems(lista);
    }
    public void FiltrarTarefa(){

        String pesquisa = barraPesquisa.getText().toLowerCase();

        ObservableList<Tarefa> filtrada =
                FXCollections.observableArrayList();

        for (Tarefa tarefa : lista) {

            if (tarefa.getNome().toLowerCase().contains(pesquisa) || tarefa.getDescricao().toLowerCase().contains(pesquisa)) {
                filtrada.add(tarefa);
            }
        }
        tabela.setItems(filtrada);
    }
}
