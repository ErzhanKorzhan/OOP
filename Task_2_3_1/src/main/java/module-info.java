module org.example.snake {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.snake.model to javafx.fxml;
    opens org.example.snake.controller to javafx.fxml;
    exports org.example.snake;
}