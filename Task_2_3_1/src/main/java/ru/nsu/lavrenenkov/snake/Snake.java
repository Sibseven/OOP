package ru.nsu.lavrenenkov.snake;
import java.util.LinkedList;

public class Snake {
    private final LinkedList<SnakeBody> body;
    private Direction direction;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    public Snake(SnakeBody head) {
        this.body = new LinkedList<>();
        this.body.add(head);
    }

    public LinkedList<SnakeBody> getBody() {
        return body;
    }

    public void changeDirection(Direction direction) {
        if(this.direction == Direction.UP && direction == Direction.DOWN) {
            return;
        }
        if(this.direction == Direction.LEFT && direction == Direction.RIGHT) {
            return;
        }
        if(this.direction == Direction.RIGHT && direction == Direction.LEFT) {
            return;
        }
        if(this.direction == Direction.DOWN && direction == Direction.UP) {
            return;
        }

        this.direction = direction;
    }

    public void addBody() {
        SnakeBody newBody = new SnakeBody();
        switch (this.direction) {
            case UP:
                newBody.setX(body.getFirst().getX());
                newBody.setY(body.getFirst().getY() - 1);
                break;
            case DOWN:
                newBody.setX(body.getFirst().getX());
                newBody.setY(body.getFirst().getY() + 1); //два раза наоборот так как y растет сверху вниз + новый элемент должен появляться в хваосте
                break;
            case LEFT:
                newBody.setX(body.getFirst().getX() - 1);
                newBody.setY(body.getFirst().getY());
                break;
            case RIGHT:
                newBody.setX(body.getFirst().getX() + 1);
                newBody.setY(body.getFirst().getY());
                break;
        }
        body.addFirst(newBody);
    }

    public void move() {
        int xOffset = 0;
        int yOffset = 0;
        switch (this.direction) {
            case UP:
                yOffset = -1;
                break;
            case DOWN:
                yOffset = 1; //наоборот так как y растет снизу вверх
                break;
            case LEFT:
                xOffset = -1;
                break;
            case RIGHT:
                xOffset = 1;
                break;
        }
        SnakeBody tail = body.removeLast();
        if(body.isEmpty()) {
            tail.setX(tail.getX() + xOffset);
            tail.setY(tail.getY() + yOffset);
        }
        else {
            tail.setX(body.getFirst().getX() + xOffset);
            tail.setY(body.getFirst().getY() + yOffset);
        }
        body.addFirst(tail);
    }
}