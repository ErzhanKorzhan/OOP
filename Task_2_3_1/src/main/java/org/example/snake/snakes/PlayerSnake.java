package org.example.snake.snakes;

import javafx.geometry.Point2D;
import org.example.snake.model.Direction;
import org.example.snake.model.GameModel;
import org.example.snake.model.Snake;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class PlayerSnake implements Snake {
    protected Deque<Point2D> body = new LinkedList<>();
    protected Direction dir = Direction.RIGHT;
    protected int growthCounter = 0;
    protected boolean alive = true;
    protected boolean winner = false;

    public PlayerSnake(Point2D start) {
        body.add(start);
    }

    @Override
    public void move(GameModel model) {
        if (!alive || winner) return;

        Point2D currentHead = getHead();
        Point2D raw = new Point2D(
                currentHead.getX() + dir.dx,
                currentHead.getY() + dir.dy
        );

        double newX = (raw.getX() + model.getCols()) % model.getCols();
        double newY = (raw.getY() + model.getRows()) % model.getRows();
        Point2D head = new Point2D(newX, newY);

        body.addFirst(head);

        if (growthCounter > 0) {
            growthCounter--;
        } else {
            body.removeLast();
        }
    }

    @Override
    public Deque<Point2D> getBody() {
        return body;
    }

    @Override
    public Point2D getHead() {
        return body.peekFirst();
    }

    @Override
    public boolean occupies(Point2D p) {
        return body.contains(p);
    }

    @Override
    public boolean selfCollision() {
        return new HashSet<>(body).size() < body.size();
    }

    @Override
    public void grow() {
        growthCounter++;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public boolean isWinner() {
        return winner;
    }

    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    @Override
    public void setDirection(Direction d) {
        if (d.dx + dir.dx != 0 || d.dy + dir.dy != 0) {
            this.dir = d;
        }
    }

    @Override
    public Direction getDirection() {
        return dir;
    }
}