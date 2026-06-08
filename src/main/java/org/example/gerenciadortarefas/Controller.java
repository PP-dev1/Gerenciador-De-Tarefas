package org.example.gerenciadortarefas;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<Tarefa> tabela;

    @FXML
    private TableColumn<Tarefa, Boolean> marcar;

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

        tabela.setEditable(true);
        marcar.setEditable(true);

        marcar.setCellValueFactory(cellData ->
                cellData.getValue().concluidaProperty());

        marcar.setCellFactory(
                CheckBoxTableCell.forTableColumn(marcar)
        );

        task.setCellValueFactory(
                new PropertyValueFactory<>("nome"));

        descricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao"));

        status.setCellValueFactory(
                cellData -> cellData.getValue().statusProperty());

        colPrioridade.setCellValueFactory(
                new PropertyValueFactory<>("prioridade"));

        marcar.setCellValueFactory(cellData ->
                cellData.getValue().concluidaProperty());

        marcar.setCellFactory(
                CheckBoxTableCell.forTableColumn(marcar));

        colPrioridade.setCellFactory(column ->
                new TableCell<>() {

                    @Override
                    protected void updateItem(String item,
                                              boolean empty) {

                        super.updateItem(item, empty);

                        if(empty || item == null){
                            setText(null);
                            setStyle("");
                            return;
                        }

                        setText(item);

                        switch (item){

                            case "Alta" ->
                                    setStyle("-fx-background-color:#ff4d4d;");

                            case "Média" ->
                                    setStyle("-fx-background-color:#f4ff00;");

                            case "Baixa" ->
                                    setStyle("-fx-background-color:#2196f3;");
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

        if(nome.isBlank() || prio == null){
            return;
        }

        Tarefa nova =
                new Tarefa(
                        nome,
                        desc,
                        prio,
                        "Pendente"
                );

        lista.add(nova);

        lista.sort((t1, t2) ->
                Integer.compare(
                        prioridadeValor(t1.getPrioridade()),
                        prioridadeValor(t2.getPrioridade())
                )
        );

        campoNome.clear();
        campoDescricao.clear();
    }

    @FXML
    public void FiltrarTarefa(){

        String pesquisa =
                barraPesquisa
                        .getText()
                        .toLowerCase();

        ObservableList<Tarefa> filtrada =
                FXCollections.observableArrayList();

        for(Tarefa tarefa : lista){

            if(tarefa.getNome()
                    .toLowerCase()
                    .contains(pesquisa)

                    || tarefa.getDescricao()
                    .toLowerCase()
                    .contains(pesquisa)){

                filtrada.add(tarefa);
            }
        }

        tabela.setItems(filtrada);
    }

    private int prioridadeValor(String prioridade){

        return switch (prioridade){

            case "Alta" -> 1;

            case "Média" -> 2;

            case "Baixa" -> 3;

            default -> 999;
        };
    }
}