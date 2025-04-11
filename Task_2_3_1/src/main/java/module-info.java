module ru.nsu.lavrenenkov.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ru.nsu.lavrenenkov.snake to javafx.fxml;
    exports ru.nsu.lavrenenkov.snake;
}