package ru.nsu.lavrenenkov.snake;

public class SnakeBody {
    private int X;
    private int Y;

    public int getX() {
        return X;
    }
    public void setX(int x) {
        X = x;
    }
    public int getY() {
        return Y;
    }
    public void setY(int y) {
        Y = y;
    }

    public SnakeBody(SnakeBody snakeBody) {
        X = snakeBody.getX();
        Y = snakeBody.getY();
    }
    public SnakeBody() {

    }
}
