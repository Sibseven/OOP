package ru.nsu.lavrenenkov.snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class GameController {
    private static final int TILE_SIZE = 25;
    private static final int GRID_SIZE = 17;
    private static final int MAX_FOOD = 2;
    private static final int WIN_SIZE = 3;
    private List<List<Rectangle>> field;
    private List<Food> foods;
    private Snake snake;
    private boolean isAlive = false;
    Timeline gameCycle;

    public GameController() {
        field = new ArrayList<>();
        foods = new ArrayList<>();
    }

    @FXML
    private Pane gameBoard;

    @FXML
    public void initialize() {
        field = new ArrayList<>();
        foods = new ArrayList<>();
        gameBoard.setFocusTraversable(true);
        gameBoard.requestFocus();
        createGrid();
        createSnake();
        setupGameCycle();
        isAlive = true;
        gameBoard.setOnKeyPressed(this::handleKeyPress);
    }


    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                snake.changeDirection(Snake.Direction.UP);
                break;
            case S:
                snake.changeDirection(Snake.Direction.DOWN);
                break;
            case A:
                snake.changeDirection(Snake.Direction.LEFT);
                break;
            case D:
                snake.changeDirection(Snake.Direction.RIGHT);
                break;
        }
    }


    private void createGrid() {
        for (int x = 0; x < GRID_SIZE; x++) {
            field.add(new ArrayList<>());
            for (int y = 0; y < GRID_SIZE; y++) {
                Rectangle cell = new Rectangle(TILE_SIZE, TILE_SIZE);
                field.get(x).add(cell);
                cell.setFill((x + y) % 2 == 0 ? Paint.valueOf("#00FF00") : Paint.valueOf("#008000"));
                cell.setX(x * TILE_SIZE);
                cell.setY(y * TILE_SIZE);
                gameBoard.getChildren().add(cell);
            }
        }
    }

    private void setupGameCycle() {
        if(gameCycle != null) {
            gameCycle.stop();
        }
        gameCycle = new Timeline(new KeyFrame(Duration.seconds(0.7), event -> gameCycleEvent()));
        gameCycle.setCycleCount(Timeline.INDEFINITE);
        gameCycle.play();
    }

    private void createSnake() {
        SnakeBody head = new SnakeBody();
        head.setX(7);
        head.setY(7);
        this.snake = new Snake(head);
        snake.changeDirection(Snake.Direction.UP);
    }

    private void gameCycleEvent() {
    if (isAlive) {
        gameBoard.requestFocus();
        snake.move();
        checkCollisions();
        if (!isAlive) {
            restart();
        }
        checkFood();
        createFood();
        redraw();
        }
    }

    private void restart() {
        Button bt = new Button("Restart");
        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                initialize();
            }
        });
        gameBoard.getChildren().add(bt);
    }

    private void checkCollisions() {
        if (snake.getBody().getFirst().getX() > GRID_SIZE || snake.getBody().getFirst().getX() < 0
                    || snake.getBody().getFirst().getY() > GRID_SIZE || snake.getBody().getFirst().getX() < 0) {
                isAlive = false;
                return;
        }
        for (int i = 0; i < snake.getBody().size(); i++) {
            for (int j = i + 1; j < snake.getBody().size(); j++) {
                SnakeBody obj1 = snake.getBody().get(i);
                SnakeBody obj2 = snake.getBody().get(j);
                if (obj1.getX() == obj2.getX() && obj1.getY() == obj2.getY()) {
                    isAlive = false;
                    return;
                }
            }
        }
    }

    private void checkFood() {
        SnakeBody head = snake.getBody().getFirst();
        Food foodEaten = null;
        for (Food food : foods) {
            if (food.getX() == head.getX() && food.getY() == head.getY()) {
                snake.addBody();
                foodEaten = food;
                break;
            }
        }
        foods.remove(foodEaten);
        try {
            checkWin();
        }
        catch (Exception e) {
            System.out.println("Interrupted!");
        }
    }

    private void checkWin() throws InterruptedException {
        if (snake.getBody().size() >= WIN_SIZE) {
            Label win = new Label("YOU WIN");
            gameBoard.getChildren().add(win);
            win.layoutXProperty().bind(
                    gameBoard.widthProperty().subtract(win.widthProperty()).divide(2)
            );
            win.layoutYProperty().bind(
                    gameBoard.heightProperty().subtract(win.heightProperty()).divide(2)
            );

            win.toFront();
            gameCycle.stop();
            sleep(2000);
            restart();
        }
    }
    private void createFood() {
        while (foods.size() < MAX_FOOD) {
            boolean collison = false;
            int x = 0;
            int y = 0;
            do {
                x = (int) (Math.random() * GRID_SIZE);
                y = (int) (Math.random() * GRID_SIZE);
                for (SnakeBody body : snake.getBody()) {
                    if (body.getX() == x && body.getY() == y) {
                        collison = true;
                    }
                }
                for (Food food : foods) {
                    if (food.getX() == x && food.getY() == y) {
                        collison = true;
                    }
                }
            } while (collison);
            Food food = new Food(x, y);
            foods.add(food);
        }
    }

    private void redraw() {
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                Rectangle cell = field.get(x).get(y);
                cell.setFill((x + y) % 2 == 0 ? Paint.valueOf("#00FF00") : Paint.valueOf("#008000"));
            }
        }
        for (SnakeBody body : snake.getBody()) {
            int x = body.getX();
            int y = body.getY();
            field.get(x).get(y).setFill(Paint.valueOf("#0000FF"));
        }
        int xHead = snake.getBody().getFirst().getX();
        int yHead = snake.getBody().getFirst().getY();
        field.get(xHead).get(yHead).setFill(Paint.valueOf("#00BFFF"));

        for (Food food : foods) {
            int x = food.getX();
            int y = food.getY();
            field.get(x).get(y).setFill(Paint.valueOf("#FF0000"));
        }
    }
}