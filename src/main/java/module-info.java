module org.example.gerenciadortarefas {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.gerenciadortarefas to javafx.fxml;
    exports org.example.gerenciadortarefas;
}