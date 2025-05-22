package org.example.snake.model;

import javafx.geometry.Point2D;
import java.util.Deque;

public interface Snake {
    Deque<Point2D> getBody();
    Point2D getHead();
    boolean occupies(Point2D p);
    boolean selfCollision();
    void move(GameModel model);
    void grow();
    boolean isAlive();
    void setAlive(boolean alive);
    boolean isWinner();
    void setWinner(boolean winner);
    Direction getDirection();
    void setDirection(Direction d);
}